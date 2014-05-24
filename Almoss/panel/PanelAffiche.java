package panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import bouton.SelecFichier;

public class PanelAffiche extends JPanel{
	

	private static final long serialVersionUID = 1L;
	final SelecFichier ouvr = new SelecFichier("Selection Fichier", this);
	AfficheGraphe graphe = new AfficheGraphe();
	
	JPanel panBout = new JPanel(); //Panel pour le bouton de sélection
	JPanel panGraphe = new JPanel(); //Panel pour le graphe
	JPanel panGroupe = new JPanel(); // Panel qui contient les deux autres panel
	JPanel pan1 = new JPanel();// Panel pour la mise en forme
	JPanel pan2 = new JPanel();// Panel pour la mise en forme
	
	public PanelAffiche() throws FileNotFoundException{
		super();
		
		//parametrage du Panel pour le graphe 
		panGraphe.setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));// Ajout de Bordure
		panGraphe.setLayout(new GridLayout());
		
		//Creation du Panel pour le bouton
		panBout.setLayout(new GridLayout(1,3));
		panBout.add(pan1);
		panBout.add(ouvr);// On encadre le bouton de deux panel (pour qu'il soit au de son panel centre)
		panBout.add(pan2);
		
		// Ajout du panel pour le bouton dans un autre Panel (facilite le positionnement)
		panGroupe.setLayout(new GridLayout(2,1));
		panGroupe.add(panBout);
		
		// Ajout des panel bouton et graphe
		this.setLayout(new BorderLayout());
		this.add(panGroupe, BorderLayout.NORTH);
		this.add(panGraphe,BorderLayout.CENTER);
	}
	/*
	 * Affiche le graphe correspondant au fichier "fichier"
	 */
	public void affGraphe(File fichier) throws IOException{
		if (fichier.getName().endsWith(".mcs") || fichier.getName().endsWith(".MCS") || fichier.getName().endsWith(".Mcs")){
			graphe.CalculeGraphe(fichier, panGraphe);// si c'est un fichier mcs
		}else if(fichier.getName().endsWith(".dat") || fichier.getName().endsWith(".DAT")){
			graphe.calculeGrapheDat(fichier, panGraphe);// si c'est un fichier dat
		}
	}
}

