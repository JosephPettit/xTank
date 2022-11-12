package SharedResources;

import java.awt.Rectangle;

public class Missile extends Rectangle {

    private final int playerNumber;
    private double x, y;
    private int r;
    private boolean exploded;

    public Missile(TankData parent) {
        this.playerNumber = parent.getPlayerNumber();
        this.x = parent.getX();
        this.y = parent.getY();
        this.r = parent.getmR();
        setSize(20, 20);
        exploded = false;
    }

    public void explode() {
        exploded = true;
    }

    public boolean isExploded() {
        return exploded;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
