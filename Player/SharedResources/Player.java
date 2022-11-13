package SharedResources;

import java.io.Serializable;

/**
 * X-tank player class.
 */
public class Player implements Serializable {
    private int health;
    private final int playerNumber;
    private long lastHit;
    private long current;
    private boolean alive;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
        health = 3;
        alive = true;
    }

    /**
     * If player tank is hit by missile, decrement health by 1.
     * If health is 0, alive = false;
     */
    public void playerHit() {
        current = System.currentTimeMillis();
        if (current >= lastHit + 500) {
            lastHit = current;
            health--;
            System.out.println("I've been hit!");
            if (health == 0) {
                alive = false;
            }
        }

    }

    public boolean isAlive() {
        return alive;
    }

    public int getHealth() {
        return health;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

}
