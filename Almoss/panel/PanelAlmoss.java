package panel;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import almoss.MenuBarAlmoss;

/*
 * Panel dans le quel se trouve les onglets des differents outils proposes
 */

public class PanelAlmoss extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JTabbedPane onglet;
	JComponent ong1, ong2, ong3, ong4;
	PanelOpe ongOpe = new PanelOpe();
	PanelPliage ongPli =  new PanelPliage();
	
	
	public PanelAlmoss(MenuBarAlmoss menu)  throws IOException {
		super(new GridLayout(1,1));
		
		PanelFit ongFit = new PanelFit(menu);
		onglet = new JTabbedPane();
		
		// Ajout de l'onglet pour le pliage
		ong1 = ongPli;
		onglet.addTab("Pliage", null,ong1);
		
		//Ajout de l'onglet pour les opérations
		ong2 = ongOpe;
		onglet.addTab("Opération",null,ong2);
		
		// Ajout de l'onglet pour l'affichage des grahes
		PanelAffiche ongGraphe = new PanelAffiche();
		ong3 = ongGraphe;
		onglet.addTab("Affichage", ong3);
		
		// Ajout de l'onglet pour le fit multiple
		PanelMulti ongMulti = new PanelMulti();
		ong4= ongMulti;
		onglet.addTab("Traiter plusieurs fichiers", ong4);
		
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
