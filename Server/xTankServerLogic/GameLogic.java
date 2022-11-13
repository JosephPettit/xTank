package xTankServerLogic;

import SharedResources.GameState;
import SharedResources.Player;

public class GameLogic {

    /**
     * Checks {@code GameState} for current winner.
     * 
     * @param game {@code GameState} object to be evaluated. 
     * @return {@code True} if only one player is remaining. 
     */
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
