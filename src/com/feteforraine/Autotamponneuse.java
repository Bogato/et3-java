package com.feteforraine;

public class Autotamponneuse {
    private boolean isOnTrack;
    private double posX, posY;

    private boolean isIn;
    private String name;

    private boolean isOn;
    private boolean isBlinking;

    // 3 .
    public Autotamponneuse(double x, double y) {
        isOnTrack = true;
        posX = x; posY = y;
        isIn = false; name = "";
        isOn = false;
        isBlinking = false;
    }

    public Autotamponneuse() {
        // Default constructor.
        // We reuse the previous constructor with default values for x and y.
        this(0.0d, 0.0d);
        isOnTrack = false;
    }

    // 4.
    public boolean estOccupee () {
        return this.isIn;
    }

    public String getNomOccupant() {
        return this.name;
    }

    public boolean estAllumee () {
        return this.isOn;
    }

    public boolean estClignotante () {
        return this.isBlinking;
    }

    // 5.
    public static void main(String[] args) {
        // TODO

        // Access to a field or a method of a null reference raise a
        // NullPointerException.

        // The backtrace is useful to trace the origin of an exception.
        // For example, the backtrace:
        // Exception in thread "main" java.lang.NullPointerException
        //     at ups.gate.Autotamponneuse.main(Main.java:46)
        //     at ups.gate.AutoTamponneuse.calculeDistance(Main.java:162)
        // means that a NullPointerException was raised in method
        // 'calculeDistance', the later being called by the 'main' method


        Autotamponneuse a1 = new Autotamponneuse();
        Autotamponneuse a2 = new Autotamponneuse();
        /* On peut tester aussi avec null pour voir si notre code passe
         * meme si quelqu'un nous donne une reference nulle.
         */
        // Autotamponneuse a2 = null;

        // Version objet
        a1.calculeDistance(a2);
        // Version imperative
        Autotamponneuse.calculeDistante(a1, a2);

    }

    // 6.
    private static int ID = 0; // Global counter
    private int id = ++ID; // Local counter

    // 7.
    @Override
    public String toString() { // String toString() is defined in Object
        if (!isOnTrack)
            return "[" + id + "]: auto non placée";

        return "[" + id + "] (" + posX + ", " + posY + ") "
                + (isIn ? "occupée (" + name + ") " : "libre ")
                + (isOn ? "allumée " : "eteinte ")
                + (isBlinking ? "" : "non ") + "clignotante";
    }

    // And the StringBuilder version of String toString()
    public String toString2() {
        StringBuilder sb = new StringBuilder();

        sb.append("[" + id + "]");

        if (!isOnTrack) sb.append("non placée.");
        else {
            sb.append ("(" + posX + ", " + posY + ") ");
            if (!isIn) sb.append ("libre.");
            else {
                sb.append ("occupée par " + name + " ");
                if (!isOn) sb.append ("et non allumée.");
                else {
                    sb.append ("allumée ");
                    if (!isBlinking) sb.append (" et non clignotante.");
                    else sb.append (" et clignotante.");
                }
            }
        }

        return sb.toString();
    }

    // 8.
    boolean place(double posX, double posY) {
        this.posX = posX; this.posY = posY;
        isOnTrack = true;
        return true;
    }

    boolean ajouteOccupant(String nom) {
        if (!isOnTrack || isIn) return false;
        name = nom;
        isIn = true;
        return true;
    }

    boolean enleveOccupant() {
        if (!isIn) return false;
        name = "";
        isIn = false;
        return true;
    }

    boolean allume() {
        if (!isIn) return false;
        isOn = true;
        return true;
    }

    boolean demarreClignotement() {
        if (!isOn) return false;
        isBlinking = true;
        return true;
    }

    boolean arreteClignotement() {
        if (!isBlinking) return false;
        isBlinking = false;
        return true;
    }


    // 9.
    private static final double DIST_MAX = Double.MAX_VALUE;
    private static final double EPSILON = 2.0d;


    public double calculeDistance(Autotamponneuse auto) {
        // Remember to check null pointers when manipulating auto, unless you
        // are *sure* that calculeDistance will never receive a null object as
        // a parameter. Better safe than sorry !
        if (auto == null) return DIST_MAX;
        // By definition, 'this' is *never* null !
        double dx = this.posX - auto.posX;
        double dy = this.posY - auto.posY;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public boolean collision(Autotamponneuse auto) {
        // Not necessary to check null pointeurs since we do not manipulate
        // auto in this function
        if (calculeDistance(auto) > EPSILON) return false;
        return true;
    }

    // 10.
    public static double calculeDistante(Autotamponneuse a1,
                                         Autotamponneuse a2) {
        if (a1 == null) return DIST_MAX;
        // Let's reuse code !
        return a1.calculeDistance(a2);
    }

    public static boolean collision(Autotamponneuse a1, Autotamponneuse a2) {
        if (a1 == null) return false;
        // Same here
        return a1.collision(a2);
    }

    // 11.
    @Override
    public boolean equals(Object o) {
        if (o == null) return false; // 'this' is *never* a null reference
        if (o == this) return true;  // same object
        // We need to check before downcasting o to Autotamponneuse that 'o'
        // and 'this' were created with the same type.
        // Downcasting is not safe, use it carefully !!!
        if (o.getClass() != this.getClass()) return false;
        Autotamponneuse auto = (Autotamponneuse)o;
        // We could have check all fields but since ids are
        // *supposed* to be unique, we will just check them to check if
        // two Autotamponneuses are equals !
        return this.id == auto.id;
    }
}
