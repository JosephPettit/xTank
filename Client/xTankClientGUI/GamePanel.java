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

import GameMaps.GameMap;
import GameMaps.GameMapOne;
import SharedResources.GameState;
import SharedResources.Missile;
import SharedResources.TankData;

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
		timer.setInitialDelay(10);
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

			Toolkit.getDefaultToolkit().sync();

			g2d.setTransform(reset);

			for (Missile missile : gameState.getAllMissiles()) {
				if (!missile.isExploded())
					g2d.drawImage(new ImageIcon(getClass().getResource("Assets/missile.png")).getImage(),
							(int) missile.getX(), (int) missile.getY(), this);
			}

			// TODO: Johnny - How did you do this magic with g2d.drawImage(new
			// ImageIcon(getClass().getResource("Assets/missile.png")).getImage()
			// if we can do the same thing on line 55 instead of craft.drawImage, use your
			// magic and have it use the string in data (TankData).
			// we can delete the Tank class for simplicity and just use the methods in
			// data(TankData). <3 Joe
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
