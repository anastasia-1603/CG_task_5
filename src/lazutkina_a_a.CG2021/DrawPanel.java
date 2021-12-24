import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import math.Vector2;

import model.Field;
import model.IModel;
import model.World;
import model.factory.ModelFactory;
import model.factory.OvalFactory;
import timers.AbstractWorldTimer;

import javax.swing.JPanel;
import javax.swing.Timer;

import math.Rectangle;
import timers.UpdateWorldTimer;
import utils2d.ScreenConverter;

public class DrawPanel extends JPanel implements ActionListener,
        MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
    private ScreenConverter sc;
    private World w;
    private AbstractWorldTimer uwt;
    private Timer drawTimer;
    private ModelFactory factory;

    public DrawPanel() {
        super();
        Field f = new Field(
                new Rectangle(0, 10, 10, 10),
                0.1, 9.8);
        w = new World(f);

        this.factory = new OvalFactory();
        addObject(1, 0.6, 0.6, f.getRectangle().getCenter(), Color.ORANGE, null);
        sc = new ScreenConverter(f.getRectangle(), this.getWidth(), this.getHeight());
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
        this.addKeyListener(this);

        (uwt = new UpdateWorldTimer(w, 10)).start();
        drawTimer = new Timer(40, this);
        drawTimer.start();
    }

    public void addObject(double m, double width, double height, Vector2 position, Color color, File file) {
        w.addObject(factory.createModel(m, width, height, position, color, file));
    }

    public void clearObjects() {
        w.getObjects().clear();
    }

    public ModelFactory getFactory() {
        return factory;
    }

    public void setFactory(ModelFactory factory) {
        this.factory = factory;
    }

    public void setG(double g) {
        this.w.getField().setG(g);
    }

    public void setMu(double mu) {
        this.w.getField().setMu(mu);
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        sc.setWs(width);
        sc.setHs(height);
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        this.setFocusable(true);
        Graphics2D g2 = bi.createGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        w.draw((Graphics2D) bi.getGraphics(), sc);
        for (IModel m: w.getObjects()) {
            animate(m);
        }
        g.drawImage(bi, 0, 0, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        /*int direction = 0;
        if (e.getButton() == MouseEvent.BUTTON1)
            direction = 1;
        else if (e.getButton() == MouseEvent.BUTTON3)
            direction = -1;
        w.getExternalForce().setValue(10*direction);*/
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //w.getExternalForce().setValue(0);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //w.getExternalForce().setLocation(sc.s2r(new ScreenPoint(e.getX(), e.getY())));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //w.getExternalForce().setLocation(sc.s2r(new ScreenPoint(e.getX(), e.getY())));
    }

    private static final double SCALE_STEP = 0.1;

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        int clicks = e.getWheelRotation();
        double scale = 1;
        double coef = 1 + SCALE_STEP * (clicks < 0 ? -1 : 1);
        for (int i = Math.abs(clicks); i > 0; i--) {
            scale *= coef;
        }

        sc.changeScale(scale);
    }

    private static final double STEP = 0.01;

    public void animate(IModel object) {

        if (isLeft) object.setPosition(object.getPosition().add(new Vector2(-STEP, 0)));
        if (isRight) object.setPosition(object.getPosition().add(new Vector2(STEP, 0)));
        if (isUp) object.setPosition(object.getPosition().add(new Vector2(0, STEP)));
        if (isDown) object.setPosition(object.getPosition().add(new Vector2(0, -STEP)));
        this.repaint();
    }

    private boolean isLeft = false;
    private boolean isRight = false;
    private boolean isUp = false;
    private boolean isDown = false;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void onKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) isLeft = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) isRight = true;
        if (e.getKeyCode() == KeyEvent.VK_UP) isUp = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) isDown = true;
    }

    public void onKeyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) isLeft = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) isRight = false;
        if (e.getKeyCode() == KeyEvent.VK_UP) isUp = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) isDown = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        onKeyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        onKeyReleased(e);
    }
}
