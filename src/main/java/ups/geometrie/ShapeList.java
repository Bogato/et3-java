package ups.geometrie;

import java.awt.Point;
import java.util.ArrayList;

public class ShapeList extends ArrayList<AbstractShape> implements ShapeTranform {

    public void translate(int dx, int dy) {
        for (AbstractShape s : this)
            s.translate(dx, dy);
    }

    public void translate(Point p) {
        translate(p.x, p.y);
    }

    public void homothety(double delta) {
        for (AbstractShape s : this)
            s.homothety(delta);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (AbstractShape s : this)
            sb.append(s);
        return sb.toString();
    }
}
