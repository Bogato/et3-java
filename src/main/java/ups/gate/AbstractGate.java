package ups.gate;

public abstract class AbstractGate {
    private ICycle listener;

    protected void incrementCycle () throws GateException {
        if (listener == null) return;
        listener.onCycle();
    }

    /**
     * Add a ICycle listener called on each cycle.
     * @param listener the ICycle listener
     */
    public void addCycleListener(ICycle listener) {
        this.listener = listener;
    }
}
