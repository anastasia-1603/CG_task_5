package model.factory;

import math.Vector2;
import model.IModel;
import model.shapes.Oval;

import java.awt.*;

public class OvalFactory implements ModelFactory{
    @Override
    public IModel createModel(double m, double width, double height, Vector2 position, Color color) {
        return new Oval(m, width, height, position, color);
    }
}
