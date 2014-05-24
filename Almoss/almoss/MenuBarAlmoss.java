package almoss;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBarAlmoss extends JMenuBar{

	private static final long serialVersionUID = 1L;
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
		JMenu param;
		/* Creation des menus */
		param = new JMenu("Parametres");// vide pour le moment
		
		
		//Lors d'un clique sur l'item : ouvre une selection de fichier pour un fichier .par
		para = new JMenuItem("Fichier param�tre (.par)");
		para.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				choixpara = new JFileChooser();
				choixpara.showOpenDialog(null);
				filePara = choixpara.getSelectedFile();
			}
		});
		
		
		
		/* Ajout des items au menu */
		param.add(para);
		
		
		
		/* Ajouts des menus à la barre de menu */
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
