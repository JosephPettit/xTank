package xTankServerLogic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import GameMaps.GameMap;

import java.awt.geom.Rectangle2D;

import SharedResources.GameState;
import SharedResources.Missile;
import SharedResources.TankData;

public class ClientConnection implements Runnable {
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
            tankAction(player);
            moveMissile(player);
        }
        return gameState;
    }

    private void tankAction(TankData tank) {
        if (moveValid(tank)) {
            moveTank(tank);
            scoobyDooLogic(tank);
        } else {
            tank.setmDr(0);
            tank.setmDx(0);
            tank.setmDy(0);
        }
        missileTankCollision(tank);
        missileWallCollision(tank);
    }

    private void missileWallCollision(TankData tank) {
        for (Missile missile : tank.getMissiles()) {
            for (Rectangle2D wall : serverModel.getGameMap().getWalls()) {
                if (!missile.isExploded() && missile.intersects(wall)) {
                    missile.explode();
                    break;
                }
            }
        }

    }

    private void missileTankCollision(TankData tank) {
        for (Missile missile : serverModel.getGameState().getAllMissiles()) {
            for (TankData player : serverModel.getGameState().getPlayers()) {
                if (player.getPlayerNumber() != missile.getPlayerNumber()) {
                    Rectangle2D playerTank = player;
                    if (!missile.isExploded() && missile.intersects(playerTank)) {
                        System.out.printf("Player %d, hit player %d \n", missile.getPlayerNumber(),
                                player.getPlayerNumber());
                        missile.explode();
                        break;
                    }
                }
            }
        }
    }

    private void moveTank(TankData tank) {
        tank.setmR((tank.getmR() + (tank.getmDy() < 0 ? -1 * tank.getmDr() : tank.getmDr())) % 360);
        tank.setmX(tank.getX() + ((tank.getmDy()) * Math.cos(Math.toRadians(tank.getmR()))));
        tank.setmY(tank.getY() + ((tank.getmDy()) * Math.sin(Math.toRadians(tank.getmR()))));
    }

    private void moveMissile(TankData tank) {
        for (Missile missile : tank.getMissiles()) {
            missile.setX(missile.getX() + (Math.cos(Math.toRadians(missile.getR())) * 2));
            missile.setY(missile.getY() + (Math.sin(Math.toRadians(missile.getR())) * 2));
        }
    }

    private boolean moveValid(TankData tank) {
        TankData temp = (TankData) tank.clone();
        moveTank(temp);
        for (Rectangle2D wall : serverModel.getGameMap().getWalls()) {
            if (temp.intersects(wall))
                return false;
        }
        return true;
    }

    // TODO: scooby doo logic broken now that panels are removed. 
    private void scoobyDooLogic(TankData tank) {
        if (tank.getX() >= GameMap.GAME_WIDTH - 30) {
            tank.setmX(0);
        } else if (tank.getX() <= 0) {
            tank.setmX(GameMap.GAME_WIDTH - 30);
        }

        if (tank.getY() <= 0) {
            tank.setmY(500);
        } else if (tank.getY() > 500) {
            tank.setmY(10);
        }

    }

}
