package ups.gate;

public class RS {
    private boolean r, s, q;
    private Nor nr, ns;
    private IFixit fixitListener;

    public RS () {
        this(false, false, false);
    }

    protected RS(boolean r, boolean s, boolean q) {
        this.r = r; this.s = s; this.q = q;
        nr = new Nor(r, !q);
        ns = new Nor(s, q);
    }

    public boolean getR() {
        return r;
    }

    public boolean getS() {
        return s;
    }

    public boolean getQ() {
        return q;
    }

    public boolean getNotQ() {
        return !q;
    }

    private void setNorInput(Nor nor, boolean inLeft, boolean inRight) throws BrokenRSException {
        try {
            nor.setInput(inLeft, inRight);
        } catch (GateException e) {
            if (fixitListener == null) {
                throw new BrokenRSException("Need to repair NOR gate: " + e.getMessage());
            }
            // If we have a listener registered, let's it fix this mess !
            fixitListener.onBrokenNor(nor);
        }
    }

    public void setR(boolean r) throws BrokenRSException {
        this.r = r;
        boolean oldQ = q;
        setNorInput(nr, r, !oldQ);
        setNorInput(ns, s, nr.getOutput());
        this.q = nr.getOutput();
    }

    public void setS(boolean s) throws BrokenRSException {
        this.s = s;
        boolean oldQ = q;
        setNorInput(ns, s, oldQ);
        setNorInput(nr, r, ns.getOutput());
        this.q = nr.getOutput();
    }

    public void setRS(boolean r, boolean s) throws BrokenRSException {
        setR(r);
        setS(s);
    }

    public void addCycleListenerR(ICycle listener) {
        nr.addCycleListener(listener);
    }

    public void addCycleListenerS(ICycle listener) {
        ns.addCycleListener(listener);
    }

    public void addFixitListener(IFixit listener) {
        this.fixitListener = listener;
    }

    @Override
    public String toString() {
        return "R=" + r + "\t| S=" + s  + "\t| Q=" + q + "\t| !Q=" + !q;
    }
}
