package lazutkina_a_a.CG2021.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lazutkina_a_a.CG2021.math.Vector2;
import lazutkina_a_a.CG2021.utils2d.ScreenConverter;
import lazutkina_a_a.CG2021.utils2d.ScreenPoint;

import java.awt.*;

/**
 *
 * Класс, описывающий шайбу.
 * @author Alexey
 */
public class Puck extends IModel  {
    private double m, r;
    private double width;
    private double height;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    /*public Puck(double m, double width, double height, Vector2 position) {
        super(m, width, height, position);

        this.velocity = new Vector2(0, 0);
        this.acceleration = new Vector2(0, 0);
    }*/

    /*/**
     * Создаём шайбу с нулевой скоростью и ускорением
     * @param m Масса шайбы [кг]
     * @param r Радиус шайбы [м]
     * @param position Положение шайбы относительно начала координат [м]
     */
     /*public Puck(double m, double r, Vector2 position) {
        this.m = m;
        this.r = r;
        this.width = 2 * r;
        this.height = 2 * r;
        this.position = position;
        this.velocity = new Vector2(0, 0);
        this.acceleration = new Vector2(0, 0);
    }*/



    public Puck(double m, double width, double height, Vector2 position) {
        super(m, width, height, position);
        this.m = m;
        this.width = width;
        this.height = height;

        this.position = position;
        this.velocity = new Vector2(0, 0);
        this.acceleration = new Vector2(0, 0);
    }

    /*public Puck() {
        super(1, 0.6, 0.6, new Vector2(5, 5));
    }*/

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }

    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public void draw(Graphics2D g, ScreenConverter sc) {
        Color oldC = g.getColor();
        ScreenPoint pc = sc.r2s(this.getPosition());
        int rh = sc.r2sDistanceH(width/2);
        int rv = sc.r2sDistanceV(height/2);
        g.setColor(Color.BLACK);
        g.fillOval(pc.getI() - rh, pc.getJ() - rv, rh + rh, rv + rv);
        g.setColor(oldC);
    }
}
