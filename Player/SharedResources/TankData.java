package SharedResources;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * X-Tank TankData class.
 * 
 * Holds all tank location data and image information.
 */
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
        setSize(20, 20);
        assignStartingLocation();
    }

    /**
     * Assigns Players tank color
     * 
     * @param tankColor
     * @return
     */
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
                mX = 1200;
                mY = 60;
                mR = 180;
            }
            case 2 -> {
                mX = 40;
                mY = 460;
                mR = 0;
            }
            case 3 -> {
                mX = 1200;
                mY = 460;
                mR = 180;
            }
        }
    }

    /**
     * Fires missile from tank.
     * 
     * If more than 5 missiles fired, last fired missile is destroyed.
     */
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

}
