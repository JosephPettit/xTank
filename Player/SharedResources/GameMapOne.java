package SharedResources;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JComponent;

import xTankClientGUI.ClientFrame;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.geom.Rectangle2D;

public class GameMapOne extends JComponent {

    static final int GAME_WIDTH = 1280;
    static final int GAME_HEIGHT = 720;
    private Rectangle2D.Double west;
    private Rectangle2D.Double east;
    private Rectangle2D.Double north;
    private Rectangle2D.Double south;
    private ArrayList<Rectangle2D> walls;

    public GameMapOne() {
        west = new Rectangle2D.Double(0, 0, 10, GAME_HEIGHT);
        east = new Rectangle2D.Double(GAME_WIDTH - 25, 0, 10, GAME_HEIGHT);
        north = new Rectangle2D.Double(0, 0, GAME_WIDTH, 10);
        south = new Rectangle2D.Double(0, GAME_HEIGHT - 100, GAME_WIDTH, 10);

        walls = new ArrayList<Rectangle2D>();
        walls.add(west);
        walls.add(east);
        walls.add(north);
        walls.add(south);
    }

    @Override
    public synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        g2d.setColor(Color.blue);
        g2d.fill(west);
        g2d.fill(east);
        g2d.fill(north);
        g2d.fill(south);
    }

    public ArrayList<Rectangle2D> getWalls() {
        return walls;
    }
}