package xTankServerLogic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import SharedResources.GameState;
import SharedResources.TankData;

public class ClientConnection implements Runnable {
    private int width = 1200;
    private int height = 720;

    private Socket socket;
    private ObjectInputStream objIn;
    private ObjectOutputStream objOut;
    private TankData tank;
    private ServerModel serverModel;

    ClientConnection(Socket socket, ServerModel serverModel) throws IOException {
        this.socket = socket;
        this.serverModel = serverModel;
    }

    @Override
    public void run() {
        try {
            objOut = new ObjectOutputStream(this.socket.getOutputStream());
            objIn = new ObjectInputStream(this.socket.getInputStream());
            System.out.println("connected to " + socket.getInetAddress().getHostName());

            // Handshake between client and server
            // server -> client: color list; server <- client: color selection
            tank = serverModel.startingTank(serverModel.clientTankSelection(objIn, objOut));

            // server -> client: gameState
            objOut.writeObject(serverModel.getGameState());

            // server -> client: playerNumber 
            objOut.writeInt(tank.getPlayerNumber());

            // Handshake complete confirmation
            objOut.writeObject("Hello from Server");
            System.out.println(objIn.readObject()); // hello from client

            while (serverModel.getGameState() != null) {
                serverModel.setGameState((GameState) objIn.readObject());
                objOut.writeObject(gameUpdate(serverModel.getGameState()));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private synchronized GameState gameUpdate(GameState gameState) {
        for (TankData player : gameState.getPlayers()) {
            moveTank(player);
            moveMissile(player);
            checkCollision(player);
        }

        return gameState;
    }

    private void moveTank(TankData tank) {
        tank.setmR((tank.getmR() + (tank.getmDy() < 0 ? -1 * tank.getmDr() : tank.getmDr())) % 360);
        tank.setmX(tank.getmX() + ((tank.getmDy()) * Math.cos(Math.toRadians(tank.getmR()))));
        tank.setmY(tank.getmY() + ((tank.getmDy()) * Math.sin(Math.toRadians(tank.getmR()))));
    }

    private void moveMissile(TankData tank) {
        tank.setMissileX(tank.getMissileX() + (Math.cos(Math.toRadians(tank.getMissileR())) * 2));
		tank.setMissileY(tank.getMissileY() + (Math.sin(Math.toRadians(tank.getMissileR())) * 2));
    }

    private void checkCollision(TankData tank) {
        if (tank.getmX() >= width - 30) {
            tank.setmX(0);
        } else if (tank.getmX() <= 0) {
            tank.setmX(width - 30);
        }

        if (tank.getmY() <= 0) {
            tank.setmY(height - 110);
        } else if (tank.getmY() > height - 110) {
            tank.setmY(0);
        }
    }

}
