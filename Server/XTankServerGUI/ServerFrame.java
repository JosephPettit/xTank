package XTankServerGUI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ServerFrame extends JFrame {

	private ServerInformationPanel runningPanel;

	/**
	 * Create the frame.
	 */
	public ServerFrame(String ipAddress) {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 100);
		setLocationRelativeTo(null);
		setTitle("X-Tank Server");
		runningPanel = new ServerInformationPanel();
		setContentPane(runningPanel);
		runningPanel.displayIP(ipAddress);
		setIconImage(new ImageIcon(getClass().getResource("xtanklogo.png")).getImage());
	}

	public void setIPAddress(String ipAddress) {
		runningPanel.displayIP(ipAddress);
	}

}
