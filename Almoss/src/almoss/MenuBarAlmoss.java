package almoss;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBarAlmoss extends JMenuBar{
	JMenuItem ouvrir;
	JFileChooser choix;
	int returnVal;
	File file ;
	boolean filesel = false; // indique si l'utilisateur à selectionner un fichier
	
	public MenuBarAlmoss(final FenetreAlmoss fen) throws FileNotFoundException{
		super();
		JMenu fichier, edition;
		/* CrÃ©ation des menus */
		fichier = new JMenu("Fichier");
		edition = new JMenu("Edition");// vide pour le moment
		
		/* CrÃ©ation des elements contenu dans les menus */
		ouvrir = new JMenuItem("Ouvrir...");
		ouvrir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				choix = new JFileChooser();
				returnVal = choix.showOpenDialog(null); // on fait apparaitre la fenetre de selection des fichiers
				// returnVal permet de tester si l'utilisateur a cliqué sur OK ou annuler (ou autres...)
				file = choix.getSelectedFile(); // Récupération du fichier séléctionner
				if (returnVal == JFileChooser.APPROVE_OPTION){
					filesel = true;
					fen.changeTextOnglet(1, getFileName());
				}
			}
		});
		
		
		
		/* Ajout des items au menu */
		fichier.add(ouvrir);
		
		/* Ajouts des menus Ã  la barre de menu */
		this.add(fichier);
		this.add(edition);
	
	
	}
	
	public String getFileName(){
		if (filesel){
			return file.getName();
		}else{
			return "Aucun fichier selectionner";
		}
	}

	

	

}
