package model.shapes;

import math.Vector2;
import model.IModel;
import utils2d.ScreenConverter;
import utils2d.ScreenPoint;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image extends IModel {

    File image;

    public Image(double m, double width, double height, Vector2 position, File image) {
        super(m, width, height, position);
        this.image = image;
    }

    @Override
    public void draw(Graphics2D g, ScreenConverter sc) {
        try {
            BufferedImage img = ImageIO.read(image);
            ScreenPoint pc = sc.r2s(new Vector2(this.getPosition().getX() - this.getWidth()/2,
                    this.getPosition().getY() + this.getHeight()/2));
            int w = sc.r2sDistanceH(this.getWidth());
            int h = sc.r2sDistanceV(this.getHeight());
            java.awt.Image newImg = img.getScaledInstance(w, h,
                    java.awt.Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(newImg);
            g.drawImage(imageIcon.getImage(), pc.getI() , pc.getJ(), null);
        } catch (IOException ignored) {
        }
    }

    protected ImageIcon createImageIcon(String path,
                                        String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
