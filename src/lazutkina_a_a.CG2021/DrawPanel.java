import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import math.Vector2;

import model.Field;
import model.ModelType;
import model.World;
import model.factory.CreatorShapes;
import model.factory.ModelFactory;
import model.factory.OvalFactory;
import model.factory.SquareFactory;
import model.shapes.Puck;
import timers.AbstractWorldTimer;
import javax.swing.JPanel;
import javax.swing.Timer;
import math.Rectangle;
import timers.UpdateWorldTimer;
import utils2d.ScreenConverter;
import utils2d.ScreenPoint;

public class DrawPanel extends JPanel implements ActionListener,
        MouseListener, MouseMotionListener, MouseWheelListener {
    private ScreenConverter sc;
    private World w;
    private AbstractWorldTimer uwt;
    private Timer drawTimer;
    private ModelFactory factory;


    public DrawPanel(ModelFactory factory) {
        super();
        Field f = new Field(
                new Rectangle(0, 10, 10, 10),
                0.1, 9.8);
        w = new World(f);

        this.factory = factory;
        /*ModelFactory ovalFactory = CreatorShapes.createModel(ModelType.OVAL);*/
        w.addObject(factory.createModel(2, 0.6, 0.6,  f.getRectangle().getCenter()));
        w.addObject(new Puck(1, 0.3, new Vector2(1, 1)));
        w.addObject(new SquareFactory().createModel(1, 0.4, 0.4, new Vector2(1, 1)));
        sc = new ScreenConverter(f.getRectangle(), this.getWidth(), this.getHeight());
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);

        (uwt = new UpdateWorldTimer(w, 10)).start();
        drawTimer = new Timer(40, this);
        drawTimer.start();
    }

    public void addObject(double m, double width, double height, Vector2 position)
    {
        w.addObject(factory.createModel(m, width, height, position));
    }

    public ModelFactory getFactory() {
        return factory;
    }

    public void setFactory(ModelFactory factory) {
        this.factory = factory;
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
        Graphics2D g2 = bi.createGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        w.draw((Graphics2D)bi.getGraphics(), sc);
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
        int direction = 0;
        if (e.getButton() == MouseEvent.BUTTON1)
            direction = 1;
        else if (e.getButton() == MouseEvent.BUTTON3)
            direction = -1;
        w.getExternalForce().setValue(10*direction);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        w.getExternalForce().setValue(0);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        w.getExternalForce().setLocation(sc.s2r(new ScreenPoint(e.getX(), e.getY())));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        w.getExternalForce().setLocation(sc.s2r(new ScreenPoint(e.getX(), e.getY())));
    }

    private static final double SCALE_STEP = 0.1;
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
       /* double oldMu = w.getField().getMu();
        oldMu = Math.round(oldMu*100 + e.getWheelRotation())*0.01;

        if (oldMu < -1)
            oldMu = -1;
        else if (oldMu > 1)
            oldMu = 1;
        else if (Math.abs(oldMu) < 0.005)
            oldMu = 0;
        w.getField().setMu(oldMu);*/
        int clicks = e.getWheelRotation();
        double scale = 1;
        double coef = 1 + SCALE_STEP * (clicks < 0 ? -1 : 1);
        for (int i = Math.abs(clicks); i > 0; i--) {
            scale *= coef;
        }

        sc.changeScale(scale);
    }

}
