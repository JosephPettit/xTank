import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import XTankServerGUI.ServerFrame;
import xTankServerLogic.Server;

public class ServerMain {

	public static void main(String[] args) throws UnknownHostException {
		Executor pool = Executors.newFixedThreadPool(20);
		System.out.println(InetAddress.getLocalHost().getHostAddress());
		ServerFrame sf = new ServerFrame(InetAddress.getLocalHost().getHostAddress());
		sf.setVisible(true);
		Server server = new Server(pool);
		pool.execute(server);
	}

}
