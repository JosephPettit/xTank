package xTankClientGUI;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import SharedResources.GameState;

public class ClientFrame extends JFrame {

	public static final int GAME_WIDTH = 1280;
	public static final int GAME_HEIGHT = 720;
	// private ClientCardPanel clientCardPanel;
	private GamePanel gamePanel;

	/**
	 * Create the frame.
	 */
	public ClientFrame() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setLocationRelativeTo(null);
		setTitle("X-Tank Client");
		setResizable(false);
		// this.setIconImage(new
		// ImageIcon(getClass().getResource("Assets/redTank.png")).getImage());
		setupPanels();
		setContentPane(gamePanel);
	}

	public void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	// public void cycleCard() {
	// clientCardPanel.cycleCard();
	// }

	private void setupPanels() {
		// clientCardPanel = new ClientCardPanel();
		// add(clientCardPanel);
		gamePanel = new GamePanel();
		// clientCardPanel.addCard(gamePanel);
		add(gamePanel);
	}

	public void addGamePanelKeyListener(KeyListener listener) {
		this.addKeyListener(listener);
	}

	public void addGameState(GameState gameState) {
		gamePanel.addGameState(gameState);
	}

	public void addGamePanelTimerListener(ActionListener actionListener) {
		gamePanel.addGameTimerListener(actionListener);
	}
}
