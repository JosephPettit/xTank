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

/**
 * Server Model
 * 
 * All game data is stored and managed within the Server Model.
 */
public class ServerModel {
    private ArrayList<ClientConnection> clientConnections;
    private Executor pool;
    private ArrayList<String> tankColors;
    private int numPlayers;
    private GameState gameState;
    private GameMap gameMap;
    private String[] availMaps;

    /**
     * ServerModel to control X-Tank
     * 
     * @param pool ThreadPool to house all client connections.
     */
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

    /**
     * Adds starting colors.
     */
    private void addTankColors() {
        tankColors.add("Yellow");
        tankColors.add("Green");
        tankColors.add("Blue");
        tankColors.add("Red");
    }

    /**
     * Adds {@code ClientConnection} to {@code ArrayList} of
     * {@code ClientConnections}.
     * 
     * @param connection to be added.
     */
    public void addConnection(ClientConnection connection) {
        clientConnections.add(connection);
    }

    /**
     * Starts all connected client threads.
     */
    public void startConnections() {
        for (ClientConnection connection : clientConnections) {
            pool.execute(connection);
        }
    }

    /**
     * Creates new Player and increments number of players.
     * 
     * @param color Player's color.
     * @return new Player's {@code TankData}
     */
    public TankData startingTank(String color) {
        return gameState.addPlayer(color, numPlayers++);
    }

    /**
     * Returns the current list of available colors.
     * 
     * @return Available tank colors.
     */
    private String[] getColors() {
        return tankColors.toArray(new String[tankColors.size()]);
    }

    /**
     * Receives the Players color selection and removes the selected color from list
     * of available colors.
     * 
     * @param objIn
     * @param objOut
     * @return {@code String} of user's tank color selection.
     * @throws ClassNotFoundException
     * @throws IOException
     */
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

    /**
     * Set's {@code GameMap} based off of players selection.
     * 
     * @param selection from user.
     * @return {@code GameMap}
     */
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
