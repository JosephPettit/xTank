import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import xTankServerLogic.Server;
import xTankServerLogic.ServerModel;

public class ServerMain {

	public static void main(String[] args) {
		Executor pool = Executors.newFixedThreadPool(20);
		Server server = new Server(pool);
		pool.execute(server);
	}

}
