package ups.gate;

public abstract class Gate {
    private long cycle = 0;
    private ICycle listener;

    protected void incrementCycle () throws CycleException {
        if (listener == null) return;
        listener.onIncr();
    }

    public void addCycleListener(ICycle listener) {
        this.listener = listener;
    }
}
