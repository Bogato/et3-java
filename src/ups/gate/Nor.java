package ups.gate;

public class Nor extends BinopGate {

    public Nor() {
        super();
    }

    public Nor(boolean inputA, boolean inputB) {
        super(inputA, inputB);
    }

    @Override
    public boolean update() {
        return !(inLeft || inRight);
    }

    @Override
    public String toString() {
        return "Nor:  " + super.toString();
    }
}
