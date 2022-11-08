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
}
