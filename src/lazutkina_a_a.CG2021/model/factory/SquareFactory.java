package model.factory;

import math.Vector2;
import model.IModel;
import model.shapes.Square;

public class SquareFactory implements ModelFactory{
    @Override
    public IModel createModel(double m, double width, double height, Vector2 position) {
        return new Square(m, width, height, position);
    }
}
