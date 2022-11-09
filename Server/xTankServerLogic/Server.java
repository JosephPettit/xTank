package xTankServerLogic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;

public class Server implements Runnable {

  private ServerModel serverModel;

  public Server(Executor pool) {
    serverModel = new ServerModel(pool);
  }

  @Override
  public void run() {
    try (var listener = new ServerSocket(58901)) {

      System.out.println("Waiting for client1");
      Socket socket = listener.accept();
      System.out.println("connected to " + socket.getInetAddress().getHostAddress());
      ClientConnection clientConnection1 = new ClientConnection(socket, serverModel);

      System.out.println("Waiting for client2");
      socket = listener.accept();
      System.out.println("connected to " + socket.getInetAddress().getHostAddress());
      ClientConnection clientConnection2 = new ClientConnection(socket, serverModel);
      System.out.println("Adding clients to server");
      serverModel.addConnection(clientConnection1);
      serverModel.addConnection(clientConnection2);

      System.out.println("Starting Server");
      serverModel.startConnections();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
