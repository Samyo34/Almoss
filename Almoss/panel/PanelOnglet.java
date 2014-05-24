package panel;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelOnglet extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public PanelOnglet(String s){
		super(false);
		JLabel filler = new JLabel(s);
        filler.setHorizontalAlignment(JLabel.CENTER);
        this.setLayout(new GridLayout(1, 1));
        this.add(filler);
	}

}
