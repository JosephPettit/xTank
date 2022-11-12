package SharedResources;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {
    private ArrayList<TankData> playerTanks;
    private ArrayList<Player> players;

    public GameState() {
        playerTanks = new ArrayList<>();
        players = new ArrayList<>();
    }

    public void playerHit(int playerNumber){
        for(Player player : players){
            if(player.getPlayerNumber() == playerNumber)
                player.playerHit();
        }
    }
    
    public TankData addPlayer(String color, int playerNumber) {
        TankData newTank = new TankData(color, playerNumber);
        players.add(new Player(playerNumber));
        playerTanks.add(newTank);
        return newTank;
    }

    public synchronized ArrayList<TankData> getPlayerTanks() {
        return playerTanks;
    }

    public synchronized ArrayList<Missile> getAllMissiles() {
        ArrayList<Missile> rtnList = new ArrayList<>();
        for (TankData player : playerTanks) {
            rtnList.addAll(player.getMissiles());
        }
        return rtnList;
    }

    @Override
    public String toString() {
        return "GameState [\nplayers=" + playerTanks + "\n]";
    }

}
