package almoss;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PanelAlmoss extends JPanel{
	
	private JTabbedPane onglet;
	
	public PanelAlmoss(){
		super(new GridLayout(1,1));
		onglet = new JTabbedPane();
		JComponent ong1 = makeTextPanel("Panel #1");// Permet d'écrire du texte dans un onglet
		// Ajout d'un onglet (onglet 1)
		onglet.addTab("onglet 1", null,ong1);
		JComponent ong2 = makeTextPanel("Panel #2");
		// Ajout d'un onglet (onglet 2)
		onglet.addTab("onglet 2",null,ong2);
		// Ajoute l'ensemble des onglets dans PanelAlmoss (en organisation GridLayout(1,1)
		this.add(onglet);
		setVisible(true);
	}
	
    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
	
}
