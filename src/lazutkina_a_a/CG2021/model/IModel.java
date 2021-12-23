package lazutkina_a_a.CG2021.model;

import lazutkina_a_a.CG2021.math.Vector2;
import lazutkina_a_a.CG2021.utils2d.ScreenConverter;

import java.awt.*;

public class IModel implements IDrawer{
    private double m, r;
    private Vector2 position;
    private double width;
    private double height;
    private Vector2 velocity;
    private Vector2 acceleration;


    public IModel(double m, double width, double height, Vector2 position) {
        this.m = m;
        this.width = width;
        this.height = height;
        this.position = position;

        this.velocity = new Vector2(0, 0);
        this.acceleration = new Vector2(0, 0);
    }

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
