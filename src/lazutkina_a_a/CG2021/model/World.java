package lazutkina_a_a.CG2021.model;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import lazutkina_a_a.CG2021.math.Vector2;
import lazutkina_a_a.CG2021.utils2d.ScreenConverter;
import lazutkina_a_a.CG2021.utils2d.ScreenPoint;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * Класс, описывающий весь мир, в целом.
 * @author Alexey
 */
public class World {

    private List<IModel> objects;
    private Puck p;
    private Field field;
    private ForceSource externalForce;

    public World(Puck p, Field field) {
        this.p = p;
        this.field = field;
        this.externalForce = new ForceSource(field.getRectangle().getCenter());
    }

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
        for(IModel obj : objects)
        {
            update(dt, obj);
        }
    }

    public void update(double dt, IModel object) {
        Vector2 np = object.getPosition()
                .add(object.getVelocity().mul(dt))
                .add(object.getAcceleration().mul(dt*dt*0.5));
        Vector2 nv = object.getVelocity()
                .add(object.getAcceleration().mul(dt));

        double vx = nv.getX(), vy = nv.getY();
        boolean reset = false;
        if (np.getX() - object.getR() < field.getRectangle().getLeft() ||
                np.getX() + object.getR() > field.getRectangle().getRight()) {
            vx = -vx;
            reset = true;
        }
        if (np.getY() - object.getR() < field.getRectangle().getBottom() ||
                np.getY() + object.getR() > field.getRectangle().getTop()) {
            vy = -vy;
            reset = true;
        }
        nv = new Vector2(vx, vy);
        if (nv.length() < 1e-10)
            nv = new Vector2(0, 0);
        if (reset)
            np = object.getPosition();

        Vector2 Fvn = externalForce.getForceAt(np);
        Vector2 Ftr = object.getVelocity().normolized().mul(-field.getMu()*object.getM()* field.getG());
        Vector2 F = Ftr.add(Fvn);

        object.setAcceleration(F.mul(1/object.getM()));
        object.setVelocity(nv);
        object.setPosition(np);
    }

    /**
     * Метод обновления состояния мира за указанное время
     * @param dt Промежуток времени, за который требуется обновить мир.
     */
    public void update(double dt) {
        Vector2 np = p.getPosition()
                .add(p.getVelocity().mul(dt))
                .add(p.getAcceleration().mul(dt*dt*0.5));
        Vector2 nv = p.getVelocity()
                .add(p.getAcceleration().mul(dt));

        double vx = nv.getX(), vy = nv.getY();
        boolean reset = false;
        if (np.getX() - p.getR() < field.getRectangle().getLeft() || np.getX() + p.getR() > field.getRectangle().getRight()) {
            vx = -vx;
            reset = true;
        }
        if (np.getY() - p.getR() < field.getRectangle().getBottom() || np.getY() + p.getR() > field.getRectangle().getTop()) {
            vy = -vy;
            reset = true;
        }
        nv = new Vector2(vx, vy);
        if (nv.length() < 1e-10)
            nv = new Vector2(0, 0);
        if (reset)
            np = p.getPosition();

        Vector2 Fvn = externalForce.getForceAt(np);
        Vector2 Ftr = p.getVelocity().normolized().mul(-field.getMu()*p.getM()* field.getG());
        Vector2 F = Ftr.add(Fvn);

        p.setAcceleration(F.mul(1/p.getM()));
        p.setVelocity(nv);
        p.setPosition(np);
    }

    /**
     * Метод рисует ткущее состояние мира.
     * На самом деле всю логику рисования стоит вынести из этого класса
     * куда-нибудь в WroldDrawer, унаследованный от IDrawer
     * @param g Графикс, на котором надо нарисовать текущее состояние.
     * @param sc Актуальный конвертер координат.
     */
    public void draw(Graphics2D g, ScreenConverter sc) {
        field.draw(g, sc);
        if (this.getObjects().size() != 0)
        {
            for (IModel obj : this.getObjects())
            {
                obj.draw(g, sc);
            }
        }
        else
        {
            System.out.println("Нет объектов для отрисовки"); //todo переделать чтобы отображалось на окне
        }
        g.drawString(String.format("Mu=%.2f", field.getMu()), 10, 30);
        g.drawString(String.format("F=%.0f", externalForce.getValue()), 10, 50);

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

    public Puck getP() {
        return p;
    }

    public void setP(Puck p) {
        this.p = p;
    }

    public ForceSource getExternalForce() {
        return externalForce;
    }


}