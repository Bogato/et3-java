package ups.geometrie;

import java.awt.Point;
import java.awt.Color;

public final class Square extends Rectangle {

    public Square(Point center, Color color, double size) {
        super(center, color, size, size);
    }

    public Square(Point center, double size) {
        this(center, Color.GREEN, size);
    }

    private void setSide(double side) {
        super.setWidth(side);
        super.setHeight(side);
    }

    public void setWidth(double width) {
        setSide(width);
    }

    public void setHeight(double height) {
        setSide(height);
    }

    @Override
    public String toString() {
        return "[Cercle\n" +
                "\t[centre de gravité : " + center + "]\n" +
                "\t[longueur : " + height + "]\n" +
                "\t[couleur : " + color + "]\n" +
                ']';
    }
}
