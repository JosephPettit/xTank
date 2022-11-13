package xTankServerLogic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;

import javax.swing.JOptionPane;

public class Server implements Runnable {

  private ServerModel serverModel;
  private Integer[] availPlayers;

  public Server(Executor pool) {
    serverModel = new ServerModel(pool);
    availPlayers = new Integer[] { 2, 3, 4 };
  }

  @Override
  public void run() {
    if (false) { // TODO: added for testing
      Integer numPlayers = (Integer) JOptionPane.showInputDialog(null,
          "Server Starting \nEnter total number of players:",
          "xTank Total Players",
          JOptionPane.QUESTION_MESSAGE, null, availPlayers, availPlayers[0]);

      try (var listener = new ServerSocket(58901)) {

        Socket socket = new Socket();
        for (int i = 0; i < numPlayers; i++) {
          System.out.printf("Waiting for player %d to join...\n\n", i + 1);
          socket = listener.accept();
          System.out.printf("Connected to %s \n", socket.getInetAddress().getHostAddress());
          serverModel.addConnection(new ClientConnection(socket, serverModel));
        }
        System.out.println("Starting Server");
        serverModel.startConnections();

      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    else { // TODO: remove added for testing
      try (var listener = new ServerSocket(58901)) {

        Socket socket = new Socket();
        for (int i = 0; i < 2; i++) {
          System.out.printf("Waiting for player %d to join...\n\n", i + 1);
          socket = listener.accept();
          System.out.printf("Connected to %s \n", socket.getInetAddress().getHostAddress());
          serverModel.addConnection(new ClientConnection(socket, serverModel));
        }
        System.out.println("Starting Server");
        serverModel.startConnections();

      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }
}
