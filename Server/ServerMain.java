
import java.awt.EventQueue;

import XTankServerGUI.ServerController;
import XTankServerGUI.ServerFrame;
import XTankServerGUI.ServerModel;

public class ServerMain {

	private ServerFrame serverFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.out.println("print");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerMain window = new ServerMain();
					ServerModel model = new ServerModel();

					ServerController controller = new ServerController(window.serverFrame, model);
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
	public ServerMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		serverFrame = new ServerFrame();

	}

}
