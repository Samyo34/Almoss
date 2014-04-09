package almoss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Addition {
	
	static FileInputStream fis1,fis2;
	static int byteLu;
	
	  //public static void main(String[] argv) throws IOException
		public Addition(File file1, File file2, int type) throws IOException
	  {
		  
		  /* **************Premier fichier**************** */
		  ArrayList<Integer>list1 = new ArrayList<Integer>();
		  byte[] debut = new byte[256];
		  fis1=new FileInputStream(file1);
		  
		  try{
			  	//fis1.skip(256);// Saut des 256 premiers octets (ce ne sont pas ceux contenant les valeurs)
			  for(int i=0;i<256;i++){
				  debut[i]=(byte) fis1.read();
			  }
				while((byteLu=fis1.read())!=-1){// Lecture du fichier jusqu'au bout, octet par octet (8 bytes)
					list1.add(byteLu);// Ajout de la valeur à la liste
				}
			}
			finally{
				fis1.close();
			}
		  
		  
		  
		  /* ***************Second fichier******************* */
		  ArrayList<Integer>list2 = new ArrayList<Integer>();
		  fis2=new FileInputStream(file2);
		  try{
			  	fis2.skip(256);// Saut des 256 premiers octets (ce ne sont pas ceux contenant les valeurs)
				while((byteLu=fis2.read())!=-1){// Lecture du fichier jusqu'au bout, octet par octet (8 bytes)
					list2.add(byteLu);// Ajout de la valeur à la liste
				}
			}
			finally{
				fis2.close();
			}
		  
		  fis1.close();
		  fis2.close();
		  
		  
		  
		  /* ****************Addition******************* */
		  ArrayList<Integer>addition = new ArrayList<Integer>();
		  
		  for(int i = 0; i<list1.size();i++){
			  addition.add(list1.get(i)+list2.get(i));
			  System.out.println(addition.get(i));
		  }
		  
		  
		  /*Ecriture dans un fichier */
		  if (type == 0){
			  File sous = new File("soustraction.txt");
			  File sousdest =  new File("E:/Travail/Projet Almoss/"+"soustraction.txt");
			  sous.createNewFile();
			  copyFile(sous,sousdest);
			  
			  FileWriter soust = new FileWriter(sous);
			  for(int i =0;i<256;i++){
				  soust.write(debut[i]);
			  }
			  for (int i=256; i<addition.size();i++){
					  soust.write(addition.get(i).toString());
					  soust.write("\r\n");  		  
			  }
			  
			  soust.close();
		  }else{
			  File sous = new File("soustraction.mcs");
			  File sousdest =  new File("E:/Travail/Projet Almoss/"+"soustraction.mcs");
			  sous.createNewFile();
			  copyFile(sous,sousdest);
			  
			  FileWriter soust = new FileWriter(sous);
			  for(int i =0;i<256;i++){
				  soust.write(debut[i]);
			  }
			  for (int i=256; i<addition.size();i++){
					  soust.write(addition.get(i).toString());
					  soust.write("\r\n");  		  
			  }
			  
			  soust.close();
			  
		  }
	  }
	  
	  public static boolean copyFile(File source, File dest){
			try{
				// Declaration et ouverture des flux
				java.io.FileInputStream sourceFile = new java.io.FileInputStream(source);
		 
				try{
					java.io.FileOutputStream destinationFile = null;
		 
					try{
						destinationFile = new FileOutputStream(dest);
		 
						// Lecture par segment de 0.5Mo 
						byte buffer[] = new byte[512 * 1024];
						int nbLecture;
		 
						while ((nbLecture = sourceFile.read(buffer)) != -1){
							destinationFile.write(buffer, 0, nbLecture);
						}
					} finally {
						destinationFile.close();
					}
				} finally {
					sourceFile.close();
				}
			} catch (IOException e){
				e.printStackTrace();
				return false; // Erreur
			}
			source.delete();
			return true; // Résultat OK  
		}


}

