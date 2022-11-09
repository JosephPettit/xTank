package SharedResources;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {
    private ArrayList<TankData> players;

    public GameState() {
        players = new ArrayList<>();
    }

    public TankData addPlayer(String color, int playerNumber) {
        TankData newPlayer = new TankData(color, playerNumber);
        players.add(newPlayer);
        return newPlayer;
    }
}
