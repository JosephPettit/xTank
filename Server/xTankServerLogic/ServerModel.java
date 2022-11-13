package xTankServerLogic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.Executor;

import javax.swing.JOptionPane;

import GameMaps.*;
import SharedResources.GameState;
import SharedResources.TankData;

public class ServerModel {
    private ArrayList<ClientConnection> clientConnections;
    private Executor pool;
    private ArrayList<String> tankColors;
    private int numPlayers;
    private GameState gameState;
    private GameMap gameMap;
    private String[] availMaps;

    public ServerModel(Executor pool) {
        this.pool = pool;
        this.gameState = new GameState();
        this.availMaps = new String[] { "Easy", "Medium", "Hard" };

        this.gameMap = setMap((String) JOptionPane.showInputDialog(null,
                "Almost started!\nPlease choose map from list below:",
                "xTank Map Selection",
                JOptionPane.QUESTION_MESSAGE, null, getAvailMaps(), getAvailMaps()[0]));
                
        clientConnections = new ArrayList<>();
        tankColors = new ArrayList<>();
        addTankColors();
        numPlayers = 0;
    }

    private void addTankColors() {
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

    public String[] getAvailMaps() {
        return availMaps;
    }

    public GameMap setMap(String selection) {
        return switch (selection) {
            case "Easy" -> {
                yield new GameMapOne();
            }

            case "Medium" -> {
                yield new GameMapTwo();
            }

            case "Hard" -> {
                yield new GameMapThree();
            }

            default -> throw new IllegalArgumentException("Unexpected value: " + selection);
        };
    }
}
