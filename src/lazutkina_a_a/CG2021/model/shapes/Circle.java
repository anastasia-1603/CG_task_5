package lazutkina_a_a.CG2021.model.shapes;

import lazutkina_a_a.CG2021.math.Vector2;

public class Circle extends Oval {
   /* public Circle(double m, double width, double height, Vector2 position) {
        super(m, width, height, position);
    }*/

    public Circle(double m, double r, Vector2 position)
    {
        super(m, r*2, r*2, position);
    }
}