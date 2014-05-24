package panel;

import java.awt.GridLayout;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;

import bouton.ButtonGnu;
import bouton.SelecFichier;

/*
 * Panel comportant les lignes de la liste des fichiers à traiter
 */

public class PanelLFichier extends JPanel {

	private static final long serialVersionUID = 1L;
	File fichier;
	SelecFichier par;
	JLabel nomF, nomEtat;
	JPanel panel;
	
	 boolean existe = false; // Vrai si le bouton gnuplot existe déjà
	
	public PanelLFichier(JLabel nom, JLabel etat, JPanel pane){
		super();
		nomF = nom;
		nomEtat = etat;
		panel =pane;
		GridLayout gl = new GridLayout(1,3);
		gl.setHgap(5); //Cinq pixels d'espace entre les colonnes
		gl.setVgap(5); //Cinq pixels d'espace entre les lignes
		setLayout(gl);
		add(nomF);// Ajout du nom du fichier
		add(nomEtat);// Ajout de l'etat du traitement (en cours, en attente, termine...)
		add(panel);// Ajout des boutons suppressions, select .par, (et gnuPlot quand le traitement est termine) contenu dans panel
	}
	
	/*
	 * change l'etat du traitement
	 */
	public void changeEtat(String s){
		nomEtat = new JLabel(s);
		removeAll();
		add(nomF);
		add(nomEtat);
		add(panel);
		revalidate();
		repaint();
		
	}
	
	public void setFiles(File fich, SelecFichier para){
		fichier = fich;
		par = para;
	}
	
	// Ajoute le bouton pour l'excution de gnuplot
	public void addButton(File fileDelta){
		if(!existe){ //si le bouton n'existe pas déjà :
			ButtonGnu gnu = new ButtonGnu(fileDelta);
			panel.add(gnu);
			revalidate();
			repaint();
			existe=true;
		}
		
	}
	
	public SelecFichier getPar(){
		return par;
	}
	
	public File getFichier(){
		return fichier;
	}

}
