package model.shapes;

import math.Vector2;
import model.IModel;
import utils2d.ScreenConverter;
import utils2d.ScreenPoint;

import java.awt.*;

public class Square extends IModel {

    public Square(double m, double width, double height, Vector2 position, Color color) {
        super(m, width, height, position, color);
    }

    @Override
    public void draw(Graphics2D g, ScreenConverter sc) {
        ScreenPoint tl = sc.r2s(new Vector2(this.getPosition().getX() - this.getWidth()/2,
                this.getPosition().getY() + this.getHeight()/2));
        int w = sc.r2sDistanceH(this.getWidth());
        int h = sc.r2sDistanceV(this.getHeight());
        g.setColor(this.getColor());
        g.fillRect(tl.getI(), tl.getJ(), w, h);
    }
}
