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

    public synchronized ArrayList<TankData> getPlayers() {
        return players;
    }

    public synchronized void updatePlayer(TankData player) {
        players.remove(player.getPlayerNumber());
        players.add(player.getPlayerNumber(), player);
    }

    @Override
    public String toString() {
        return "GameState [\nplayers=" + players + "\n]";
    }

}
