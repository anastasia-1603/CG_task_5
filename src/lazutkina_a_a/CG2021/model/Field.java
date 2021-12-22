package lazutkina_a_a.CG2021.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import lazutkina_a_a.CG2021.math.Rectangle;
import lazutkina_a_a.CG2021.utils2d.ScreenConverter;
import lazutkina_a_a.CG2021.utils2d.ScreenPoint;

import java.awt.*;

/**
 *
 * Класс, описывающий прямоугольное поле.
 * @author Alexey
 */
public class Field implements IDrawer{
    private Rectangle rectangle;
    private double mu, g;

    /**
     * Создаёт игровое поле
     * @param rectangle Прямоугольник поля
     * @param mu Коэффициент трения
     * @param g Ускорение свободного падения
     */
    public Field(Rectangle rectangle, double mu, double g) {
        this.rectangle = rectangle;
        this.mu = mu;
        this.g = g;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getMu() {
        return mu;
    }

    public void setMu(double mu) {
        this.mu = mu;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public void draw(Graphics2D g, ScreenConverter sc) {
        ScreenPoint tl = sc.r2s(this.getRectangle().getTopLeft());
        int w = sc.r2sDistanceH(this.getRectangle().getWidth());
        int h = sc.r2sDistanceV(this.getRectangle().getHeight());
        g.setColor(Color.WHITE);
        g.fillRect(tl.getI(), tl.getJ(), w, h);
        g.setColor(Color.RED);
        g.drawRect(tl.getI(), tl.getJ(), w, h);
    }
}
