package xTankClientGUI;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GameMaps.GameMap;
import SharedResources.Player;

public class HealthBar extends JComponent {

    private ArrayList<JLabel> bars;
    private String display; 
    private int x, y;
    private Color color;
    String healthMessage = "";

    public HealthBar(int player, int health, int x, int y) {
        healthMessage = "";
        player++;
        switch(health){
            case 3:
                healthMessage = "\u2764\u2764\u2764";
                break;
            case 2:
                healthMessage = "\u2764\u2764";
                break;
            case 1:
                healthMessage = "\u2764";
                break;
            default:
                healthMessage = "";
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
        g2d.drawString(display, x,y);
        g2d.setColor(Color.RED);
        g2d.drawString(healthMessage, x + 90, y);
        
    }

}
