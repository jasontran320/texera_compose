package edu.uci.ics.texera.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import edu.uci.ics.texera.api.constants.ErrorMessages;
import edu.uci.ics.texera.api.constants.SchemaConstants;
import edu.uci.ics.texera.api.exception.StorageException;
import edu.uci.ics.texera.api.field.IDField;
import edu.uci.ics.texera.api.field.IField;
import edu.uci.ics.texera.api.schema.Attribute;
import edu.uci.ics.texera.api.schema.AttributeType;
import edu.uci.ics.texera.api.schema.Schema;
import edu.uci.ics.texera.api.tuple.Tuple;
import edu.uci.ics.texera.storage.utils.StorageUtils;

/**
 * DataWriter is the layer where Texera handles upper-level operators' write/delete/update operations
 *   and performs corresponding operations to Lucene.
 *   
 * Write Operations:
 *   DataWriter will write tuples to a Lucene index folder.
 *   DataWriter will assign an random generated "_id" field to every tuple
 *   that is being inserted to the table.
 *   
 * Delete Operations:
 *   DataWriter can handle deletions according to one or more Lucene queries.
 *   It also supports clear all tuples in a table.
 *   
 *   
 * Update Operations:
 *   DataWriter can update the tuple, with the tuple's _id remaining the same.
 *   
 *   
 * DataWriter for a specific table is only accessible from RelationManager.
 * 
 * 
 * @author Zuozhi Wang
 *
 */
public class DataWriter {

    private Path indexDirectory;
    private Schema schema;
    private DataStore dataStore;
    private Analyzer analyzer;

    private IndexWriter luceneIndexWriter;
    
    private boolean isOpen = false;

    /*
     * The package-only level constructor is only accessible inside the storage package.
     * Only the RelationManager is allowed to constructor a DataWriter object, 
     *  while upper-level operators can't.
     */
    DataWriter(DataStore dataStore, Analyzer analyzer) {
        this.indexDirectory = dataStore.getDataDirectory();
        // change the schema to a schema with _ID field
        this.schema = dataStore.getSchema();
        if (! this.schema.containsAttribute(SchemaConstants._ID)) {
            this.schema = Schema.Builder.getSchemaWithID(this.schema);
        }
        this.dataStore = new DataStore(indexDirectory, schema);
        this.analyzer = analyzer;
    }
    
    public DataStore getDataStore() {
        return this.dataStore;
    }
    
    public void open() throws StorageException {
        if (this.luceneIndexWriter == null || ! this.luceneIndexWriter.isOpen()) {
            try {
                Directory directory = FSDirectory.open(this.indexDirectory);
                IndexWriterConfig conf = new IndexWriterConfig(analyzer);
                this.luceneIndexWriter = new IndexWriter(directory, conf);
                this.isOpen = true;
            } catch (IOException e) {
                throw new StorageException(e.getMessage(), e);
            }
        }
    }

    public void close() throws StorageException {
        if (this.luceneIndexWriter != null) {
            try {
                this.luceneIndexWriter.close();
                this.isOpen = false;
            } catch (IOException e) {
                throw new StorageException(e.getMessage(), e);
            }
        }
    }

    public void clearData() throws StorageException {
        if (! isOpen) {
            throw new StorageException(ErrorMessages.OPERATOR_NOT_OPENED);
        }
        try {
            this.luceneIndexWriter.deleteAll();
        } catch (IOException e) {
            close();
            throw new StorageException(e.getMessage(), e);
        }
    }

