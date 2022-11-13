package xTankClientGUI;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;

/**
 * Displays the Player Health Area
 */
public class HealthBar extends JComponent {

    private String display;
    private int x, y;
    private String healthMessage;
    private Color heartColor;

    public HealthBar(int player, int health, int x, int y) {
        healthMessage = "";
        player++;
        switch (health) {
            case 3:
                healthMessage = "\u2764\u2764\u2764";
                heartColor = (Color.GREEN);
                break;
            case 2:
                healthMessage = "\u2764\u2764";
                heartColor = (Color.YELLOW);
                break;
            case 1:
                healthMessage = "\u2764";
                heartColor = (Color.RED);
                break;
            case 0:
                healthMessage = "\u2620";
                heartColor = (Color.WHITE);
                break;
            default:
                healthMessage = "\u2620";
                heartColor = (Color.WHITE);
                break;

        }
        display = String.format("Player %d Health: ", player);
        this.x = x;
        this.y = y;
    }

    @Override
    public synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        g2d.setColor(Color.WHITE);
        g2d.drawString(display, x, y);
        g2d.setColor(heartColor);
        g2d.drawString(healthMessage, x + 90, y);

    }

}
