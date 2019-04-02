package com.feteforraine;

import java.util.Arrays;

// A player is not eliminated if its AutoTamponneuse is blinking !
public class PisteAutoTamponneuse {
    private Autotamponneuse autos [];
    private int turn;

    // Decrease the bound if you want to quickly converge.
    // Otherwise, increase it !
    private static double BOUND = 1000.d;

    public PisteAutoTamponneuse(int size) {
        autos = new Autotamponneuse[size];

        // Let's initialize all Autotamponneuses !
        for (int i = 0; i < autos.length; ++i) {
            autos[i] = new Autotamponneuse();
        }

        turn = 0;
    }

    public void initialize() {
        double posX, posY;

        int i = 0, j;
        while (i < this.autos.length) {
            /* Math.random() yields [0.0, 1.0[ (see javadoc) */
            posX = Math.random() * BOUND;
            posY = Math.random() * BOUND;

            Autotamponneuse current = this.autos[i];

            current.place(posX, posY);
            // Check if autos[i] does not collide with previous autos.
            for (j = 0; j < i; ++j) {
                if (current.collision(this.autos[j])) break;
            }
            if (j == i) ++i; // All autos checked, place the next one

            current.ajouteOccupant("Player" + i);
            current.allume();
            current.demarreClignotement();
        }

        turn = 0;
    }

    // return false if the game is finished
    public boolean doTurn() {
        double posX, posY;

        // Random move of the autos
        for (Autotamponneuse a : this.autos) {
            if (! a.estClignotante()) continue; // Go to next iteration

            posX = Math.random() * BOUND;
            posY = Math.random() * BOUND;

            a.place(posX, posY);
        }

        // Check collisions
        int i, j;
        for (i = 0; i < this.autos.length; ++i) {
            if (! autos[i].estClignotante()) continue; // already eliminated

            for (j = 0; j < i; ++j) {
                if (autos[i].collision(autos[j])) {
                    autos[i].arreteClignotement();
                    autos[j].arreteClignotement();
                    System.out.println("Collision entre "
                            + autos[i].getNomOccupant() + " et "
                            + autos[j].getNomOccupant());
                }

            }
        }

        int counter = 0;
        for (Autotamponneuse a : this.autos) {
            if (a.estClignotante()) ++counter;
        }

        ++turn;

        // At most one auto left ? If true, the game is finished !
        return (counter > 1);
    }

    public String result () {
        StringBuilder sb = new StringBuilder("Liste des vainqueurs au tour ");
        sb.append(turn);
        sb.append(" : \n");
        for (Autotamponneuse a : this.autos) {
            if (! a.estClignotante()) continue;
            sb.append("- ").append(a.getNomOccupant()).append('\n');
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Autotamponneuse a: this.autos) {
            // append return a reference to the initial String Builder.
            // We can then chain append !
            sb.append(a).append('\n');
        }
        return sb.toString();
    }

    // Another possible toString() using method toString() of Arrays
    public String toString2() {
        return Arrays.toString(this.autos);
    }

    public static void main(String args []) {
        PisteAutoTamponneuse p = new PisteAutoTamponneuse(10);
        System.out.println(p);
        p.initialize();
        System.out.println(p);

        while(p.doTurn()) { }

        System.out.println(p.result());
    }
}
