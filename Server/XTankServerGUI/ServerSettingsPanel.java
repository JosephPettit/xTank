
package XTankServerGUI;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ServerSettingsPanel extends JPanel {

	private Font font = new Font("Footlight MT Light", Font.PLAIN, 22);
	private SpringLayout springLayout;
	private JLabel lblSettingsHeader;
	private JLabel lblNumPlayers;
	private JSpinner spinNumPlayers;

	/**
	 * Create the panel.
	 */
	public ServerSettingsPanel() {
		super();
		springLayout = new SpringLayout();
		setLayout(springLayout);
		lblSettingsHeader = new JLabel("X Tank Server");
		lblSettingsHeader.setForeground(new Color(255, 0, 0));
		lblSettingsHeader.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.NORTH, lblSettingsHeader, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblSettingsHeader, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblSettingsHeader, 41, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblSettingsHeader, 440, SpringLayout.WEST, this);
		lblSettingsHeader.setFont(new Font("Ink Free", Font.BOLD | Font.ITALIC, 32));
		add(lblSettingsHeader);

		lblNumPlayers = new JLabel("NUMBER OF PLAYERS:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNumPlayers, 6, SpringLayout.SOUTH, lblSettingsHeader);
		springLayout.putConstraint(SpringLayout.WEST, lblNumPlayers, 20, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNumPlayers, 37, SpringLayout.SOUTH, lblSettingsHeader);
		springLayout.putConstraint(SpringLayout.EAST, lblNumPlayers, 253, SpringLayout.WEST, this);
		lblNumPlayers.setFont(new Font("Footlight MT Light", Font.BOLD, 22));
		add(lblNumPlayers);

		spinNumPlayers = new JSpinner();
		spinNumPlayers.setModel(new SpinnerNumberModel(2, 2, 4, 1));
		spinNumPlayers.setFont(font);
		springLayout.putConstraint(SpringLayout.NORTH, spinNumPlayers, 6, SpringLayout.SOUTH, lblSettingsHeader);
		springLayout.putConstraint(SpringLayout.WEST, spinNumPlayers, 6, SpringLayout.EAST, lblNumPlayers);
		springLayout.putConstraint(SpringLayout.SOUTH, spinNumPlayers, 0, SpringLayout.SOUTH, lblNumPlayers);
		springLayout.putConstraint(SpringLayout.EAST, spinNumPlayers, 59, SpringLayout.EAST, lblNumPlayers);
		add(spinNumPlayers);

	}

	public int getNumPlayers() {
		return (Integer) spinNumPlayers.getValue();
	}

}
