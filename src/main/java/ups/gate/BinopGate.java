package ups.gate;

/**
 * An abstract logic binary operator
 */
public abstract class BinopGate extends AbstractGate {
    protected boolean inLeft, inRight;
    private boolean out;

    // set to true if one input is modified.
    // set to false when output computation is done.
    private boolean modified;

    /**
     * Compute the output with respect to the outputs
     * @return output
     */
    protected abstract boolean update();

    public BinopGate(boolean inLeft, boolean inRight) {
        this.inLeft = inLeft;
        this.inRight = inLeft;
        modified = true;
    }

    public void setInputLeft(boolean inLeft) throws GateException {
        this.inLeft = inLeft;
        modified = true;
        incrementCycle();
    }

    public void setInputRight(boolean inRight) throws GateException {
        this.inRight = inRight;
        modified = true;
        incrementCycle();
    }

    public void setInput(boolean inLeft, boolean inRight)
            throws GateException {
        setInputLeft(inLeft);
        setInputRight(inRight);
    }

    private boolean memoOut() {
        if (modified) {
            out = update(); // update the output if one or more inputs have been modified
            modified = false;
        }
        return out;
    }

    public boolean getOutput() {
        return memoOut();
    }

    @Override
    public String toString() {
        return "a=" + inLeft + "\t| b=" + inRight + "\t| q=" + memoOut();
    }
}
