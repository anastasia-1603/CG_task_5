package model;

import math.Vector2;

import java.awt.*;
import java.util.Calendar;

public abstract class IModel implements IDrawer{
    private double m;
    private Vector2 position;
    private double width;
    private double height;
    private Vector2 velocity;
    private Vector2 acceleration;
    private Color color;

    protected IModel(double m, double width, double height, Vector2 position, Color color) {
        this.m = m;
        this.width = width;
        this.height = height;
        this.position = position;
        this.color = color;

        this.velocity = new Vector2(0, 0);
        this.acceleration = new Vector2(0, 0);
    }

    protected IModel(double m, double width, double height, Vector2 position) {
        this(m, width, height, position, Color.BLACK);
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

    public Vector2 getVelocity() {
        return velocity;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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

}
