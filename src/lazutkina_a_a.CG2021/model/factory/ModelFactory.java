package model.factory;

import math.Vector2;
import model.IModel;

public interface ModelFactory {
    IModel createModel(double m, double width, double height, Vector2 position);
}
