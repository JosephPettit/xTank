package XTankServerGUI;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class ServerFrame extends JFrame {

	private ServerInformationPanel runningPanel;
	private final String ipAddress;

	/**
	 * Create the frame.
	 */
	public ServerFrame(String ipAddress) {
		super();
		this.ipAddress = ipAddress;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 100);
		setLocationRelativeTo(null);
		setTitle("X-Tank Server");
		runningPanel = new ServerInformationPanel();
		setContentPane(runningPanel);
		runningPanel.displayIP(ipAddress);
	}

	public void addCopyButtonListener(ActionListener listener) {
		runningPanel.addCopyTextListener(listener);
	}

	public void setIPAddress(String ipAddress) {
		runningPanel.displayIP(ipAddress);
	}

}
