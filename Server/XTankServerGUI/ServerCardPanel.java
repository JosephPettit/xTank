
package XTankServerGUI;

import java.awt.CardLayout;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class ServerCardPanel extends JPanel {
	public Font font = new Font("Footlight MT Light", Font.PLAIN, 18);

	private BorderLayout borderLayout;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private JPanel statusPanel;
	private JLabel lblStatus;
	private JLabel lblStatusUpdate;
	private JButton btnServerController;

	/**
	 * Create the panel.
	 */
	public ServerCardPanel() {
		super();
		borderLayout = new BorderLayout(0, 0);
		setLayout(borderLayout);
		cardLayout = new CardLayout();

		cardPanel = new JPanel(cardLayout);
		add(cardPanel, BorderLayout.CENTER);

		statusPanel = new JPanel();
		lblStatus = new JLabel("Server Status: ");
		lblStatus.setVerticalAlignment(SwingConstants.BOTTOM);
		lblStatus.setHorizontalAlignment(SwingConstants.LEFT);
		lblStatus.setFont(font);
		statusPanel.add(lblStatus);

		lblStatusUpdate = new JLabel("");
		statusPanel.add(lblStatusUpdate);

		btnServerController = new JButton("Start Server");
		btnServerController.setFont(font);
		statusPanel.add(btnServerController);
		add(statusPanel, BorderLayout.SOUTH);

	}

	public void addCard(JPanel card, String name) {
		cardPanel.add(card, name);
	}

	void cycleCard() {
		cardLayout.next(cardPanel);
	}

	void addServerButtonListener(ActionListener listenForButton) {
		btnServerController.addActionListener(listenForButton);
	}

	void updateServerStatus(String status) {
		lblStatusUpdate.setText(status);
	}

	void updateServerButtonText(String buttonText) {
		btnServerController.setText(buttonText);
	}
}
