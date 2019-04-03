package ups.gate;

public abstract class GateException extends Exception {
    public GateException() {
        super();
    }

    public GateException(String msg) {
        super(msg);
    }
}
