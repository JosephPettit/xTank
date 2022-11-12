package SharedResources;

import java.io.Serializable;

public class Player implements Serializable{
    private int health;
    private final int playerNumber;
    private long lastHit;
    private long current;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
        health = 3;
    }

    public void playerHit() {
        current = System.currentTimeMillis();
        if (current >= lastHit + 20) {
            lastHit = current;
            health--;
            System.out.println("I've been hit!");
        }

    }

    public int getHealth() {
        return health;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

}
