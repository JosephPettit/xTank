import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.SwingUtilities;

import XTankServerGUI.ServerFrame;
import xTankServerLogic.Server;

/**
 * Starts and runs X-Tank Server
 */
public class ServerMain {

	public static void main(String[] args) throws UnknownHostException {
		Executor pool = Executors.newFixedThreadPool(20);
		ServerFrame sf = new ServerFrame(InetAddress.getLocalHost().getHostAddress());
		sf.setVisible(true);
		Server server = new Server(pool);
		pool.execute(server);
	}

}
