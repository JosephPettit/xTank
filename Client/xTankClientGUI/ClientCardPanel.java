package XTankClientGUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class ClientCardPanel extends JPanel {
	private CardLayout cardLayout;
	private BorderLayout borderLayout;
	private JPanel cardPanel;
	
	/**
	 * Create the panel.
	 */
	public ClientCardPanel() {
		super();
		borderLayout = new BorderLayout(0, 0);
		setLayout(borderLayout);
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		add(cardPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Game info here?");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		
		JLabel lblOrMaybeDown = new JLabel("Or Maybe down here?");
		panel_1.add(lblOrMaybeDown);
	}
	
	void addCard(JPanel panel) {
		cardPanel.add(panel);
	}
	
	void cycleCard() {
		cardLayout.next(cardPanel);
	}

}
