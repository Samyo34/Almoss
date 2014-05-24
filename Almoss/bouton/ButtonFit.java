package bouton;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;

import panel.PanelLFichier;
import fonction.ExecFit;
import almoss.MenuBarAlmoss;

public class ButtonFit extends JButton{
	
	File[] files;
	SelecFichier[] pars;
	PanelLFichier[] panel;
	String curDir = System.getProperty("user.dir");// permet de connaitre le repertoire courant de l'application
	
	public ButtonFit(final JComboBox type){
		super("lancer le fit");
		addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String cmd;
				ExecFit fit = new ExecFit();
				for (int i=0; i<files.length;i++){//Pour tout les fichiers selectionne
					
					panel[i].changeEtat("En cours...");
					
						MenuBarAlmoss para = new MenuBarAlmoss(); // premet de recuperer le fichier .par selectionne dans la barre de menu
						if(pars[i].getFichier() == null){ // si l'utilisateur n'a pas fait de selection de fichier dans l'onglet Fit :
							// Utilisation du fichier .par selectioner dans le barre de menu
							cmd = curDir+"\\Log\\fullham.exe " + files[i].getAbsolutePath() + " " + para.getFilePara() + " "+ type.getSelectedItem();
						}else{
							// Utilisation du fichier .par selectionne dans l'onglet
							cmd = curDir+"\\Log\\fullham.exe " + files[i].getAbsolutePath() + " " + pars[i].getFichier().getAbsolutePath() + " "+ type.getSelectedItem();
						}
						fit.setCmd(cmd); // creation d'un thread qui execute fullham
						fit.run();// Execution du thread
						panel[i].changeEtat("Terminer");
						
							/* Déplacement des fichiers dans le répertoire approprié */
							int numRep;
							try {
								
								numRep = creerRep();// creer un repertoire Full*
								// deplace les fichiers creer par Fullham dans le repertoire crée
								deplaceFiles(getFileFullH(),curDir+ "\\File\\Full"+numRep+"\\");
								// Selectionne le fichier fit.delta pour GnuPlot + ajout du bouton gnuplot
								File fileD = new File(curDir+"\\File\\Full"+numRep+"\\fit.delta");
								panel[i].addButton(fileD);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
							
							
							
						
						
						
					}
				}				
			
		});
	}
	
	/*
	 * Recuperation la liste des fichiers selectionne par l'utilisateur
	 */
	public void setFiles(HashMap<Integer, PanelLFichier> map){
		files = new File[map.size()];
		pars = new SelecFichier[map.size()];
		panel = new PanelLFichier[map.size()];
		int i=0;
		for (Map.Entry<Integer,PanelLFichier> e : map.entrySet()) {
			files[i] = e.getValue().getFichier();
			pars[i] = e.getValue().getPar();
			panel[i] = e.getValue();
			i++;
		}
	}
	/*
	 * Creer un répertoire pour les fichiers de fullham (du type : Full1, Full2...)
	 * Cherche dans un premier temps la plus grande valeur  val du rep et creer un rep Full(val+1)
	 */
	public int creerRep() throws IOException{
		int num = (scanRepFile()+1);
		File file = new File(curDir+"\\File\\Full"+num);
		file.mkdir();
		if(file.exists()){
			file.setReadable(true);
			file.setWritable(true);
		}
		return num;
	}
	
	/*
	 * Scan le repertoire File pour connaitre le dernier repertoire :
	 * repertoire sous la forme : Full* (avec * le numeros du repertoire)
	 * return : le * le plus grand 
	 */
	public int scanRepFile(){
		File repertoire = new File(curDir+"\\File");
		
		File[] list = repertoire.listFiles();// recuperation de la liste des fichiers present dans "repertoire"
		int valeur=0;
		int max=0;
		if(list.length==0){
			/* Ne rien Faire */
		}else{
			for(int i=0;i<list.length;i++){
				if(list[i].isDirectory()){
					valeur = Integer.parseInt(Character.toString(list[i].getName().charAt(4)));// récupération du numeros de dossier (Full*)
				}
				if(valeur>=max){
					max = valeur;
				}
			}
		}
		return max;
	}
	
	/*
	 * Retourne la liste des fichiers qui ont était créé par fullham
	 */
	public File[] getFileFullH() throws HeadlessException{
		File repertoire = new File(curDir);
		File[] list = repertoire.listFiles();
		
		File[] fileFull = new File[list.length-3];
		int j=0;// réservé au parcours du tableau fileFull
		for(int i = 0; i<list.length;i++){
			
			if(list[i].getName().equalsIgnoreCase("Almoss.jar")||list[i].getName().equalsIgnoreCase("File")||list[i].getName().equalsIgnoreCase("Log")){
				/* Ne pas les traiter */
			}else{
				fileFull[j]=list[i];
				j++;
			}			
		}
		return fileFull;
		
	}
	
	/*
	 * Deplace les fichiers de files dans destination
	 */
	public void deplaceFiles(File [] files, String destination) throws IOException{
		File fileDest;
		for(int i=0;i<files.length;i++){
			fileDest = new File(destination+files[i].getName());
			System.out.println(fileDest.getAbsolutePath());
			fileDest.createNewFile();
			fileDest.setReadable(true);
			fileDest.setWritable(true);
			deplacer(files[i],fileDest);
		}
	}
	
	/*
	 * Depalace le fichier "source" dans "destination"
	 */
	public void deplacer(File source,File destination) {
		FileChannel in = null; // canal d'entrée
		FileChannel out = null; // canal de sortie
		 
		try {
		  // Init
		  in = new FileInputStream(source).getChannel();
		  out = new FileOutputStream(destination).getChannel();
		 
		  // Copie depuis le in vers le out
		  in.transferTo(0, in.size(), out);
		  in.close();
		  out.close();
		  // Suppression de du fichier source
		  source.delete();
		} catch (Exception e) {
		  e.printStackTrace();
		} finally { // finalement on ferme
		  if(in != null) {
		  	try {
			  in.close();
			} catch (IOException e) {}
		  }
		  if(out != null) {
		  	try {
			  out.close();
			} catch (IOException e) {}
		  }
		}
} 

}
