package ups.gate;

public class BrokenCycle extends CycleException implements ICycle {
    private final int maxCycle;
    public int currentCycle;

    // Called for all constructors
    {
        currentCycle = 0;
    }

    public BrokenCycle(int maxCycle) {
        this.maxCycle = maxCycle;
    }

    public BrokenCycle(int maxCycle, String msg) {
        super(msg);
        this.maxCycle = maxCycle;
    }

    @Override
    public void onIncr() throws BrokenCycle {
        if (currentCycle < ++currentCycle) return;

        throw this;
    }
}
