package almoss;

import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class PanelAffiche extends JPanel{
	
	final SelecFichier ouvr = new SelecFichier("Selection Fichier", this);
	AfficheGraphe graphe = new AfficheGraphe();
	
	JPanel panBout = new JPanel(); //Panel pour le bouton de sélection
	JPanel panGraphe = new JPanel(); //Panel pour le graphe
	
	public PanelAffiche() throws FileNotFoundException{
		super();
		
		panGraphe.setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));
		panGraphe.setLayout(new GridLayout());
		
		panBout.add(ouvr);
		
		this.setLayout(new GridLayout(2, 1));
		this.add(ouvr);
		this.add(panGraphe);
	}
	
	public void affGraphe(File fichier) throws FileNotFoundException{
		graphe.CalculeGraphe(fichier, panGraphe);
	}
}
