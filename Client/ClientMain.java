
import xTankClientGUI.ClientController;
import xTankClientGUI.ClientFrame;
import xTankClientGUI.ClientModel;
import xTankClientGUI.ServerConnection;

public class ClientMain {

	private static ServerConnection serverConnection;
	private static ClientFrame clientFrame;
	private static ClientController clientController;

	public static void main(String[] args) {
		serverConnection = new ServerConnection();
		clientFrame = new ClientFrame();
		clientController = new ClientController(clientFrame, serverConnection);
	}
}
