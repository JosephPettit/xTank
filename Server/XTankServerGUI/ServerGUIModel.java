package XTankServerGUI;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class ServerGUIModel {
	private int players;
	private String IPAddress;
	private ServerSocket serverSocket;

	public String getIPAddress() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}

	public int getPlayers() {
		return players;
	}

	public void setPlayers(int players) {
		this.players = players;
	}
}
