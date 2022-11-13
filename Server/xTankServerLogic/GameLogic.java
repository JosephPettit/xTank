package xTankServerLogic;

import SharedResources.GameState;
import SharedResources.Player;

public class GameLogic {

    public static boolean checkWinner(GameState game) {
        int temp = 0;
        for (Player player : game.getPlayers()) {
            if (player.isAlive()) {
                temp++;
            }
            if (temp > 1)
                return false;
        }
        System.out.println("check winner here");
        return true;
    }
}
