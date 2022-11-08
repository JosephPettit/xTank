package xTankServerLogic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import SharedResources.TankData;

public class Server implements Runnable {

  private int width = 1200;
  private int height = 720;
  // private Socket client1;
  private ObjectInputStream objIn;
  private ObjectOutputStream objOut;

  private static Executor pool = Executors.newFixedThreadPool(20);

  @Override
  public void run() {
    try (var listener = new ServerSocket(58901)) {

      System.out.println("Waiting for client1");
      Socket socket = listener.accept();
      System.out.println("connected to " + socket.getInetAddress().getHostName());
      ClientConnection clientConnection1 = new ClientConnection(socket);

      System.out.println("Waiting for client2");
      socket = listener.accept();
      System.out.println("connected to " + socket.getInetAddress().getHostName());
      ClientConnection clientConnection2 = new ClientConnection(socket);

      pool.execute(clientConnection1);
      pool.execute(clientConnection2);
      // clientConnection2.run();

    } catch (IOException e) {
      e.printStackTrace();
      // listener.close();
    }

  }

  // private TankData tankAction(TankData tank) {
  // moveTank(tank);
  // checkCollision(tank);
  // return tank;
  // }

  // private TankData moveTank(TankData tank) {
  // tank.setmR((tank.getmR() + (tank.getmDy() < 0 ? -1 * tank.getmDr() :
  // tank.getmDr())) % 360);
  // tank.setmX(tank.getmX() + ((tank.getmDy()) *
  // Math.cos(Math.toRadians(tank.getmR()))));
  // tank.setmY(tank.getmY() + ((tank.getmDy()) *
  // Math.sin(Math.toRadians(tank.getmR()))));
  // return tank;
  // }

  // private TankData checkCollision(TankData tank) {
  // if (tank.getmX() >= width - 30) {
  // tank.setmX(0);
  // } else if (tank.getmX() <= 0) {
  // tank.setmX(width - 30);
  // }

  // if (tank.getmY() <= 0) {
  // tank.setmY(height - 110);
  // } else if (tank.getmY() > height - 110) {
  // tank.setmY(0);
  // }

  // return tank;
  // }
}
