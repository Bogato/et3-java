package ups.gate;

/** A binary Nor gate */
public class Nor extends BinopGate {

    public Nor(boolean inputA, boolean inputB) {
        super(inputA, inputB);
    }

    @Override
    protected boolean update() {
        return !(inLeft || inRight);
    }

    @Override
    public String toString() {
        return "Nor:  " + super.toString();
    }
}
