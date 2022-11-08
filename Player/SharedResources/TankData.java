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

    public TankData() {
        mX = 40;
        mY = 60;
        mR = 0;
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
                + "]";
    }

}
