package xTankClientGUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import GameMaps.GameMap;
import SharedResources.GameState;

/**
 * X-Tank Client connection to X-Tank Server
 */
public class ServerConnection {
    private Socket socket;
    private ObjectInputStream objIn;
    private ObjectOutputStream objOut;
    private GameState initialData;
    private GameMap gameMap;

    private int playerNumber;
    private String color;

    /**
     * Client connection to server
     * 
     * @param ipAddress to connect to.
     */
    public void connectToServer(String ipAddress) throws UnknownHostException, IOException, ClassNotFoundException {
        socket = new Socket(ipAddress, 58901);
        System.out.println("Connected to " + socket.getInetAddress().getHostName());

        objIn = new ObjectInputStream(socket.getInputStream());
        objOut = new ObjectOutputStream(socket.getOutputStream());

        // handshake between client and server
        // Client <- Server: list of available colors from server
        String[] strArr = (String[]) objIn.readObject();
        // Client -> Server: color selection from dialog
        if (strArr.length != 1) {
            color = (String) JOptionPane.showInputDialog(null,
                    "Connected to " + socket.getInetAddress().getHostAddress() + "\n choose Tank color from list below",
                    "Tank Selection",
                    JOptionPane.QUESTION_MESSAGE, null, strArr, strArr[0]);
            objOut.writeObject(color);
        } else {
            color = strArr[0];
            objOut.writeObject(color);
        }

        // Client <- Server: gameMap
        gameMap = (GameMap) objIn.readObject();

        // Client <- Server: gameState
        initialData = (GameState) objIn.readObject();

        // Client <- Server: player number
        playerNumber = objIn.readInt();

        // Handshake complete confirmation
        System.out.println(objIn.readObject());
        objOut.writeObject("Hello from client");

        // Waits for thread to be started.
    }

    public GameState updateGameState(GameState gameState) throws ClassNotFoundException, IOException {
        objOut.writeObject(gameState);
        return (GameState) objIn.readObject();
    }

    public GameState getInitialGameState() {
        return initialData;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public String getColor() {
        return color;
    }

    public GameMap getGameMap() {
        return gameMap;
    }
}
