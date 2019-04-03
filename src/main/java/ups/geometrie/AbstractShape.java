package ups.geometrie;

import java.awt.*;

public abstract class AbstractShape implements ShapeTranform {
    protected Point center;
    protected Color color;

    public AbstractShape(Point center, Color color) {
        this.center = center;
        this.color = color;
    }

    public AbstractShape(Point center) {
        this(center, Color.BLUE);
    }

    public AbstractShape() {
        this(new Point());
    }

    public Point getCenter() {
        // Beware of returning a copy of center, instead of a reference !
        return new Point(center);
    }

    public void setCenter(Point center) {
        // Same as getCenter(), assign a copy of the point or else our point could be modified from outside !
        this.center = new Point(center);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void translate(int dx, int dy) {
        center = new Point(center.x + dx, center.y + dy);
    }

    public void translate(Point p) {
        translate(p.x, p.y);
    }

    public abstract void homothety(double delta);
}
