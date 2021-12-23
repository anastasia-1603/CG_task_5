package model.factory;

import math.Vector2;
import model.IModel;

import java.awt.*;

public interface ModelFactory {
    IModel createModel(double m, double width, double height, Vector2 position, Color color);
}
