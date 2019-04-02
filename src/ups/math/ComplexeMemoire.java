package ups.math;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComplexeMemoire extends Complexe implements Serializable {

    // 8.
    private ArrayList<String> history = new ArrayList<>();
    private static ArrayList<String> staticHistory = new ArrayList<>();

    public ComplexeMemoire(int re, int im) {
        super(re, im);
    }

    // 9.
    public ComplexeMemoire(ComplexeMemoire cmem) {
        super(cmem);
        // We call here the copy constructor of ArrayList<>.
        // If we had done `this.histo = cmem.history`, we would had copy of the
        // *reference* to cmem. Therefore, changes to `this.history`
        // would also affect `cmem.history`.
        history = new ArrayList<>(cmem.history);
    }

    // Private constructor from a Complexe !
    private ComplexeMemoire(Complexe c) {
        super(c);
    }

    // 11 - 13.
    private String opToString(Complexe other, String op, Complexe res) {
        return this + " " + op + " " + other + " = " + res;
    }

    private void addHistory (String entry) {
        history.add(entry);
        staticHistory.add(entry);
    }

    public ArrayList<String> getHistory () {
        return new ArrayList<>(history);
        // Copy otherwise the caller could modify the reference
    }

    public static ArrayList<String> getStaticHistory() {
        return new ArrayList<>(staticHistory);
    }

    // 12.
    @Override
    public Complexe add(Complexe other) {
        ComplexeMemoire res = new ComplexeMemoire(super.add(other));
        String entry = opToString(other, "+", res);
        addHistory(entry);
        if (this.getClass() == other.getClass() && this != other) {
            ((ComplexeMemoire) other).addHistory(entry);
        }
        staticHistory.add(entry);
        return res;
    }

    @Override
    public Complexe mult(Complexe other) {
        ComplexeMemoire res = new ComplexeMemoire(super.mult(other));
        String entry = opToString(other, "*", res);
        addHistory(entry);
        if (this.getClass() == other.getClass() && this != other) {
            ((ComplexeMemoire) other).addHistory(entry);
        }
        staticHistory.add(entry);
        return res;
    }
}
