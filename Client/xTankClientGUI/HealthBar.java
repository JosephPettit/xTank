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
    public HealthBar(int player, int health, int x, int y) {
        String healthMessage = "";
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
        display = String.format("Player %d Health: %s", player, healthMessage);
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
        
    }

}
