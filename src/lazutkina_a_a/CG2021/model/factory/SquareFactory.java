package lazutkina_a_a.CG2021.model.factory;

import lazutkina_a_a.CG2021.math.Vector2;
import lazutkina_a_a.CG2021.model.IModel;
import lazutkina_a_a.CG2021.model.shapes.Square;

public class SquareFactory implements ModelFactory{
    @Override
    public IModel createModel(double m, double width, double height, Vector2 position) {
        return new Square(m, width, height, position);
    }
}
