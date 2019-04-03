package ups.gate;

public class NorTest extends BinopGateTest {

    @Override
    public BinopGate newInstance() {
        return new Nand(true, true);
    }

    @Override
    public boolean output(boolean left, boolean right) {
        return !(left && right);
    }
}