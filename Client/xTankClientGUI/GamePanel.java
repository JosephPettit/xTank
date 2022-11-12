package xTankClientGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import SharedResources.GameState;
import SharedResources.TankData;

public class GamePanel extends JPanel{

	private Timer timer;
	private Tank craft;
	private GameState gameState;

	AffineTransform identity = new AffineTransform();

	/**
	 * Create the panel.
	 */
	public GamePanel() {
		setBackground(Color.BLACK);
		timer = new Timer(10, null);
		timer.start();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		for (TankData data : gameState.getPlayers()) {
			craft = new Tank(data);

			AffineTransform reset = g2d.getTransform();

			g2d.rotate(Math.toRadians(craft.getmR()), craft.getmX() + 10, craft.getmY() + 10);
			g2d.drawImage(craft.getImage(), (int) craft.getmX(), (int) craft.getmY(), this);

			Toolkit.getDefaultToolkit().sync();
			
			g2d.setTransform(reset);
			
			g2d.drawImage(new ImageIcon(getClass().getResource("Assets/missile.png")).getImage(), (int) craft.getMissileX(), (int) craft.getMissileY(), this);
			
		}
		g.dispose();
		timer.restart();
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
