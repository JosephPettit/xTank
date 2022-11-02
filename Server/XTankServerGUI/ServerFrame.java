package XTankServerGUI;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ServerFrame extends JFrame {

	private ServerSettingsPanel settingsPanel;
	private ServerRunningPanel runningPanel;
	private ServerCardPanel serverCardPanel;

	/**
	 * Create the frame.
	 */
	public ServerFrame() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setupPanels();
		setContentPane(serverCardPanel);

	}

	public void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	public void addServerButtonListener(ActionListener listener) {
		serverCardPanel.addServerButtonListener(listener);
	}

	public void addCopyButtonListener(ActionListener listener) {
		runningPanel.addCopyTextListener(listener);
	}

	public int retrievePlayers() {
		return settingsPanel.getNumPlayers();
	}

	public void setServerStatus(String status) {
		serverCardPanel.updateServerStatus(status);
	}

	public void cycleCard() {
		serverCardPanel.cycleCard();
	}

	public void updateLog(String message) {
		runningPanel.updateLog(message);
	}

	public void setIPAddress(String ipAddress) {
		runningPanel.displayIP(ipAddress);
	}

	public void updateServerButtonText(String message) {
		serverCardPanel.updateServerButtonText(message);
	}

	private void setupPanels() {
		serverCardPanel = new ServerCardPanel();
		settingsPanel = new ServerSettingsPanel();
		runningPanel = new ServerRunningPanel();
		serverCardPanel.addCard(settingsPanel, "Settings Panel");
		serverCardPanel.addCard(runningPanel, "Running Panel");
	}
}
