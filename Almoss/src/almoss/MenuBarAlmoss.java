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
	boolean filesel = false; // indique si l'utilisateur � selectionner un fichier
	
	public MenuBarAlmoss(final FenetreAlmoss fen) throws FileNotFoundException{
		super();
		JMenu fichier, edition;
		/* Création des menus */
		fichier = new JMenu("Fichier");
		edition = new JMenu("Edition");// vide pour le moment
		
		/* Création des elements contenu dans les menus */
		ouvrir = new JMenuItem("Ouvrir...");
		ouvrir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				choix = new JFileChooser();
				returnVal = choix.showOpenDialog(null); // on fait apparaitre la fenetre de selection des fichiers
				// returnVal permet de tester si l'utilisateur a cliqu� sur OK ou annuler (ou autres...)
				file = choix.getSelectedFile(); // R�cup�ration du fichier s�l�ctionner
				if (returnVal == JFileChooser.APPROVE_OPTION){
					filesel = true;
					fen.changeTextOnglet(1, getFileName());
				}
			}
		});
		
		
		
		/* Ajout des items au menu */
		fichier.add(ouvrir);
		
		/* Ajouts des menus à la barre de menu */
		this.add(fichier);
		this.add(edition);
	
	
	}
	
	public String getFileName(){
		if (filesel){
			return file.getName();
		}else{
			return "Aucun fichier selectionné";
		}
	}

	

	

}
