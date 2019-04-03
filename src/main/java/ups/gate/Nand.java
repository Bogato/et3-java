package ups.gate;

/** A binary Nand gate */
public class Nand extends BinopGate {

    public Nand(boolean inputA, boolean inputB) {
        super(inputA, inputB);
    }

    @Override
    protected boolean update() {
        return !(inLeft && inRight);
    }

    @Override
    public String toString() {
        return "Nand: " + super.toString();
    }
}
