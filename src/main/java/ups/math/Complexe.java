package ups.math;

import java.io.Serializable;

public class Complexe implements Serializable {

    // 'final' means that a field is immutable
    // (i.e. you can only assign it once in the constructor)
    private final int re, im;

    // 1.
    public Complexe(int re, int im) {
        this.re = re;
        this.im = im;
    }

    // 4.
    public Complexe(Complexe c) {
        // Primitive types (boolean, char, int, double, ...) are copied !
        this.re = c.re;
        this.im = c.im;
    }

    // 6.
    public double modulus() {
        return Math.sqrt(re * re + im * im);
    }

    public double argument() {
        return Math.acos(re / modulus());
    }

    // 7.
    public Complexe add(Complexe c) {
        int re = this.re + c.re;
        int im = this.im + this.im;
        return new Complexe(re, im);
    }

    public Complexe mult(Complexe c) {
        int re = this.re * c.re - this.im * c.im;
        int im = this.re * c.im - this.im * c.re;
        return new Complexe(re, im);
    }

    // 3.
    public int getRe() { return this.re; }
    public int getIm() { return this.im; }

    // 5.
    /**
     * @see com.feteforraine.Autotamponneuse#equals(Object) for more details
     * about how to safely override an equals method.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (o.getClass() != this.getClass()) return false;
        // We can safely cast to Complexe now !
        Complexe c = (Complexe) o;
        return (this.re == c.re && this.im == c.im);
    }

    // 2.
    @Override
    public String toString() {
        // You can use a StringBuilder for a better formatting
        return ("(" + re + " + " + im + "i)");
    }
}
