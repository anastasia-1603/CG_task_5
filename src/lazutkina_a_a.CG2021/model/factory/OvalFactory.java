package model.factory;

import math.Vector2;
import model.IModel;
import model.shapes.Oval;

public class OvalFactory implements ModelFactory{
    @Override
    public IModel createModel(double m, double width, double height, Vector2 position) {
        return new Oval(m, width, height, position);
    }
}
