package lazutkina_a_a.CG2021.model.shapes;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lazutkina_a_a.CG2021.math.Vector2;
import lazutkina_a_a.CG2021.model.shapes.Circle;
import lazutkina_a_a.CG2021.utils2d.ScreenConverter;
import lazutkina_a_a.CG2021.utils2d.ScreenPoint;

import java.awt.*;

/**
 *
 * Класс, описывающий шайбу.
 * @author Alexey
 */
public class Puck extends Circle {
/*    private double m, r;
    private double width;
    private double height;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;*/

   /* *
     * Создаём шайбу с нулевой скоростью и ускорением
     * @param m Масса шайбы [кг]
     * @param width Ширина шайбы
     * @param height Длина шайбы
     * @param position Положение шайбы относительно начала координат [м]
*/    /*public Puck(double m, double width, double height, Vector2 position) {
        super(m, width, height, position);
    }*/

    /**
     * Создаём шайбу с нулевой скоростью и ускорением
     * @param m Масса шайбы [кг]
     * @param r Радиус шайбы
     * @param position Положение шайбы относительно начала координат [м]
     */
    public Puck(double m, double r, Vector2 position) {
        super(m, r, position);
    }

    @Override
    public void draw(Graphics2D g, ScreenConverter sc) {
        Color oldC = g.getColor();
        ScreenPoint pc = sc.r2s(this.getPosition());
        int rh = sc.r2sDistanceH(this.getWidth()/2);
        int rv = sc.r2sDistanceV(this.getHeight()/2);
        g.setColor(Color.BLACK);
        g.fillOval(pc.getI() - rh, pc.getJ() - rv, rh + rh, rv + rv);
        g.setColor(oldC);
    }
}
