package xTankClientGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;

import GameMaps.GameMap;
import SharedResources.Player;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Displays Player win message at end of game.
 */
public class WinnerMessage extends JComponent {

    private String display;
    private String dFooter;

    WinnerMessage(Player player) {
        display = String.format("Player %d WINS!!", player.getPlayerNumber() + 1);
        dFooter = "To replay X-Tank please have the server host restart the server, and re-join.";
    }

    @Override
    public synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Footlight MT Light", Font.PLAIN, 64));
        g2d.drawString(display, GameMap.GAME_WIDTH / 2 - 150, GameMap.GAME_HEIGHT / 2);
        g2d.setFont(new Font("Footlight MT Light", Font.PLAIN, 32));
        g2d.drawString(dFooter, GameMap.GAME_WIDTH / 2 - 450, GameMap.GAME_HEIGHT / 2 + 75);

    }
}
