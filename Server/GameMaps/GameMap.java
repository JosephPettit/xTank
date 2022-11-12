package GameMaps;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public abstract class GameMap extends JComponent {
    public static final int GAME_WIDTH = 1280;
    public static final int GAME_HEIGHT = 720;

    private Color color;
    protected ArrayList<Rectangle2D> walls;

    GameMap(Color color) {
        walls = new ArrayList<Rectangle2D>();
        this.color = color;
    }

    @Override
    public synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        g2d.setColor(color);

        for (Rectangle2D obstacle : walls) {
            g2d.fill(obstacle);
        }
    }

    public synchronized ArrayList<Rectangle2D> getWalls() {
        return walls;
    }
}
