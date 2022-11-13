package xTankClientGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import GameMaps.*;
import SharedResources.GameState;
import SharedResources.Missile;
import SharedResources.Player;
import SharedResources.TankData;

/**
 * Game Panel for holding current game state.
 */
public class GamePanel extends JPanel {

	private Timer timer;
	private GameState gameState;
	private GameMap gameMap;
	AffineTransform identity = new AffineTransform();

	/**
	 * Create the panel.
	 */
	public GamePanel() {
		setBackground(Color.BLACK);
		timer = new Timer(5, null);
		timer.setInitialDelay(10);
		timer.start();
	}

	/**
	 * Paints the current game state.
	 */
	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);
		AffineTransform reset = g2d.getTransform();

		if (gameState.isActive()) {

			// Paint map
			gameMap.paintComponent(g2d);

			for (TankData data : gameState.getPlayerTanks()) {
				// Paint tanks
				g2d.rotate(Math.toRadians(data.getmR()), data.getX() + 10, data.getY() + 10);
				g2d.drawImage(new ImageIcon(getClass().getResource(data.getTankColor())).getImage(), (int) data.getX(),
						(int) data.getY(), this);

				g2d.setTransform(reset);

				// Paint Missiles
				for (Missile missile : gameState.getAllMissiles()) {
					if (!missile.isExploded())
						g2d.drawImage(new ImageIcon(getClass().getResource("Assets/missile.png")).getImage(),
								(int) missile.getX(), (int) missile.getY(), this);
				}
			}

			// Health area display
			int i = 0;
			for (Player player : gameState.getPlayers()) {
				HealthBar healthBar = new HealthBar(player.getPlayerNumber(), player.getHealth(), 10 + (i * 150),
						GameMap.GAME_HEIGHT - 75);
				healthBar.paintComponent(g2d);
				i++;
			}
		} else {
			// Paint winning message
			for (Player player : gameState.getPlayers()) {
				if (player.isAlive()) {
					WinnerMessage wm = new WinnerMessage(player);
					wm.paintComponent(g2d);
				}
			}
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	void addInputActionListener(KeyListener listenForKey) {
		this.addKeyListener(listenForKey);
	}

	public void addGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public void addGameTimerListener(ActionListener actionListener) {
		timer.addActionListener(actionListener);
	}

	public void addGameMap(GameMap gameMap) {
		this.gameMap = gameMap;
	}

}
