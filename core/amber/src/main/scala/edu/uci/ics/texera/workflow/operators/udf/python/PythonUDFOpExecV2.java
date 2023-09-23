package edu.uci.ics.texera.workflow.operators.udf.python;

import edu.uci.ics.amber.engine.architecture.worker.PauseManager;
import edu.uci.ics.amber.engine.common.InputExhausted;
import edu.uci.ics.amber.engine.common.rpc.AsyncRPCClient;
import edu.uci.ics.texera.workflow.common.operators.OperatorExecutor;
import edu.uci.ics.texera.workflow.common.tuple.Tuple;
import edu.uci.ics.texera.workflow.common.tuple.schema.Schema;
import scala.collection.Iterator;
import scala.util.Either;

public class PythonUDFOpExecV2 implements OperatorExecutor {


    private final String code;
    private final Schema outputSchema;

    public PythonUDFOpExecV2(String code, Schema outputSchema) {
        this.code = code;
        // TODO: support multiple output Schemas
        this.outputSchema = outputSchema;
    }

    @Override
    public void open() {
    }

    @Override
    public void close() {
    }


    @Override
    public Iterator<Tuple> processTexeraTuple(Either<Tuple, InputExhausted> tuple, int input, PauseManager pauseManager, AsyncRPCClient asyncRPCClient) {
        // Will not be used. The real implementation is in the Python UDF.
        return null;
    }

    public String getCode() {
        return code;
    }

    public Schema getOutputSchema() {
        return outputSchema;
    }
}
