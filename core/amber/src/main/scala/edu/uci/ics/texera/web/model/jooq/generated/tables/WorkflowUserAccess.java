/*
 * This file is generated by jOOQ.
 */
package edu.uci.ics.texera.web.model.jooq.generated.tables;


import edu.uci.ics.texera.web.model.jooq.generated.Indexes;
import edu.uci.ics.texera.web.model.jooq.generated.Keys;
import edu.uci.ics.texera.web.model.jooq.generated.TexeraDb;
import edu.uci.ics.texera.web.model.jooq.generated.enums.WorkflowUserAccessPrivilege;
import edu.uci.ics.texera.web.model.jooq.generated.tables.records.WorkflowUserAccessRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.jooq.types.UInteger;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class WorkflowUserAccess extends TableImpl<WorkflowUserAccessRecord> {

    private static final long serialVersionUID = 712932299;

    /**
     * The reference instance of <code>texera_db.workflow_user_access</code>
     */
    public static final WorkflowUserAccess WORKFLOW_USER_ACCESS = new WorkflowUserAccess();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<WorkflowUserAccessRecord> getRecordType() {
        return WorkflowUserAccessRecord.class;
    }

    /**
     * The column <code>texera_db.workflow_user_access.uid</code>.
     */
    public final TableField<WorkflowUserAccessRecord, UInteger> UID = createField(DSL.name("uid"), org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>texera_db.workflow_user_access.wid</code>.
     */
    public final TableField<WorkflowUserAccessRecord, UInteger> WID = createField(DSL.name("wid"), org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>texera_db.workflow_user_access.privilege</code>.
     */
    public final TableField<WorkflowUserAccessRecord, WorkflowUserAccessPrivilege> PRIVILEGE = createField(DSL.name("privilege"), org.jooq.impl.SQLDataType.VARCHAR(5).nullable(false).defaultValue(org.jooq.impl.DSL.inline("NONE", org.jooq.impl.SQLDataType.VARCHAR)).asEnumDataType(edu.uci.ics.texera.web.model.jooq.generated.enums.WorkflowUserAccessPrivilege.class), this, "");

    /**
     * Create a <code>texera_db.workflow_user_access</code> table reference
     */
    public WorkflowUserAccess() {
        this(DSL.name("workflow_user_access"), null);
    }

    /**
     * Create an aliased <code>texera_db.workflow_user_access</code> table reference
     */
    public WorkflowUserAccess(String alias) {
        this(DSL.name(alias), WORKFLOW_USER_ACCESS);
    }

    /**
     * Create an aliased <code>texera_db.workflow_user_access</code> table reference
     */
    public WorkflowUserAccess(Name alias) {
        this(alias, WORKFLOW_USER_ACCESS);
    }

    private WorkflowUserAccess(Name alias, Table<WorkflowUserAccessRecord> aliased) {
        this(alias, aliased, null);
    }

    private WorkflowUserAccess(Name alias, Table<WorkflowUserAccessRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> WorkflowUserAccess(Table<O> child, ForeignKey<O, WorkflowUserAccessRecord> key) {
        super(child, key, WORKFLOW_USER_ACCESS);
    }

    @Override
    public Schema getSchema() {
        return TexeraDb.TEXERA_DB;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.WORKFLOW_USER_ACCESS_PRIMARY, Indexes.WORKFLOW_USER_ACCESS_WID);
    }

    @Override
    public UniqueKey<WorkflowUserAccessRecord> getPrimaryKey() {
        return Keys.KEY_WORKFLOW_USER_ACCESS_PRIMARY;
    }

    @Override
    public List<UniqueKey<WorkflowUserAccessRecord>> getKeys() {
        return Arrays.<UniqueKey<WorkflowUserAccessRecord>>asList(Keys.KEY_WORKFLOW_USER_ACCESS_PRIMARY);
    }

    @Override
    public List<ForeignKey<WorkflowUserAccessRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<WorkflowUserAccessRecord, ?>>asList(Keys.WORKFLOW_USER_ACCESS_IBFK_1, Keys.WORKFLOW_USER_ACCESS_IBFK_2);
    }

    public User user() {
        return new User(this, Keys.WORKFLOW_USER_ACCESS_IBFK_1);
    }

    public Workflow workflow() {
        return new Workflow(this, Keys.WORKFLOW_USER_ACCESS_IBFK_2);
    }

    @Override
    public WorkflowUserAccess as(String alias) {
        return new WorkflowUserAccess(DSL.name(alias), this);
    }

    @Override
    public WorkflowUserAccess as(Name alias) {
        return new WorkflowUserAccess(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public WorkflowUserAccess rename(String name) {
        return new WorkflowUserAccess(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public WorkflowUserAccess rename(Name name) {
        return new WorkflowUserAccess(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<UInteger, UInteger, WorkflowUserAccessPrivilege> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
