package model.factory;

import math.Vector2;
import model.IModel;

import java.awt.*;
import java.io.File;

public interface ModelFactory {
    IModel createModel(double m, double width, double height, Vector2 position, Color color, File file);
}
