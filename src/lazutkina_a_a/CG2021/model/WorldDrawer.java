package lazutkina_a_a.CG2021.model;

import lazutkina_a_a.CG2021.utils2d.ScreenConverter;
import lazutkina_a_a.CG2021.utils2d.ScreenPoint;

import java.awt.*;

public class WorldDrawer implements IDrawer{

    World world;

    public WorldDrawer(World world) {
        this.world = world;
    }

    @Override
    public void draw(Graphics2D g, ScreenConverter sc) {
        for (IModel obj : world.getObjects())
        {
            obj.draw(g, sc);
        }

    }
}
