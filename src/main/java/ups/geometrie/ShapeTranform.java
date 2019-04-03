package ups.geometrie;

import java.awt.*;

public interface ShapeTranform {
    void translate(int dx, int dy);
    void translate(Point p);

    void homothety(double delta);
}
