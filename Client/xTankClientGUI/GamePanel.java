package xTankClientGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.Timer;

import SharedResources.GameState;
import SharedResources.TankData;
import SharedResources.GameMaps.GameMap;
import SharedResources.GameMaps.GameMapOne;

public class GamePanel extends JPanel {

	private Timer timer;
	private Tank craft;
	private GameState gameState;
	private GameMap gameMap;

	AffineTransform identity = new AffineTransform();

	/**
	 * Create the panel.
	 */
	public GamePanel() {
		setBackground(Color.BLACK);
		timer = new Timer(5, null);
		timer.start();
		this.gameMap = new GameMapOne();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);
		AffineTransform reset = g2d.getTransform();

		gameMap.paintComponent(g2d);
		for (TankData data : gameState.getPlayers()) {
			craft = new Tank(data);

			g2d.rotate(Math.toRadians(craft.getmR()), craft.getmX() + 10, craft.getmY() + 10);
			g2d.drawImage(craft.getImage(), (int) craft.getmX(), (int) craft.getmY(), this);
			g2d.setTransform(reset);

			Toolkit.getDefaultToolkit().sync();
		}
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

}
