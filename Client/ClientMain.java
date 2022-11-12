

import java.io.IOException;

import javax.swing.JOptionPane;

import xTankClientGUI.ClientController;
import xTankClientGUI.ClientFrame;
import xTankClientGUI.ServerConnection;

public class ClientMain {

	private static ClientFrame clientFrame;
	private static ServerConnection serverConnection;

	public static void main(String[] args) {

		serverConnection = new ServerConnection();
		connectToServer();
		clientFrame = new ClientFrame();
		new ClientController(clientFrame, serverConnection);
	}

	private static void connectToServer() {
		String ip = JOptionPane.showInputDialog(null, "Enter Server IP Address:", "Enter Server IP",
				JOptionPane.QUESTION_MESSAGE);
		if (ip == null) {
			System.exit(0);
		}

		try {
			serverConnection.connectToServer(ip);
		} catch (ClassNotFoundException | IOException e) {

			int ans = JOptionPane.showConfirmDialog(null, "Failed to connect to server. Would you like to try again?",
					"Connection Failed", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

			if (ans == JOptionPane.YES_OPTION) {
				connectToServer();
			} else {
				System.exit(0);
			}
		}
	}

}
