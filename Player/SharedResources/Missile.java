package SharedResources;

import java.awt.Rectangle;

public class Missile extends Rectangle {

    private final int playerNumber;
    private double x, y;
    private int r;
    private boolean exploded;
    private int distance;
    private final int MAX_DISTANCE = 500;

    public Missile(TankData parent) {
        this.playerNumber = parent.getPlayerNumber();
        this.x = parent.getX();
        this.y = parent.getY();
        this.r = parent.getmR();
        setSize(20, 20);
        exploded = false;
        this.distance = 0;
    }

    

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
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

    public int getMAX_DISTANCE() {
        return MAX_DISTANCE;
    }
}
