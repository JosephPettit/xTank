package xTankClientGUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JDialog;
import javax.swing.JLabel;

import SharedResources.TankData;

public class ServerConnection {
    private Socket socket;
    private ObjectInputStream objIn;
    private ObjectOutputStream objOut;

    public void connectToServer(String ipAddress) throws UnknownHostException, IOException, ClassNotFoundException {
        socket = new Socket(ipAddress, 58901);
        System.out.println("Connected to " + socket.getInetAddress().getHostName());
        objIn = new ObjectInputStream(socket.getInputStream());
        objOut = new ObjectOutputStream(socket.getOutputStream());

        System.out.println(objIn.readObject());
        objOut.writeObject("Hello from client");
        objOut.writeObject(new TankData());
        System.out.println(objIn.readObject().toString());

    }

    public TankData getTank() throws ClassNotFoundException, IOException {
        return (TankData) objIn.readObject();
    }

    public TankData updateTank(TankData tank) throws ClassNotFoundException, IOException {
        objOut.writeObject(tank);
        return (TankData) objIn.readObject();
    }

}
