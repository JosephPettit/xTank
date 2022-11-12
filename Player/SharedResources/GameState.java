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

    public synchronized ArrayList<Missile> getAllMissiles() {
        ArrayList<Missile> rtnList = new ArrayList<>();
        for (TankData player : players) {
            rtnList.addAll(player.getMissiles());
        }
        return rtnList;
    }

    @Override
    public String toString() {
        return "GameState [\nplayers=" + players + "\n]";
    }

}
