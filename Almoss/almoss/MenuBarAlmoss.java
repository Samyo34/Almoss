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
	JMenuItem ouvrir,para;
	JFileChooser choix,choixpara;
	int returnVal;
	File file;
	static File filePara ;
	boolean filesel = false; // indique si l'utilisateur � selectionner un fichier
	
	public MenuBarAlmoss(){
		super();
	}
	
	public MenuBarAlmoss(final FenetreAlmoss fen) throws FileNotFoundException{
		super();
		JMenu fichier, param;
		/* Création des menus */
		fichier = new JMenu("Fichier");
		param = new JMenu("Parametres");// vide pour le moment
		
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
				}
			}
		});
		
		para = new JMenuItem("Fichier param�tre (.par)");
		para.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				choixpara = new JFileChooser();
				choixpara.showOpenDialog(null);
				filePara = choixpara.getSelectedFile();
			}
		});
		
		
		
		/* Ajout des items au menu */
		fichier.add(ouvrir);
		param.add(para);
		
		
		
		/* Ajouts des menus à la barre de menu */
		this.add(fichier);
		this.add(param);
	
	
	}
	
	public String getFileName(){
		if (filesel){
			return file.getName();
		}else{
			return "Aucun fichier selectionné";
		}
	}
	
	public File getFilePara(){
		return this.filePara;
	}

	

	

}
