package ups.geometrie;

import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String [] args) {
        Circle c = new Circle();
        System.out.println("BEFORE" + c);
        Point p1 = c.getCenter();
        p1.x = 1;
        System.out.println("AFTER getCenter()" + c);
        Point p2 = c.getCenterRef();
        p2.x = 1;
        System.out.println("AFTER getCenterRef()" + c);



        SpecializedShapeList<Circle> circles = new SpecializedShapeList<>();
        circles.add(new Circle());
        circles.add(new Circle());
        circles.add(new Circle());

        ArrayList<ShapeTranform> tranforms = new ArrayList<>();
        tranforms.add(circles);

        for (ShapeTranform s : tranforms) {
            s.homothety(10);
        }
    }
}
