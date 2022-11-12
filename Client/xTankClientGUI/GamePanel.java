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
import SharedResources.TankData;
import xTankClientGUI.HealthBar;

public class GamePanel extends JPanel {

	private Timer timer;
	private GameState gameState;
	private GameMap gameMap;
	//private HealthBar healthBar;

	AffineTransform identity = new AffineTransform();

	/**
	 * Create the panel.
	 */
	public GamePanel() {
		setBackground(Color.BLACK);
		timer = new Timer(5, null);
		timer.setInitialDelay(10);
		timer.start();
		this.gameMap = new GameMapTwo();
	}

	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);
		AffineTransform reset = g2d.getTransform();

		gameMap.paintComponent(g2d);
		for (TankData data : gameState.getPlayerTanks()) {

			g2d.rotate(Math.toRadians(data.getmR()), data.getX() + 10, data.getY() + 10);
			g2d.drawImage(new ImageIcon(getClass().getResource(data.getTankColor())).getImage(), (int) data.getX(),
					(int) data.getY(), this);

			g2d.setTransform(reset);

			for (Missile missile : gameState.getAllMissiles()) {
				if (!missile.isExploded())
					g2d.drawImage(new ImageIcon(getClass().getResource("Assets/missile.png")).getImage(),
							(int) missile.getX(), (int) missile.getY(), this);
					missile.setDistance(missile.getDistance()+1);
					if(missile.getDistance() > missile.getMAX_DISTANCE()) {
						missile.setExploded(true);
					}	
			}
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	// TODO: add game information in bottom 'panel', player, health etc.

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
