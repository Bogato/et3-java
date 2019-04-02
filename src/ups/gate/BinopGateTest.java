package ups.gate;

import org.junit.Test;

import static org.junit.Assert.*;

public abstract class BinopGateTest {

    public abstract boolean output(boolean left, boolean right);
    public abstract BinopGate newInstance();

    @Test
    public void update() throws CycleException {
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
}