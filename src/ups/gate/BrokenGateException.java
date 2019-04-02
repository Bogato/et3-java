package ups.gate;

public class BrokenGateException extends CycleException implements ICycle {
    private final int maxCycle;
    public int currentCycle;

    // Called for all constructors
    {
        currentCycle = 0;
    }

    public BrokenGateException(int maxCycle) {
        this.maxCycle = maxCycle;
    }

    public BrokenGateException(int maxCycle, String msg) {
        super(msg);
        this.maxCycle = maxCycle;
    }

    @Override
    public void onIncr() throws BrokenGateException {
        if (currentCycle < ++currentCycle) return;

        throw this;
    }
}