    public IDField insertTuple(Tuple tuple) throws StorageException {
        if (! isOpen) {
            throw new StorageException(ErrorMessages.OPERATOR_NOT_OPENED);
        }
        try {
            // tuple must not contain _id field
            if (tuple.getSchema().containsAttribute(SchemaConstants._ID)) {
                throw new StorageException("Tuple must not contain _id field. _id must be generated by the system");
            }
            
            // generate a random ID for this tuple
            IDField idField = new IDField(UUID.randomUUID().toString());
            Tuple tupleWithID = getTupleWithID(tuple, idField);
            
            // make sure the tuple's schema agrees with the table's schema
            if (! tupleWithID.getSchema().equals(this.schema)) {
                throw new StorageException("Tuple's schema is not the same as the table's schema");
            }
            
            Document document = getLuceneDocument(tupleWithID);
            this.luceneIndexWriter.addDocument(document);
            this.dataStore.incrementNumDocuments(1);
            
            return idField;
        } catch (IOException e) {
            close();
            throw new StorageException(e.getMessage(), e);
        }
    }
    
    /**
     * Deletes a tuple by its ID field.
     * 
     * @param idField
     * @throws StorageException
     */
    public void deleteTupleByID(IDField idField) throws StorageException {
        if (! isOpen) {
            throw new StorageException(ErrorMessages.OPERATOR_NOT_OPENED);
        }
        try {
            this.luceneIndexWriter.deleteDocuments(new Term(SchemaConstants._ID, idField.getValue().toString()));
        } catch (IOException e) {
            close();
            throw new StorageException(e);
        }
    }
    
    /**
     * Deletes tuple(s) by one (or more) queries.
     * 
     * @param deletionQuery, one or more queries that match the tuples to be deleted
     * @throws StorageException
     */
    public void deleteTuple(Query... deletionQuery) throws StorageException {
        try {
            this.luceneIndexWriter.deleteDocuments(deletionQuery);
        } catch (IOException e) {
            close();
            throw new StorageException(e.getMessage(), e);
        }
    }
    
    /**
     * Updates a tuple by its ID.
     * 
     * @param newTuple
     * @param idField
     * @throws StorageException
     */
    public void updateTuple(Tuple newTuple, IDField idField) throws StorageException {
        if (! isOpen) {
            throw new StorageException(ErrorMessages.OPERATOR_NOT_OPENED);
        }
        try {
            // if the newTuple contains the _id field, make sure the ID is consistent.
            if (newTuple.getSchema().containsAttribute(SchemaConstants._ID)) {
                if (newTuple.getField(SchemaConstants._ID) != idField) {
                    throw new StorageException("New tuple's ID is inconsistent with idValue.");
                }
            } else { // else, add the original ID to the tuple
                newTuple = getTupleWithID(newTuple, idField);
            }
            
            this.luceneIndexWriter.updateDocument(
                    new Term(SchemaConstants._ID, idField.getValue().toString()),
                    getLuceneDocument(newTuple)); 
        } catch (IOException e) {
            close();
            throw new StorageException(e);
        }
    }

    /*
     * Converts a Texera tuple to a Lucene document
     */
    private static Document getLuceneDocument(Tuple tuple) {
        List<IField> fields = tuple.getFields();
        List<Attribute> attributes = tuple.getSchema().getAttributes();
        Document doc = new Document();
        for (int count = 0; count < fields.size(); count++) {
            IField field = fields.get(count);
            Attribute attr = attributes.get(count);
            AttributeType attributeType = attr.getType();
            doc.add(StorageUtils.getLuceneField(attributeType, attr.getName(), field.getValue()));
        }
        return doc;
    }
    
    /*
     * Adds the _id to the front of the tuple, if the _id field doesn't exist in the tuple.
     */
    private static Tuple getTupleWithID(Tuple tuple, IDField _id) {
        Tuple tupleWithID = tuple;
        
        Schema tupleSchema = tuple.getSchema();
        if (! tupleSchema.containsAttribute(SchemaConstants._ID)) {
            tupleSchema = Schema.Builder.getSchemaWithID(tupleSchema);
            List<IField> newTupleFields = new ArrayList<>();
            newTupleFields.add(_id);
            newTupleFields.addAll(tuple.getFields());
            tupleWithID = new Tuple(tupleSchema, newTupleFields.stream().toArray(IField[]::new));
        }
        
        return tupleWithID;
    }

}