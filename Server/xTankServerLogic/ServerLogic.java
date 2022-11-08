package xTankServerLogic;

import java.util.ArrayList;
import java.util.concurrent.Executor;

public class ServerLogic {
    private ArrayList<ClientConnection> clientConnections;
    private Executor pool;

    ServerLogic(Executor pool) {
        this.pool = pool;
        clientConnections = new ArrayList<>();
    }

    public void addConnection(ClientConnection connection) {
        clientConnections.add(connection);
    }

    public void startConnections() {
        for (ClientConnection connection : clientConnections) {
            pool.execute(connection);
        }
    }

}
