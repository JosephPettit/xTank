package SharedResources;

import java.io.Serializable;

public class TankData implements Serializable {
    private static final long serialVersionUID = 1L;

    private double mDx;
    private double mDy;
    private double mX;
    private double mY;
    private int mR;
    private int mDr;

    private final String tankColor;

    private final int playerNumber;

    public TankData(String tankColor, int playerNumber) {
        this.tankColor = assignColor(tankColor);
        this.playerNumber = playerNumber;

        // assignColor();
        assignStartingLocation();
        mX = 40;
        mY = 60;
        mR = 0;
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
                mX = 240;
                mY = 60;
                mR = 180;
            }
        }
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

    public double getmX() {
        return mX;
    }

    public void setmX(double mX) {
        this.mX = mX;
    }

    public double getmY() {
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
        return "TankData [mDx=" + mDx + ", mDy=" + mDy + ", mX=" + mX + ", mY=" + mY + ", mR=" + mR + ", mDr=" + mDr
                + ", tankColor=" + tankColor + ", playerNumber=" + playerNumber + "]";
    }

}
