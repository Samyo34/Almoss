package almoss;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PanelAlmoss extends JPanel{
	
	private JTabbedPane onglet;
	
	public PanelAlmoss(){
		onglet = new JTabbedPane();
		JComponent ong1 = new JComponent() {
		};
		ong1.setBackground(Color.black);
		onglet.addTab("onglet 1", null,ong1);
		onglet.addTab("onglet 2",null,ong1);
		setVisible(true);
	}

}
