package model.factory;

import math.Vector2;
import model.IModel;
import model.shapes.Square;

import java.awt.*;
import java.io.File;

public class SquareFactory implements ModelFactory{
    @Override
    public IModel createModel(double m, double width, double height, Vector2 position, Color color, File file) {
        return new Square(m, width, height, position, color);
    }
}
