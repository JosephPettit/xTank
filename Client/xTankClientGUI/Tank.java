package xTankClientGUI;

import java.awt.Image;
import javax.swing.ImageIcon;

import SharedResources.TankData;

public class Tank {
	private TankData data;
	private Image mImage;

	// TODO: can maybe be deleted if gamePanel is updated to include tank images
	// from TankData
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
