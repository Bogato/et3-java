package ups.gate;

/**
 * A logic binary operator
 */
public abstract class BinopGate extends Gate {
    protected boolean inLeft, inRight;
    protected boolean out;

    // set to true if one input is modified.
    // set to false when output computation is done.
    private boolean modified;

    /**
     * Compute the output with respect to the outputs
     * @return output
     */
    public abstract boolean update();

    public BinopGate() {
        this(false, false);
    }

    public BinopGate(boolean inLeft, boolean inRight) {
        this.inLeft = inLeft;
        this.inRight = inLeft;
        modified = true;
    }

    public void setInputLeft(boolean inLeft) throws CycleException {
        this.inLeft = inLeft;
        modified = true;
        incrementCycle();
    }

    public void setInputRight(boolean inRight) throws CycleException {
        this.inRight = inRight;
        modified = true;
        incrementCycle();
    }

    public void setInput(boolean inLeft, boolean inRight)
            throws CycleException {
        setInputLeft(inLeft);
        setInputRight(inRight);
    }

    private boolean memoOut() {
        if (modified) {
            out = update();
            modified = false;
        }
        return out;
    }

    public boolean getOutput() throws CycleException {
        incrementCycle();
        return memoOut();
    }

    @Override
    public String toString() {
        return "a=" + inLeft + "\t| b=" + inRight + "\t| q=" + memoOut();
    }
}
