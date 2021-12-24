package model.factory;

import math.Vector2;
import model.IModel;
import model.shapes.Image;

import java.awt.*;
import java.io.File;

public class ImageFactory implements ModelFactory {
    @Override
    public IModel createModel(double m, double width, double height, Vector2 position, Color color, File image) {
        return new Image(m, width, height, position, image);
    }
}
