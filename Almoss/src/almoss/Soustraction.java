package almoss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Soustraction {
	
	//static File file1 = new File("E:/Travail/Projet Almoss/spectre3.MCS");
	//static File file2 = new File("E:/Travail/Projet Almoss/SPECTRE.Mcs");
	static FileInputStream fis1,fis2;
	static int byteLu;
	static byte[] buffer = new byte[4];
	static byte[] buffer2 = new byte[4];
	
	  //public static void main(String[] argv) throws IOException
		public Soustraction(File file1, File file2, int type) throws IOException
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
				while((byteLu=fis1.read(buffer))!=-1){// Lecture du fichier jusqu'au bout, octet par octet (8 bytes)
					int oct = byteArrayToInt(buffer);
					list1.add(oct);// Ajout de la valeur � la liste
				}
				int m1=(list1.get(0)+list1.get(1)+list1.get(2)+list1.get(3)+list1.get(4)+list1.get(5)+list1.get(6)+list1.get(7))/8;
				list1.remove(0);
				list1.add(0,m);
			}
			finally{
				fis1.close();
			}
		  
		  
		  
		  /* ***************Second fichier******************* */
		  ArrayList<Integer>list2 = new ArrayList<Integer>();
		  fis2=new FileInputStream(file2);
		  try{
			  	fis2.skip(256);// Saut des 256 premiers octets (ce ne sont pas ceux contenant les valeurs)
				while((byteLu=fis2.read(buffer))!=-1){// Lecture du fichier jusqu'au bout, octet par octet (8 bytes)
					int o = byteArrayToInt(buffer);
					list2.add(o);// Ajout de la valeur � la liste
				}
				int m2=(list2.get(0)+list2.get(1)+list2.get(2)+list2.get(3)+list2.get(4)+list2.get(5)+list2.get(6)+list2.get(7))/8;
				list2.remove(0);
				list2.add(0,m);
			}
			finally{
				fis2.close();
			}
		  
		  fis1.close();
		  fis2.close();
		  
		  
		  
		  /* ****************Soustraction******************* */
		  ArrayList<Integer>soustraction = new ArrayList<Integer>();
		  
		  for(int i = 0; i<list1.size();i++){
			  soustraction.add(100000+list1.get(i)-list2.get(i));
			  System.out.println(soustraction.get(i));
		  }
		  
		  
		  /*Ecriture dans un fichier */
		  if (type == 0){
			  File sous = new File("soustraction.txt");
			  File sousdest =  new File("E:/Travail/Projet Almoss/"+"soustraction.txt");
			  sous.createNewFile();
			  copyFile(sous,sousdest);
			  
			  FileWriter soust = new FileWriter(sous);
			  
			  /*for(int i =0;i<256;i++){
				  soust.write(debut[i]);
			  }*/
			  
			  for (int i=0; i<soustraction.size();i++){
				  soust.write(soustraction.get(i).toString());
				  soust.write("\r\n");  		  
			  }
			  
			  soust.close();
		  }else{
			  File sous = new File("addition.mcs");
			  File sousdest =  new File("E:/Travail/Projet Almoss/"+"addition.mcs");
			  sous.createNewFile();
			  copyFile(sous,sousdest);
			  
			  FileWriter soust = new FileWriter(sous);
			  for(int i =0;i<256;i++){
				  soust.write(debut[i]);
			  }
			  for (int i=256; i<soustraction.size();i++){
				  soust.write(soustraction.get(i));
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
			return true; // R�sultat OK  
		}
	  public static int byteArrayToInt (byte[] b)
	  {
	      int value = 0;
	      for (int i = 0; i < 4; i++)
	      {
	          int n = (b[i] < 0 ? (int) b[i] + 256 : (int)b[i]) << (8 * i);
	          value += n;
	      }
	      return value;
	  }


}
