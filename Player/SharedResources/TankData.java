package SharedResources;

import java.awt.Rectangle;

public class TankData extends Rectangle {
    private static final long serialVersionUID = 1L;

    private double mDx;
    private double mDy;
    private double mX;
    private double mY;
    private int mR;
    private int mDr;
    private double missileX;
    private double missileY;
    private double missileR;

    private final String tankColor;

    private final int playerNumber;

    public TankData(String tankColor, int playerNumber) {
        this.tankColor = assignColor(tankColor);
        this.playerNumber = playerNumber;
        missileX = -1000;
        missileY = -1000;        
        setSize(20, 20);
        assignStartingLocation();

    }

    private String assignColor(String tankColor) {
        return switch (tankColor) {
            case "Yellow" -> {
                yield "Assets/yellowTank.png";
            }
            case "Red" -> {
                yield "Assets/redTank.png";
            }
            case "Blue" -> {
                yield "Assets/blueTank.png";
            }
            case "Green" -> {
                yield "Assets/greenTank.png";
            }
            default -> null;
        };
    }

    private void assignStartingLocation() {
        switch (playerNumber) {
            case 0 -> {
                mX = 40;
                mY = 60;
                mR = 0;
            }
            case 1 -> {
                mX = 940;
                mY = 60;
                mR = 180;
            }
        }
    }

    

    public double getMissileX() {
        return missileX;
    }

    public void setMissileX(double missileX) {
        this.missileX = missileX;
    }

    public double getMissileY() {
        return missileY;
    }

    public void setMissileY(double missileY) {
        this.missileY = missileY;
    }

    public double getMissileR() {
        return missileR;
    }

    public void setMissileR(double missileR) {
        this.missileR = missileR;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public String getTankColor() {
        return tankColor;
    }

    public double getmDx() {
        return mDx;
    }

    public void setmDx(double mDx) {
        this.mDx = mDx;
    }

    public double getmDy() {
        return mDy;
    }

    public void setmDy(double mDy) {
        this.mDy = mDy;
    }

    public double getX() {
        return mX;
    }

    public void setmX(double mX) {
        this.mX = mX;
    }

    public double getY() {
        return mY;
    }

    public void setmY(double mY) {
        this.mY = mY;
    }

    public int getmR() {
        return mR;
    }

    public void setmR(int mR) {
        this.mR = mR;
    }

    public int getmDr() {
        return mDr;
    }

    public void setmDr(int mDr) {
        this.mDr = mDr;
    }

    @Override
    public String toString() {
        return "TankData [\nmDx=" + mDx + ", mDy=" + mDy + ", mX=" + mX + ", mY=" + mY + ", mR=" + mR + ", mDr=" + mDr
                + ", playerNumber=" + playerNumber + "]";
    }

}
