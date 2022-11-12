package xTankClientGUI;

import java.awt.Image;
import javax.swing.ImageIcon;

import SharedResources.TankData;

public class Tank {
	// private String craft = "Assets/yellowTank.png";

	private TankData data;
	private Image mImage;

	public Tank(TankData data) {
		ImageIcon ii = new ImageIcon(getClass().getResource(data.getTankColor()));
		mImage = ii.getImage();
		this.data = data;
	}

	public void updateTank(TankData data) {
		this.data = data;
	}

	public TankData getData() {
		return data;
	}

	public Image getImage() {
		return mImage;
	}

	// TODO: Move to Missile object / list of missiles
	public double getMissileX() {
        return data.getMissileX();
    }

    public void setMissileX(double missileX) {
        data.setMissileX(missileX);
    }

    public double getMissileY() {
        return data.getMissileY();
    }

    public void setMissileY(double missileY) {
        data.setMissileY(missileY);
    }

    public double getMissileR() {
        return data.getMissileR();
    }

    public void setMissileR(double missileR) {
        data.setMissileR(missileR);
    }

	public int getmR() {
		return data.getmR();
	}

	public double getmDy() {
		return data.getmDy();
	}

	public int getmDr() {
		return data.getmDr();
	}

	public double getmX() {
		return data.getX();
	}

	public double getmY() {
		return data.getY();
	}

	public void setmR(int mR) {
		data.setmR(mR);
	}

	public void setmX(double mX) {
		data.setmX(mX);
	}

	public void setmY(double mY) {
		data.setmY(mY);
	}

	public void setmDy(double mDy) {
		data.setmDy(mDy);
	}

	public void setmDr(int mDr) {
		data.setmDr(mDr);
		;
	}

	public void setmDx(double mDx) {
		data.setmDx(mDx);
	}

	@Override
	public String toString() {
		return "Tank [data=" + data + ", mImage=" + mImage + "]";
	}

}
