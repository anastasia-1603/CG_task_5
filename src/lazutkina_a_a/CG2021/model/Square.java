package lazutkina_a_a.CG2021.model;

import lazutkina_a_a.CG2021.math.Vector2;
import lazutkina_a_a.CG2021.utils2d.ScreenConverter;
import lazutkina_a_a.CG2021.utils2d.ScreenPoint;

import java.awt.*;

public class Square extends IModel {

    public Square(double m, double width, double height, Vector2 position) {
        super(m, width, height, position);
    }

    @Override
    public void draw(Graphics2D g, ScreenConverter sc) {
        ScreenPoint tl = sc.r2s(new Vector2(this.getPosition().getX() - this.getWidth()/2,
                this.getPosition().getY() - this.getHeight()/2));
        int w = sc.r2sDistanceH(this.getWidth());
        int h = sc.r2sDistanceV(this.getHeight());
        g.setColor(Color.WHITE);
        g.fillRect(tl.getI(), tl.getJ(), w, h);
        g.setColor(Color.BLUE);
        g.drawRect(tl.getI(), tl.getJ(), w, h);
    }
}
