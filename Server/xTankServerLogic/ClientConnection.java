package xTankServerLogic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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

    /**
     * Server connection to Clients.
     * 
     * @param socket
     * @param serverModel
     * @throws IOException
     */
    ClientConnection(Socket socket, ServerModel serverModel) throws IOException {
        this.socket = socket;
        this.serverModel = serverModel;
    }

    /**
     * Server connection to client.
     */
    @Override
    public void run() {
        try {
            objOut = new ObjectOutputStream(this.socket.getOutputStream());
            objIn = new ObjectInputStream(this.socket.getInputStream());

            // Handshake between client and server
            // server -> client: color list; server <- client: color selection
            tank = serverModel.startingTank(serverModel.clientTankSelection(objIn, objOut));

            // server -> client: gameMap
            objOut.writeObject(serverModel.getGameMap());

            // server -> client: gameState
            objOut.writeObject(serverModel.getGameState());

            // server -> client: playerNumber
            objOut.writeInt(tank.getPlayerNumber());

            // Handshake complete confirmation
            objOut.writeObject("Hello from Server");
            System.out.println(objIn.readObject()); // hello from client

            while (true) {
                serverModel.setGameState((GameState) objIn.readObject());
                objOut.writeObject(gameUpdate(serverModel.getGameState()));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates {@code GameState} object from client.
     * 
     * If only one player is remaining {@code gameState.isActive() = false}.
     * 
     * @param gameState from client.
     * @return updated {@code GameState}.
     */
    private synchronized GameState gameUpdate(GameState gameState) {
        if (!GameLogic.checkWinner(gameState))
            for (TankData player : gameState.getPlayerTanks()) {
                tankAction(player);
                moveMissile(player);
            }
        else
            gameState.setActive(false);
        return gameState;
    }

    /**
     * Handles moving tank and missile objects.
     * 
     * @param tank to be updated.
     */
    private void tankAction(TankData tank) {
        if (moveValid(tank)) {
            moveTank(tank);

        } else {
            tank.setmDr(0);
            tank.setmDx(0);
            tank.setmDy(0);
        }
        missileTankCollision();
        missileWallCollision(tank);
    }

    /**
     * Wall collision detection for missiles.
     * 
     * @param tank that fired the missile.
     */
    private void missileWallCollision(TankData tank) {
        for (Missile missile : tank.getMissiles()) {
            for (Rectangle2D wall : serverModel.getGameMap().getWalls()) {
                if (!missile.isExploded() && missile.intersects(wall)) {
                    missile.explode();
                }
            }
        }

    }

    /**
     * Missile to tank collision detection.
     * 
     * Checks all non-exploded missiles on map for tank collision.
     */
    private void missileTankCollision() {
        for (Missile missile : serverModel.getGameState().getAllMissiles()) {
            for (TankData player : serverModel.getGameState().getPlayerTanks()) {
                if (player.getPlayerNumber() != missile.getPlayerNumber()) {
                    Rectangle2D playerTank = player;
                    if (!missile.isExploded() && missile.intersects(playerTank)) {
                        serverModel.getGameState().playerHit(player.getPlayerNumber());
                        missile.explode();
                    }
                }
            }
        }
    }

    /**
     * Set Tank coordinates and rotation angle.
     * 
     * @param tank to be moved.
     */
    private void moveTank(TankData tank) {
        tank.setmR((tank.getmR() + (tank.getmDy() < 0 ? -1 * tank.getmDr() : tank.getmDr())) % 360);
        tank.setmX(tank.getX() + ((tank.getmDy()) * Math.cos(Math.toRadians(tank.getmR()))));
        tank.setmY(tank.getY() + ((tank.getmDy()) * Math.sin(Math.toRadians(tank.getmR()))));
    }

    /**
     * Set Missile coordinates and rotation angle.
     * 
     * @param tank that fired missile.
     */
    private void moveMissile(TankData tank) {
        for (Missile missile : tank.getMissiles()) {
            missile.setX(missile.getX() + (Math.cos(Math.toRadians(missile.getR())) * 2));
            missile.setY(missile.getY() + (Math.sin(Math.toRadians(missile.getR())) * 2));
        }
    }

    /**
     * Wall collision detection for {@code TankData}.
     * 
     * Creates a temp clone of {@code tank} and checks
     * for wall collisions.
     * 
     * @param tank to be evaluated.
     * @return {@code True} if move is valid.
     */
    private boolean moveValid(TankData tank) {
        TankData temp = (TankData) tank.clone();
        moveTank(temp);
        for (Rectangle2D wall : serverModel.getGameMap().getWalls()) {
            if (temp.intersects(wall))
                return false;
        }
        return true;
    }

}
