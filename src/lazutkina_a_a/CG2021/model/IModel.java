package lazutkina_a_a.CG2021.model;

import lazutkina_a_a.CG2021.math.Vector2;
import lazutkina_a_a.CG2021.utils2d.ScreenConverter;

import java.awt.*;

public abstract class IModel implements IDrawer{
    double m, r;
    Vector2 position;
    private double width;
    private double height;
    Vector2 velocity;
    Vector2 acceleration;

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

    @Override
    public void draw(Graphics2D g, ScreenConverter sc) {

    }
}
