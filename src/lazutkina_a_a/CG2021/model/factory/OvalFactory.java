package lazutkina_a_a.CG2021.model.factory;

import lazutkina_a_a.CG2021.math.Vector2;
import lazutkina_a_a.CG2021.model.IModel;
import lazutkina_a_a.CG2021.model.shapes.Oval;

public class OvalFactory implements ModelFactory{
    @Override
    public IModel createModel(double m, double width, double height, Vector2 position) {
        return new Oval(m, width, height, position);
    }
}
