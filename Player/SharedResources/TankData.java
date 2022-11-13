package SharedResources;

import java.awt.Rectangle;
import java.util.ArrayList;

public class TankData extends Rectangle {
    private static final long serialVersionUID = 1L;

    private double mDx;
    private double mDy;
    private double mX;
    private double mY;
    private int mR;
    private int mDr;

    private final String tankColor;

    private final int playerNumber;

    private ArrayList<Missile> missiles;

    public TankData(String tankColor, int playerNumber) {
        this.tankColor = assignColor(tankColor);
        this.playerNumber = playerNumber;
        this.missiles = new ArrayList<>();
        setSize(40, 40);
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

    // TODO: add starting locations for player 3 & 4
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

    public void fire() {
        if (missiles.size() >= 5) {
            for (Missile missile : missiles) {
                if (missile.isExploded())
                    missiles.remove(missile);
            }
            if (missiles.size() >= 5)
                missiles.remove(0);
        }

        missiles.add(new Missile(this));

    }

    public synchronized ArrayList<Missile> getMissiles() {
        return missiles;
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
