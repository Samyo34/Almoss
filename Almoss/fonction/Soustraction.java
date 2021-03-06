package fonction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;

/*
 * Class premettant la soustraction de fichiers Mcs
 * Le resultat est ecrit dans un fichier
 */
public class Soustraction {
	
	static FileInputStream fis1,fis2;
	static int byteLu;
	static byte[] buffer = new byte[4];
	static byte[] buffer2 = new byte[4];
	String curDir = System.getProperty("user.dir");

		public Soustraction(ArrayList<File> listFichier, int type, JPanel graphe) throws IOException
	  {
		  
		  /* **************Premier fichier**************** */
		  ArrayList<Integer>list1 = new ArrayList<Integer>();
		  byte[] debut = new byte[256];
		  fis1=new FileInputStream(listFichier.get(0));
		  
		  try{
			  for(int i=0;i<256;i++){
				  debut[i]=(byte) fis1.read();
			  }
				while((byteLu=fis1.read(buffer))!=-1){// Lecture du fichier jusqu'au bout, octet par octet (8 bytes)
					int oct = byteArrayToInt(buffer);
					list1.add(oct);// Ajout de la valeur � la liste
				}
				int m1=(list1.get(0)+list1.get(1)+list1.get(2)+list1.get(3)+list1.get(4)+list1.get(5)+list1.get(6)+list1.get(7))/8;
				list1.remove(0);
				list1.add(0,m1);
			}
			finally{
				fis1.close();
			}
		  
		  
		  
		  /* ***************Second fichier******************* */
		  ArrayList<ArrayList<Integer>> listDeList = new ArrayList<ArrayList<Integer>>();
		  ArrayList<Integer>list2 = new ArrayList<Integer>();
		  for(int i=1; i<listFichier.size();i++){
			  list2.clear();
			  fis2=new FileInputStream(listFichier.get(i));
			  try{
				  	fis2.skip(256);// Saut des 256 premiers octets (ce ne sont pas ceux contenant les valeurs)
					while((byteLu=fis2.read(buffer))!=-1){// Lecture du fichier jusqu'au bout, octet par octet (8 bytes)
						int o = byteArrayToInt(buffer);
						list2.add(o);// Ajout de la valeur � la liste
					}
					int m2=(list2.get(0)+list2.get(1)+list2.get(2)+list2.get(3)+list2.get(4)+list2.get(5)+list2.get(6)+list2.get(7))/8;
					list2.remove(0);
					list2.add(0,m2);
					listDeList.add(list2);
				}
				finally{
					fis2.close();
				}
			  
		  }
		  
		  fis1.close();
		  
		  
		  
		  /* ****************Soustraction******************* */
		  ArrayList<Integer>soustraction = new ArrayList<Integer>();
		  int diff = 1000000;
		  
		  for (int x=0;x<list1.size()-1;x++){
			 soustraction.add(diff);
		  }
		  
		  for (int x=0;x<list1.size()-1;x++){
			  soustraction.set(x,list1.get(x));
		  }
		  
		  for(int i = 0; i<listDeList.size();i++){
			  for (int y =0;y<listDeList.get(i).size()-1;y++){
				  int val = (int) listDeList.get(i).get(y);
				  diff = (int)(soustraction.get(y) - val);
				  soustraction.set(y,diff);
			  }
		  }
		  
		  double[] x = new double[soustraction.size()-1];
		  double[] y = new double[soustraction.size()-1];
		  Plot2DPanel plot = new Plot2DPanel();
		  
		  /*Ecriture dans un fichier */
		  if (type == 0){
			  File sous = new File(curDir+"\\File\\soustraction.txt");
			  File sousDat = new File(curDir+"\\File\\soustraction.dat");
			  File sousdest =  new File(curDir+"\\File\\soustraction.txt");
			  sous.createNewFile();
			  sousDat.createNewFile();
			  copyFile(sous,sousdest);
			  
			  FileWriter soust = new FileWriter(sous);
			  FileWriter soustDat = new FileWriter(sousDat);
			  
			  soustDat.write("#");
			  soustDat.write("\r\n");
			  
			  for (int i=0; i<soustraction.size()-1;i++){
				  soust.write(soustraction.get(i).toString());
				  x[i] = (double)i;
				  y[i]= (double) soustraction.get(i);
				  soust.write("\r\n");
				  soustDat.write(i+"	"+";"+"	"+soustraction.get(i).toString() + "\r\n");
			  }
			  
			  soustDat.close();
			  soust.close();
			  plot.addScatterPlot("graphe",x,y);
			  graphe.removeAll();
			  graphe.add(plot);
			  graphe.repaint();
		  }else{
			  File sous = new File(curDir+"\\File\\soustraction.mcs");
			  File sousDat = new File(curDir+"\\File\\soustraction.dat");
			  File sousdest =  new File(curDir+"\\File\\addition.mcs");
			  sous.createNewFile();
			  sousDat.createNewFile();
			  copyFile(sous,sousdest);
			  
			  FileWriter soust = new FileWriter(sous);
			  FileWriter soustDat = new FileWriter(sousDat);
			  
			  soustDat.write("#");
			  soustDat.write("\r\n");
			  
			  for(int i =0;i<256;i++){

				  soust.write(debut[i]);
			  }
			  for (int i=256; i<soustraction.size()-1;i++){
				  soust.write(soustraction.get(i));
				  x[i] = (double)i;
				  y[i]= (double) soustraction.get(i);
				  soust.write("\r\n");
				  soustDat.write(i+"	"+";"+"	"+soustraction.get(i).toString() + "\r\n");
			  }
			  
			  soustDat.close();
			  soust.close();
			  plot.addScatterPlot("graphe",x,y);
			  graphe.removeAll();
			  graphe.add(plot);
			  graphe.repaint();
			  
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
