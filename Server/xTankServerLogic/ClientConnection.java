package xTankServerLogic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import SharedResources.TankData;

public class ClientConnection implements Runnable {
    private int width = 1200;
    private int height = 720;

    private Socket socket;
    private ObjectInputStream objIn;
    private ObjectOutputStream objOut;

    ClientConnection(Socket socket) throws IOException {
        this.socket = socket;

    }

    @Override
    public void run() {
        try {
            objOut = new ObjectOutputStream(this.socket.getOutputStream());
            objIn = new ObjectInputStream(this.socket.getInputStream());
            System.out.println("connected to " + socket.getInetAddress().getHostName());
            objOut.writeObject("Hello from Server");
            System.out.println(objIn.readObject());
            TankData tank = new TankData();
            while (tank != null) {
                tank = (TankData) objIn.readObject();
                objOut.writeObject(tankAction(tank));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private TankData tankAction(TankData tank) {
        moveTank(tank);
        checkCollision(tank);
        return tank;
    }

    private TankData moveTank(TankData tank) {
        tank.setmR((tank.getmR() + (tank.getmDy() < 0 ? -1 * tank.getmDr() : tank.getmDr())) % 360);
        tank.setmX(tank.getmX() + ((tank.getmDy()) * Math.cos(Math.toRadians(tank.getmR()))));
        tank.setmY(tank.getmY() + ((tank.getmDy()) * Math.sin(Math.toRadians(tank.getmR()))));
        return tank;
    }

    private TankData checkCollision(TankData tank) {
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

        return tank;
    }

}
