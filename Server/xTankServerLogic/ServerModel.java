package xTankServerLogic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.Executor;

import GameMaps.*;
import SharedResources.GameState;
import SharedResources.Missile;
import SharedResources.TankData;

public class ServerModel {
    private ArrayList<ClientConnection> clientConnections;
    private Executor pool;
    private ArrayList<String> tankColors;
    private int numPlayers;
    private GameState gameState;
    private GameMap gameMap;

    public ServerModel(Executor pool) {
        this.pool = pool;
        this.gameState = new GameState();
        this.gameMap = new GameMapTwo();
        clientConnections = new ArrayList<>();
        tankColors = new ArrayList<>();
        tankColors.add("Yellow");
        tankColors.add("Green");
        tankColors.add("Blue");
        tankColors.add("Red");
        numPlayers = 0;
    }

    public void addConnection(ClientConnection connection) {
        clientConnections.add(connection);
    }

    public void startConnections() {
        for (ClientConnection connection : clientConnections) {
            pool.execute(connection);
        }
    }

    public TankData startingTank(String color) {
        return gameState.addPlayer(color, numPlayers++);
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

    public int getNumPlayers() {
        return numPlayers;
    }

    public synchronized GameState getGameState() {
        return gameState;
    }

    public synchronized void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public synchronized GameMap getGameMap() {
        return gameMap;
    }
}
