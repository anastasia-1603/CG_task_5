package lazutkina_a_a.CG2021.model.shapes;

import lazutkina_a_a.CG2021.math.Vector2;
import lazutkina_a_a.CG2021.model.IModel;
import lazutkina_a_a.CG2021.utils2d.ScreenConverter;
import lazutkina_a_a.CG2021.utils2d.ScreenPoint;

import java.awt.*;

public class Oval extends IModel {

    public Oval(double m, double width, double height, Vector2 position) {
        super(m, width, height, position);
    }

    @Override
    public void draw(Graphics2D g, ScreenConverter sc) {
        Color oldC = g.getColor();
        ScreenPoint pc = sc.r2s(this.getPosition());
        int rh = sc.r2sDistanceH(getWidth()/2);
        int rv = sc.r2sDistanceV(getHeight()/2);
        g.setColor(Color.BLACK);
        g.fillOval(pc.getI() - rh, pc.getJ() - rv, rh + rh, rv + rv);
        g.setColor(oldC);
    }
}
