package almoss;

import java.awt.event.*;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBarAlmoss extends JMenuBar{
	JMenuItem ouvrir;
	JFileChooser choix;
	
	public MenuBarAlmoss(){
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
				choix.showOpenDialog(null);
			}
		});
		
		/* Ajout des items au menu */
		fichier.add(ouvrir);
		
		/* Ajouts des menus à la barre de menu */
		this.add(fichier);
		this.add(edition);
	
	
	}

	

	

}
