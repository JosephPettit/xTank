package xTankClientGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.util.concurrent.locks.ReentrantLock;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import SharedResources.GameState;
import SharedResources.TankData;

public class GamePanel extends JPanel{

	private Timer timer;
	private Tank craft;
	private GameState gameState;
	private ReentrantLock updateLock = new ReentrantLock();

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

	public void paint(Graphics g) {


		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		for (TankData data : gameState.getPlayers()) {
			updateLock.lock();
			try{
				craft = new Tank(data);

				AffineTransform reset = g2d.getTransform();
	
				g2d.rotate(Math.toRadians(craft.getmR()), craft.getmX() + 10, craft.getmY() + 10);
				g2d.drawImage(craft.getImage(), (int) craft.getmX(), (int) craft.getmY(), this);
	
				Toolkit.getDefaultToolkit().sync();
				
				g2d.setTransform(reset);
				
				g2d.drawImage(new ImageIcon(getClass().getResource("Assets/missile.png")).getImage(), (int) craft.getMissileX(), (int) craft.getMissileY(), this);
			} finally {
				updateLock.unlock();
			}
		}

		// Coordinate Variables
		int p1xpos = ((int)gameState.getPlayers().get(0).getmX()); 
		int p1ypos = ((int)gameState.getPlayers().get(0).getmY()); 
		int p1missilexpos = ((int)gameState.getPlayers().get(0).getMissileX());
		int p1missileypos = ((int)gameState.getPlayers().get(0).getMissileY());

		int p2xpos = ((int)gameState.getPlayers().get(1).getmX()); 
		int p2ypos = ((int)gameState.getPlayers().get(1).getmY()); 
		int p2missilexpos = ((int)gameState.getPlayers().get(1).getMissileX());
		int p2missileypos = ((int)gameState.getPlayers().get(1).getMissileY());

		// Check missile hits
		if(p1missilexpos > (p2xpos - 10) && p1missilexpos < (p2xpos + 10)) {
			if(p1missileypos > (p2ypos - 10) && p1missileypos < (p2ypos + 10)) {
				System.out.print("p1 hit p2");
			}
		}
		if(p2missilexpos > (p1xpos - 10) && p2missilexpos < (p1xpos + 10)) {
			if(p2missileypos > (p1ypos - 10) && p2missileypos < (p1ypos + 10)) {
				System.out.print("p2 hit p1");
			}
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
