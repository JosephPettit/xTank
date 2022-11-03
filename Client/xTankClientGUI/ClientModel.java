package XTankClientGUI;

import java.awt.event.KeyEvent;

public class ClientModel {
	private static Tank tank;
	
	public ClientModel(){
		tank = new Tank();
	}
	public Tank getTank() {
		if(tank == null)
			return new Tank();
		return tank;
	}

	public void moveTank() {
		tank.setmR((tank.getmR() + (tank.getmDy() < 0 ? -1 * tank.getmDr() : tank.getmDr())) % 360);
		tank.setmX(tank.getmX() + ((tank.getmDy()) * Math.cos(Math.toRadians(tank.getmR()))));
		tank.setmY(tank.getmY() + ((tank.getmDy()) * Math.sin(Math.toRadians(tank.getmR()))));
	}
	
	public void checkCollision() {
		if(tank.getmX() >= ClientFrame.GAME_WIDTH - 30){
			tank.setmX(0);
		}
		else if(tank.getmX() <= 0){
			tank.setmX(ClientFrame.GAME_WIDTH - 30);
		}

		if(tank.getmY() <= 0){
			tank.setmY(ClientFrame.GAME_HEIGHT - 110);
		}
		else if(tank.getmY() > ClientFrame.GAME_HEIGHT - 110){
			tank.setmY(0);
		}
	}
	
}
