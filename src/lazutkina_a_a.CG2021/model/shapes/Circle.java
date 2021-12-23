package model.shapes;

import math.Vector2;

import java.awt.*;

public class Circle extends Oval {

    public Circle(double m, double r, Vector2 position, Color color)
    {
        super(m, r*2, r*2, position, color);
    }
}
