package xTankClientGUI;
public class ClientMain {

	private static ClientFrame clientFrame;
	private static ClientModel clientModel;
	private static ClientController clientController;
	private static ServerModel serverModel;



	public static void main(String[] args) {
		serverModel = new ServerModel();
		// clientModel = new ClientModel();			
		clientFrame = new ClientFrame();
		clientController = new ClientController(clientFrame, serverModel);
		System.out.println("Main - " + Thread.currentThread().getName());
	}
}
