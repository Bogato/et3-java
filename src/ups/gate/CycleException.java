package ups.gate;

public abstract class CycleException extends Exception {
    public CycleException() {
        super();
    }

    public CycleException(String msg) {
        super(msg);
    }
}
