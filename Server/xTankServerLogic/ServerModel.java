package xTankServerLogic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executor;

public class ServerModel {
    private ArrayList<ClientConnection> clientConnections;
    private Executor pool;
    private ArrayList<String> tankColors;

    public ServerModel(Executor pool) {
        this.pool = pool;
        clientConnections = new ArrayList<>();
        tankColors = new ArrayList<>();
        tankColors.add("Yellow");
        tankColors.add("Green");
        tankColors.add("Blue");
        tankColors.add("Red");

    }

    public void addConnection(ClientConnection connection) {
        clientConnections.add(connection);
    }

    public void startConnections() {
        for (ClientConnection connection : clientConnections) {
            pool.execute(connection);
        }
    }

    private String[] getColors() {
        return tankColors.toArray(new String[tankColors.size()]);
    }

    public synchronized String clientTankSelection(ObjectInputStream objIn, ObjectOutputStream objOut)
            throws ClassNotFoundException, IOException {
        objOut.writeObject(getColors());
        String selection = (String) objIn.readObject();
        tankColors.remove(selection);
        return selection;
    }

}
