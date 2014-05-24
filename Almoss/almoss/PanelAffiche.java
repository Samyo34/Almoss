package almoss;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class PanelAffiche extends JPanel{
	
	final SelecFichier ouvr = new SelecFichier("Selection Fichier", this);
	AfficheGraphe graphe = new AfficheGraphe();
	
	JPanel panBout = new JPanel(); //Panel pour le bouton de sélection
	JPanel panGraphe = new JPanel(); //Panel pour le graphe
	JPanel panGroupe = new JPanel(); // Panel qui contient les deux autres panel
	JPanel pan1 = new JPanel();
	JPanel pan2 = new JPanel();
	
	public PanelAffiche() throws FileNotFoundException{
		super();
				
		panGraphe.setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));
		panGraphe.setLayout(new GridLayout());
		
		panBout.setLayout(new GridLayout(1,3));
		panBout.add(pan1);
		panBout.add(ouvr);
		panBout.add(pan2);
		
		
		panGroupe.setLayout(new GridLayout(2,1));
		panGroupe.add(panBout);
		
		this.setLayout(new BorderLayout());
		this.add(panGroupe, BorderLayout.NORTH);
		this.add(panGraphe,BorderLayout.CENTER);
	}
	
	public void affGraphe(File fichier) throws IOException{
		if (fichier.getName().endsWith(".mcs") || fichier.getName().endsWith(".MCS") || fichier.getName().endsWith(".Mcs")){
			graphe.CalculeGraphe(fichier, panGraphe);
		}else if(fichier.getName().endsWith(".dat") || fichier.getName().endsWith(".DAT")){
			graphe.calculeGrapheDat(fichier, panGraphe);
		}
	}
}

