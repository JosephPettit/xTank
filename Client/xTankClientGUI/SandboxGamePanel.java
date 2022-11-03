package xTankClientGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;


import javax.swing.JPanel;
import javax.swing.Timer;

public class SandboxGamePanel extends JPanel {


	private Timer timer;
	private Tank craft;

	AffineTransform identity = new AffineTransform();


	/**
	 * Create the panel.
	 */
	public SandboxGamePanel() {
		setBackground(Color.BLACK);
		timer = new Timer(5, null);
		timer.start();
	}

	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.rotate(Math.toRadians(craft.getmR()), craft.getmX() + 10, craft.getmY() + 10);
		g2d.drawImage(craft.getImage(), (int) craft.getmX(), (int) craft.getmY(), this);

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	void addInputActionListener(KeyListener listenForKey) {
		this.addKeyListener(listenForKey);
	}

	public void addPlayerTank(Tank tank) {
		craft = tank;
	}

	public void addGameTimerListener(ActionListener actionListener) {
		timer.addActionListener(actionListener);
	}

}
