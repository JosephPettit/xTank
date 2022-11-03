package XTankClientGUI;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ClientFrame extends JFrame {

	static final int GAME_WIDTH = 1280;
	static final int GAME_HEIGHT = 720;
	private ClientCardPanel clientCardPanel;
	private SandboxGamePanel gamePanel;

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
		
		setupPanels();
		
		setContentPane(clientCardPanel);
	}

	public void displayErrorMesasge(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
		
	public void cycleCard() {
		clientCardPanel.cycleCard();
	}
	
	private void setupPanels() {
		clientCardPanel = new ClientCardPanel();
		add(clientCardPanel);
		gamePanel = new SandboxGamePanel();
		clientCardPanel.addCard(gamePanel);
	}
	
	public void addGamePanelKeyListener(KeyListener listener) {
		this.addKeyListener(listener);
	}
	
	public void addPlayerTank(Tank tank) {
		gamePanel.addPlayerTank(tank);
	}

	public void addGamePanelTimerListener(ActionListener actionListener) {
		gamePanel.addGameTimerListener(actionListener);
		
	}
}
