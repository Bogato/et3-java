package ups.gate;

public class Nand extends BinopGate {

    public Nand() {
        super();
    }

    public Nand(boolean inputA, boolean inputB) {
        super(inputA, inputB);
    }

    @Override
    public boolean update() {
        return !(inLeft && inRight);
    }

    @Override
    public String toString() {
        return "Nand: " + super.toString();
    }
}
