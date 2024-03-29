package xTankClientGUI;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.ImageIcon;

import GameMaps.GameMap;
import SharedResources.GameState;
import SharedResources.TankData;

/**
 * X-Tank Client window controller
 */
public class ClientController {

  private ClientFrame clientFrame;
  private ServerConnection serverConnection;
  private TankData tank;
  private GameState gameState;
  private final int playerNumber;
  private GameMap gameMap;

  public ClientController(ClientFrame clientFrame, ServerConnection serverConnection) {
    this.clientFrame = clientFrame;
    this.serverConnection = serverConnection;
    this.clientFrame.setIconImage(assignIcon(serverConnection.getColor()));
    this.clientFrame.setVisible(true);
    this.gameState = serverConnection.getInitialGameState();
    this.gameMap = serverConnection.getGameMap();
    this.playerNumber = serverConnection.getPlayerNumber();
    this.tank = getGSTank();
    this.clientFrame.setTitle("X-Tank Client: Player " + (playerNumber + 1));
    addGameStartInfo();
    setupListeners();
  }

  private TankData getGSTank() {
    return gameState.getPlayerTanks().get(playerNumber);
  }

  private void addGameStartInfo() {
    clientFrame.addGameMap(gameMap);
    clientFrame.addGameState(gameState);
  }

  private void setupListeners() {
    addGameListener();
    addGameTimerListener();
  }

  /**
   * Adds game listener for key presses and releases.
   */
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
            if (key == KeyEvent.VK_SPACE) {
              tank.fire();
            }
          }
        });
  }

  /**
   * Adds timer listener. GameState updates at the end of every timer cycle.
   */
  private void addGameTimerListener() {

    clientFrame.addGamePanelTimerListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              gameState = serverConnection.updateGameState(gameState);
              tank = gameState.getPlayerTanks().get(playerNumber);
              addGameStartInfo();
              clientFrame.repaint();
            } catch (IOException | ClassNotFoundException e1) {
              clientFrame.displayErrorMessage(e1.toString());
              clientFrame.displayErrorMessage("This error is irrecoverable \nGame will now close");
              System.exit(-1);
            }
          }
        });
  }

  /**
   * Assigns tank image paths from user's selection
   */
  private Image assignIcon(String tankColor) {
    String image = null;
    switch (tankColor) {
      case "Yellow" -> {
        image = "Assets/yellowTank.png";
      }
      case "Red" -> {
        image = "Assets/redTank.png";
      }
      case "Blue" -> {
        image = "Assets/blueTank.png";
      }
      case "Green" -> {
        image = "Assets/greenTank.png";
      }
    }

    return new ImageIcon(getClass().getResource(image)).getImage();
  }

}
