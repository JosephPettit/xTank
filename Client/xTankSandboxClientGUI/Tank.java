package xTankSandboxClientGUI;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Tank {
	private String craft = "Assets/greenTank.png";

	private double mDx;
	private double mDy;
	private double mX;
	private double mY;
	private int mR;
	private int mDr;

	private Image mImage;

	public Tank() {
		ImageIcon ii = new ImageIcon(getClass().getResource(craft));
		mImage = ii.getImage();
		mX = 40;
		setmY(60);
		setmR(0);
	}

	public Image getImage() {
		return mImage;
	}

	public int getmR() {
		return mR;
	}

	public double getmDy() {
		return mDy;
	}

	public int getmDr() {
		return mDr;
	}

	public double getmX() {
		return mX;
	}

	public double getmY() {
		return mY;
	}

	public void setmR(int mR) {
		this.mR = mR;
	}

	public void setmX(double mX) {
		this.mX = mX;
	}

	public void setmY(double mY) {
		this.mY = mY;
	}

	public void setmDy(double mDy) {
		this.mDy = mDy;
	}

	public void setmDr(int mDr) {
		this.mDr = mDr;
	}

	public void setmDx(double mDx) {
		this.mDx = mDx;
	}

	@Override
	public String toString() {
		return "Tank [craft=" + craft + ", mDx=" + mDx + ", mDy=" + mDy + ", mX=" + mX + ", mY=" + mY + ", mR=" + mR
				+ ", mDr=" + mDr + ", mImage=" + mImage + "]";
	}

}
