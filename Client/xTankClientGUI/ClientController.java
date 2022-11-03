package XTankClientGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ClientController {

	private ClientFrame clientFrame;
	private ClientModel clientModel;

	public ClientController(ClientFrame clientFrame, ClientModel clientModel) {
		this.clientFrame = clientFrame;
		this.clientModel = clientModel;
		this.clientFrame.cycleCard();
		addPlayerTank();
		setupListeners();
	}

	private void addPlayerTank() {
		clientFrame.addPlayerTank(clientModel.getTank());
	}
	
	private void setupListeners() {
		addGameListener();
		addGameTimerListener();
	}

	private void addGameListener() {
		clientFrame.addGamePanelKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();

				if ((key == KeyEvent.VK_LEFT) || (key == KeyEvent.VK_RIGHT)) {
					clientModel.getTank().setmDr(0);
				}
				if ((key == KeyEvent.VK_UP) || (key == KeyEvent.VK_DOWN)) {
					clientModel.getTank().setmDx(0);
					clientModel.getTank().setmDy(0); 
				}

			}

			public void keyPressed(KeyEvent e) {
//				System.out.println(e.getExtendedKeyCode());
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_UP) {

					clientModel.getTank().setmDy(1);
				}

				if (key == KeyEvent.VK_DOWN) {
					clientModel.getTank().setmDy(-1);
				}

				if (key == KeyEvent.VK_LEFT) {
					clientModel.getTank().setmDr(-1);
				}

				if (key == KeyEvent.VK_RIGHT) {
					clientModel.getTank().setmDr(1);
				}
				
			}
		});
	}
	
	private void addGameTimerListener() {
		clientFrame.addGamePanelTimerListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clientModel.moveTank();
				clientModel.checkCollision();
				clientFrame.repaint();
			}
			
		});
	}
}
