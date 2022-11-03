package XTankServerGUI;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

public class ServerController {
	private ServerFrame serverFrame;
	private ServerModel serverModel;
	boolean running = true;

	public ServerController(ServerFrame serverFrame, ServerModel serverModel) {
		this.serverFrame = serverFrame;
		this.serverModel = serverModel;

		try {
			setupListeners();
			this.serverFrame.setIPAddress(serverModel.getIPAddress());
		} catch (UnknownHostException e) {
			serverFrame.updateLog(e.toString());
			e.printStackTrace();
			serverFrame.updateLog(
					"\nWell this is embarrassing, something went wrong.\n\nPlease close and re-open this window.");
			serverFrame.displayErrorMessage("Unable to local IP address \nidk how to fix that... \n¯\\_(ツ)_/¯ ");
		}
	}

	private void setupListeners() throws UnknownHostException {

		addServerButtonListener();
		addCopyButtonListener();
	}

	private void addCopyButtonListener() throws UnknownHostException {
		this.serverFrame.addCopyButtonListener(new ActionListener() {
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			StringSelection selection = new StringSelection(serverModel.getIPAddress());

			@Override
			public void actionPerformed(ActionEvent e) {
				clipboard.setContents(selection, null);
			}

		});
	}

	private void addServerButtonListener() {
		serverFrame.addServerButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				serverModel.setPlayers(serverFrame.retrievePlayers());
				serverFrame.cycleCard();
				serverFrame.updateLog(Integer.toString(serverModel.getPlayers()));

				if (running) {
					serverFrame.updateServerButtonText("End Server");
					running = false;
				} else {
					running = true;
					serverFrame.updateServerButtonText("Start Server");
				}
			}
		});
	}

}
