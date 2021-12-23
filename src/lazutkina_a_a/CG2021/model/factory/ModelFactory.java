package lazutkina_a_a.CG2021.model.factory;

import lazutkina_a_a.CG2021.math.Vector2;
import lazutkina_a_a.CG2021.model.IModel;

public interface ModelFactory {
    IModel createModel(double m, double width, double height, Vector2 position);
}
