package lazutkina_a_a.CG2021;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.List;

import lazutkina_a_a.CG2021.math.Vector2;
import lazutkina_a_a.CG2021.model.*;
import lazutkina_a_a.CG2021.timers.AbstractWorldTimer;
import javax.swing.JPanel;
import javax.swing.Timer;
import lazutkina_a_a.CG2021.math.Rectangle;
import lazutkina_a_a.CG2021.timers.UpdateWorldTimer;
import lazutkina_a_a.CG2021.utils2d.ScreenConverter;
import lazutkina_a_a.CG2021.utils2d.ScreenPoint;

/**
 *
 * @author Alexey
 */
public class DrawPanel extends JPanel implements ActionListener,
        MouseListener, MouseMotionListener, MouseWheelListener {
    private ScreenConverter sc;
    private World w;
    private AbstractWorldTimer uwt;
    private Timer drawTimer;

    public DrawPanel() {
        super();
        Field f = new Field(
                new Rectangle(0, 10, 10, 10),
                0.1, 9.8);
        //w = new World(new Puck(1, 0.3, f.getRectangle().getCenter()), f);
        w = new World(f);
        w.addObject(new Puck(1, 0.6, 0.6, f.getRectangle().getCenter()));
        w.addObject(new Puck(1, 0.6, 0.6, new Vector2(0, 0)));
        w.addObject(new Square(1, 0.4, 0.4, new Vector2(1, 1)));
        sc = new ScreenConverter(f.getRectangle(), 450, 450);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);

        (uwt = new UpdateWorldTimer(w, 10)).start();
        drawTimer = new Timer(40, this);
        drawTimer.start();
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
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
