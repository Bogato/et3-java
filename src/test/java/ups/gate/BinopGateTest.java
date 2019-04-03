package ups.gate;

import org.junit.Test;

import static org.junit.Assert.*;

public abstract class BinopGateTest {

    private class CycleException extends GateException implements ICycle {
        private final int maxCycle;
        private int currentCycle;

        // Called for all constructors
        {
            currentCycle = 0;
        }

        CycleException(int maxCycle) {
            this.maxCycle = maxCycle;
        }

        int getCycle() {
            return currentCycle;
        }

        @Override
        public void onCycle() throws GateException {
            if (maxCycle > ++currentCycle) return;

            throw this;
        }
    }

    public abstract boolean output(boolean left, boolean right);
    public abstract BinopGate newInstance();

    @Test
    public void update() throws GateException {
        BinopGate g = newInstance();

        final boolean [] bools = {true, false};

        for (boolean left : bools) {
            for (boolean right : bools) {
                g.setInput(left, right);
                boolean truth = output(left, right);
                assertEquals(truth, g.update());
            }
        }
    }

    @Test
    public void incrementCycle() {
        int maxCycle = 42;

        BinopGate g = newInstance();
        CycleException exn = new CycleException(maxCycle);
        GateException exnGate = null;

        g.addCycleListener(exn);

        try {
            for (int i = 0; i < maxCycle; ++i) {
                g.incrementCycle();
            }
        } catch (GateException e) {
            exnGate = e;
        }

        assertSame(exn, exnGate);
        assertEquals(maxCycle, exn.getCycle());
    }
}