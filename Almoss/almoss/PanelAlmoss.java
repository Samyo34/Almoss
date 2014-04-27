package almoss;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PanelAlmoss extends JPanel{
	
	private JTabbedPane onglet;
	JComponent ong1, ong2, ong3, ong4;
	PanelOpe ongOpe = new PanelOpe();
	PanelPliage ongPli =  new PanelPliage();
	
	
	public PanelAlmoss(MenuBarAlmoss menu)  throws IOException {
		super(new GridLayout(1,1));
		
		PanelFit ongFit = new PanelFit(menu);
		onglet = new JTabbedPane();
		ong1 = ongPli;
		// Ajout d'un onglet (onglet 1)
		onglet.addTab("Pliage", null,ong1);
		
		ong2 = ongOpe;
		// Ajout d'un onglet (onglet 2)
		onglet.addTab("Opération",null,ong2);
		
		// Ajout d'un onglet (onglet 3);
		ong3=ongFit;
		onglet.addTab("Fit", null, ong3);
		
		PanelAffiche ongGraphe = new PanelAffiche();
		ong4 = ongGraphe;
		onglet.addTab("Affichage", ong4);
		// Ajoute l'ensemble des onglets dans PanelAlmoss (en organisation GridLayout(1,1))
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
    
    public void changeText(int onglet, String s){
    	JComponent ong;
    	ong = makeTextPanel(s);
    	this.onglet.insertTab("Pliage", null, ong, null, 1);
    	this.onglet.remove(ong1);
    }
	
}
