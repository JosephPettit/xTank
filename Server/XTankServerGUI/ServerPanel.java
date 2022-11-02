
package XTankServerGUI;

public enum ServerPanel {
	SETTINGS_PANEL("ServerSettingsPanel"), RUNNING_PANEL("ServerRunningPanel");

	private final String panelName;

	ServerPanel(String name) {
		this.panelName = name;
	}

	public String getPanelName() {
		return panelName;
	}

}
