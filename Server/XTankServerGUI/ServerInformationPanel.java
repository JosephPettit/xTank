
package XTankServerGUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ServerInformationPanel extends JPanel {

	private Font font = new Font("Footlight MT Light", Font.PLAIN, 22);

	private JPanel ipPanel;
	private JLabel serverIP;
	private JTextField serverIPUpdate;
	private JButton btnCopyIP;

	/**
	 * Displays Server IP address
	 */
	public ServerInformationPanel() {
		super();
		setLayout(new BorderLayout(0, 0));
		ipPanel = new JPanel();
		add(ipPanel, BorderLayout.NORTH);
		ipPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		serverIP = new JLabel("Server IP Address: ");
		serverIP.setVerticalAlignment(SwingConstants.BOTTOM);
		serverIP.setHorizontalAlignment(SwingConstants.LEFT);
		serverIP.setFont(font);
		ipPanel.add(serverIP);

		serverIPUpdate = new JTextField();
		serverIPUpdate.setEditable(false);
		serverIPUpdate.setFont(font);
		serverIPUpdate.setColumns(8);
		ipPanel.add(serverIPUpdate);

		btnCopyIP = new JButton("Copy");

		ipPanel.add(btnCopyIP);
	}

	void displayIP(String ipAddress) {
		serverIPUpdate.setText(ipAddress);
	}

	void addCopyTextListener(ActionListener ListenForButton) {
		btnCopyIP.addActionListener(ListenForButton);
	}

}
