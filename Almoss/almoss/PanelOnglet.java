package almoss;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelOnglet extends JPanel {
	
	public PanelOnglet(String s){
		super(false);
		JLabel filler = new JLabel(s);
        filler.setHorizontalAlignment(JLabel.CENTER);
        this.setLayout(new GridLayout(1, 1));
        this.add(filler);
	}

}
