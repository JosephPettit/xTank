package xTankClientGUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import SharedResources.TankData;

public class ServerConnection {
    private Socket socket;
    private ObjectInputStream objIn;
    private ObjectOutputStream objOut;
    private TankData initialData;

    public void connectToServer(String ipAddress) throws UnknownHostException, IOException, ClassNotFoundException {
        socket = new Socket(ipAddress, 58901);
        System.out.println("Connected to " + socket.getInetAddress().getHostName());

        objIn = new ObjectInputStream(socket.getInputStream());
        objOut = new ObjectOutputStream(socket.getOutputStream());

        // handshake between client and server 
        // Client <- Server: list of available colors from server 
        String[] strArr = (String[]) objIn.readObject();
        // Client -> Server: color selection from dialog 
        objOut.writeObject(JOptionPane.showInputDialog(null,
                "Connected to " + socket.getInetAddress().getHostAddress() + "\n choose Tank color from list below",
                "Tank Selection",
                JOptionPane.QUESTION_MESSAGE, null, strArr, strArr[0]));
       
        // tank from gameState
        initialData = (TankData) objIn.readObject();
        // System.out.println("TankData from server" + initialData);

        // Handshake complete confirmation 
        System.out.println(objIn.readObject());
        objOut.writeObject("Hello from client");

        // Waits for thread to be started. 
    }

    public TankData updateTank(TankData tank) throws ClassNotFoundException, IOException {
        objOut.writeObject(tank);
        return (TankData) objIn.readObject();
    }

    public TankData getInitialTankData() {
        return initialData;
    }

}
