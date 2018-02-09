package rcc.mc;

public class Response {

    public enum ResponseState {
        CONSISTENT,
        INCONSISTENT,
        FAIL
    }

    final String output;
    final ResponseState state;

    public Response(String output, ResponseState state) {
        this.output = output;
        this. state = state;
    }

    public String getOutput() {
        return output;
    }

    public ResponseState getState() {
        return state;
    }
}
