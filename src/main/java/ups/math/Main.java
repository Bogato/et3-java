package ups.math;

import java.io.*;
import java.lang.reflect.Field;

public class Main {

    // Closeable is an interface which defines only the method
    // java.io.Closeable#close() throws IOException
    // Known implementing classes are FileOutputStream, ObjectOutputStream,
    // FileInputStream, ObjectInputStream, etc...
    public static void close(Closeable c) {
        if (c == null) return;
        try {
            c.close();
        } catch (IOException e) {
            System.err.println("Erreur lors de la fermeture de " + c);
        }
    }

    public static void serializeTest(Complexe c) {
        String file = "demo.bin";
        FileOutputStream fileOut = null;
        ObjectOutputStream objectOut = null;
        FileInputStream fileIn = null;
        ObjectInputStream objectIn = null;

        try {
            fileOut = new FileOutputStream(file);
            objectOut = new ObjectOutputStream(fileOut);

            fileIn = new FileInputStream(file);
            objectIn = new ObjectInputStream(fileIn);

            objectOut.writeObject(c);
            Complexe rc = (Complexe) objectIn.readObject();
            System.out.println("equals: " + rc.equals(c));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            close(objectOut);
            close(fileOut);
            close(objectIn);
            close(fileIn);
        }
    }

    public static void main(String [] args) {
        ComplexeMemoire cm = new ComplexeMemoire(10,10);

        Complexe c = cm // 10 + 10i
                // (10 + 10i) + (2 + 4i)
                .add(new Complexe(2, 4))
                // ((10 + 10i) + (2 + 4i)) * (7 + 5i)
                .mult(new Complexe(7,5));

        c = cm.add(c);

        System.out.println(cm.getHistory());

        serializeTest(cm);
        serializeTest(c);
    }
}
