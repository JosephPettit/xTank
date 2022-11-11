package XTankServerGUI;

import java.awt.EventQueue;

public class ServerGUIMain {

	private ServerFrame serverFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGUIMain window = new ServerGUIMain();
					ServerGUIModel model = new ServerGUIModel();

					new ServerController(window.serverFrame, model);
					window.serverFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ServerGUIMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		serverFrame = new ServerFrame();

	}

}
