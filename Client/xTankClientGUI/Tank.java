package xTankClientGUI;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Tank {
	private String craft = "greenTank.png";

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

//	public void move() {
//
//		setmR((getmR() + (getmDy() < 0 ? -1 * getmDr() : getmDr())) % 360);
//
//		mX += ((getmDy()) * Math.cos(Math.toRadians(getmR())));
//		setmY(getmY() + ((getmDy()) * Math.sin(Math.toRadians(getmR()))));
//
//		 System.out.println("mx = " + mX + ", my = " + mY + ", mr = " + mR);
//	}

	public Image getImage() {
		return mImage;
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP) {

			mDy = 1;
		}

		if (key == KeyEvent.VK_DOWN) {
			mDy = -1;
		}

		if (key == KeyEvent.VK_LEFT) {
			mDr = -1;
		}

		if (key == KeyEvent.VK_RIGHT) {
			mDr = 1;
		}
	}
//
//	public void keyReleased(KeyEvent e) {
//		int key = e.getKeyCode();
//
//		if ((key == KeyEvent.VK_LEFT) || (key == KeyEvent.VK_RIGHT)) {
//			mDr = 0;
//		}
//		if ((key == KeyEvent.VK_UP) || (key == KeyEvent.VK_DOWN)) {
//			mDx = 0;
//			mDy = 0;
//		}
//	}

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
