package ups.geometrie;

import java.util.ArrayList;

public class Main {
    public static void main(String [] args) {
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
