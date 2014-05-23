package almoss;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLDocument.Iterator;

import com.developpez.adiguba.shell.Shell;

public class ButtonFit extends JButton{
	
	File[] files;
	SelecFichier[] pars;
	PanelLFichier[] panel;
	String curDir = System.getProperty("user.dir");
	//String curDir = "E:\\Travail\\Projet_Almoss\\Almoss";
	
	public ButtonFit(final JComboBox type){
		super("lancer le fit");
		addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO : la commande d'éxecution de fullham
				//			modifier le texte dans les JPanel
				String cmd;
				ExecFit fit = new ExecFit();
				for (int i=0; i<files.length;i++){
					
					panel[i].changeEtat("En cours...");
					try {
						for(File file:getFileFullH()){
							System.out.println(file.getAbsolutePath());
						}
					} catch (HeadlessException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
						int res =0;
						System.out.println(files[i]);
						MenuBarAlmoss para = new MenuBarAlmoss();
						if(pars[i].getFichier() == null){
							//E:\\Travail\\Projet_Almoss\\full\\fullham.exe
							cmd = curDir+"\\Log\\fullham.exe " + files[i].getAbsolutePath() + " " + para.getFilePara() + " "+ type.getSelectedItem();
						}else{
							cmd = curDir+"\\Log\\fullham.exe " + files[i].getAbsolutePath() + " " + pars[i].getFichier().getAbsolutePath() + " "+ type.getSelectedItem();
						}
						fit.setCmd(cmd);
						fit.run();
						//fit.wait();
						panel[i].changeEtat("Terminer");
						
							/* Déplacement des fichiers dans le répertoire approprié */
							int numRep;
							try {
								
								numRep = creerRep();
								deplaceFiles(getFileFullH(),curDir+ "\\File\\Full"+numRep+"\\");
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
		//E:\\Travail\\Projet_Almoss\\Almoss\\
		File file = new File(curDir+"\\File\\Full"+num);
		file.mkdir();
		if(file.exists()){
			file.setReadable(true);
			file.setWritable(true);
		}
		return num;
	}
	
	public int scanRepFile(){
		File repertoire = new File(curDir+"\\File");
		
		File[] list = repertoire.listFiles();
		int valeur;
		int max=0;
		if(list.length==0){
			/* Ne rien Faire */
		}else{
			for(int i=0;i<list.length;i++){
				valeur = Integer.parseInt(Character.toString(list[i].getName().charAt(4)));// récupération du numeros de dossier (Full*)
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
		  source.delete();
		} catch (Exception e) {
		  e.printStackTrace(); // n'importe quelle exception
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
