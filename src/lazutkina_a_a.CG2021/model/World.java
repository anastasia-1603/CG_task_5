package model;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import math.Vector2;
import utils2d.ScreenConverter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * Класс, описывающий весь мир, в целом.
 * @author Alexey
 */
public class World implements IDrawer{

    private List<IModel> objects;
    private Field field;
    private ForceSource externalForce;

    public World(List<IModel> objects, Field field) {
        this.objects = objects;
        this.field = field;
        this.externalForce = new ForceSource(field.getRectangle().getCenter());
    }

    public World(Field field) {
        this.objects = new ArrayList<>();
        this.field = field;
        this.externalForce = new ForceSource(field.getRectangle().getCenter());
    }

    public void updateAll(double dt){
        for (IModel obj : objects)
        {
            update(dt, obj);
        }
    }

    /**
     * Метод обновления состояния модели в мире за указанное время
     * @param dt Промежуток времени, за который требуется обновить модель.
     */
    public void update(double dt, IModel object) {
        Vector2 np = object.getPosition()
                .add(object.getVelocity().mul(dt))
                .add(object.getAcceleration().mul(dt*dt*0.5));
        Vector2 nv = object.getVelocity()
                .add(object.getAcceleration().mul(dt));

        double vx = nv.getX(), vy = nv.getY();
        boolean reset = false;
        if (np.getX() - object.getWidth()/2 < field.getRectangle().getLeft() ||
                np.getX() + object.getWidth()/2 > field.getRectangle().getRight()) {
            vx = -vx;
            reset = true;
        }
        if (np.getY() - object.getHeight()/2 < field.getRectangle().getBottom() ||
                np.getY() + object.getHeight()/2 > field.getRectangle().getTop()) {
            vy = -vy;
            reset = true;
        }
        nv = new Vector2(vx, vy);
        if (nv.length() < 1e-10)
            nv = new Vector2(0, 0);
        if (reset)
            np = object.getPosition();

        Vector2 Fvn = externalForce.getForceAt(np);
        Vector2 Ftr = object.getVelocity().normalized().mul(-field.getMu()*object.getM()* field.getG());
        Vector2 F = Ftr.add(Fvn);

        object.setAcceleration(F.mul(1/object.getM()));
        object.setVelocity(nv);
        object.setPosition(np);
    }

    public void draw(Graphics2D g, ScreenConverter sc) {
        field.draw(g, sc);
        if (this.getObjects().size() != 0)
        {
            for (IModel obj : this.getObjects())
            {
                obj.draw(g, sc);
            }
        }
    }

    public void addObject(IModel object)
    {
        this.getObjects().add(object);
    }

    public void addObjects(List<IModel> objects)
    {
        this.getObjects().addAll(objects);
    }

    public List<IModel> getObjects() {
        return objects;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public ForceSource getExternalForce() {
        return externalForce;
    }
}