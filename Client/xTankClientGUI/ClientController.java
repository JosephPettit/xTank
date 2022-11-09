package xTankClientGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ClientController {

  private ClientFrame clientFrame;
  private ServerConnection serverConnection;
  private Tank tank;

  public ClientController(ClientFrame clientFrame, ServerConnection serverConnection) {
    this.clientFrame = clientFrame;
    this.serverConnection = serverConnection;
    this.clientFrame.cycleCard();
    this.clientFrame.setVisible(true);
    this.tank = initialTank();
    addPlayerTank();
    setupListeners();
  }

  private Tank initialTank() {
    return new Tank(serverConnection.getInitialTankData());
    // return null;
  }

  private void addPlayerTank() {
    clientFrame.addPlayerTank(tank);
  }

  private void setupListeners() {
    addGameListener();
    addGameTimerListener();
  }

  private void addGameListener() {
    clientFrame.addGamePanelKeyListener(
        new KeyAdapter() {
          public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if ((key == KeyEvent.VK_LEFT) || (key == KeyEvent.VK_RIGHT)) {
              tank.setmDr(0);
            }
            if ((key == KeyEvent.VK_UP) || (key == KeyEvent.VK_DOWN)) {
              tank.setmDx(0);
              tank.setmDy(0);
            }
          }

          public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_UP) {
              tank.setmDy(1);
            }

            if (key == KeyEvent.VK_DOWN) {
              tank.setmDy(-1);
            }

            if (key == KeyEvent.VK_LEFT) {
              tank.setmDr(-1);
            }

            if (key == KeyEvent.VK_RIGHT) {
              tank.setmDr(1);
            }
            try {
              tank.updateTank(serverConnection.updateTank(tank.getData()));
            } catch (ClassNotFoundException | IOException e1) {
              clientFrame.displayErrorMessage(e1.toString());
              e1.printStackTrace();
            }
          }
        });
  }

  private void addGameTimerListener() {
    clientFrame.addGamePanelTimerListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              tank.updateTank(serverConnection.updateTank(tank.getData()));
              // serverModel.checkCollision(tank.getData());
            } catch (IOException | ClassNotFoundException e1) {
              clientFrame.displayErrorMessage(e1.toString());
              e1.printStackTrace();
            }
            clientFrame.repaint();
          }
        });
  }
}
