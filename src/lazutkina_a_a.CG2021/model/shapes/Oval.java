package model.shapes;

import math.Vector2;
import model.IModel;
import utils2d.ScreenConverter;
import utils2d.ScreenPoint;

import java.awt.*;

public class Oval extends IModel {

    public Oval(double m, double width, double height, Vector2 position, Color color) {
        super(m, width, height, position, color);
    }

    @Override
    public void draw(Graphics2D g, ScreenConverter sc) {
        Color oldC = g.getColor();
        ScreenPoint pc = sc.r2s(this.getPosition());
        int rh = sc.r2sDistanceH(this.getWidth()/2);
        int rv = sc.r2sDistanceV(this.getHeight()/2);
        g.setColor(this.getColor());
        g.fillOval(pc.getI() - rh, pc.getJ() - rv, rh*2, rv*2);
        g.setColor(oldC);
    }
}
