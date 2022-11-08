package xTankSandboxClientGUI;


import java.awt.EventQueue;

public class ClientSandBoxMain {

	private static ClientFrame clientFrame;
	private static ClientModel clientModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				clientModel = new ClientModel();
				clientFrame = new ClientFrame();

				ClientController controller = new ClientController(clientFrame, clientModel);
				clientFrame.setVisible(true);
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientSandBoxMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		clientFrame = new ClientFrame();
	}

}
