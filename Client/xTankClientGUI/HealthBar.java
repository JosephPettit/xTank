package xTankClientGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public abstract class HealthBar extends JComponent {
    public static final int GAME_WIDTH = 1280;
    public static final int GAME_HEIGHT = 720;

    private Color color;
    protected ArrayList<Rectangle2D> healthBars;

    HealthBar(Color color) {
        healthBars = new ArrayList<Rectangle2D>();
        this.color = color;

        // Add Bars Here
        Rectangle2D p1Bar = new Rectangle2D.Double(0,100,20,20);
        healthBars.add(p1Bar);
    }

    @Override
    public synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        g2d.setColor(color);

        for (Rectangle2D obstacle : healthBars) {
            g2d.fill(obstacle);
        }
    }

    public synchronized ArrayList<Rectangle2D> getHealthBars() {
        return healthBars;
    }
}
