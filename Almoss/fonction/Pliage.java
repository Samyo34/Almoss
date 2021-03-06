package fonction;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;

//Pour l'instant le pliage prend 512 points et le replie donc en 256 au centre
//Devra �tre am�lior� en pouvant changer le point de pli(de sym�trie)

public class Pliage {
	File file;//R�cup�rer le fichier ouvert via la barre de menu
	static FileInputStream fis;
	static int byteLu;
	static byte[] buffer = new byte[4];
			
			
			
	public Pliage(File file){
		this.file=file;
	}
	
		public void pli(File file, JPanel graphe) throws IOException //M�thode de pli 
		{
		  ArrayList<Integer>list = new ArrayList<Integer>();//Liste contenant tous les points
		  byte[] debut = new byte[256];
		  fis=new FileInputStream(file);
		  
		  int point;
		  
		  try{
		  		//fis.skip(256);// Saut des 256 premiers octets (ce ne sont pas ceux contenant les valeurs)
			  for(int i=0;i<256;i++){
				  debut[i]=(byte) fis.read();
			  }
				while((byteLu=fis.read(buffer))!=-1){// Lecture du fichier jusqu'au bout, octet par octet (8 bytes)
					int oct = byteArrayToInt(buffer);
					list.add(oct);// Ajout de la valeur � la liste
				}
				int m=(list.get(0)+list.get(1)+list.get(2)+list.get(3)+list.get(4)+list.get(5)+list.get(6)+list.get(7))/8;
				list.remove(0);
				list.add(0,m);
			}
			finally{
				fis.close();
			}
			
		  
			int size=list.size()-1;//Doit �tre pair sinon erreur dans les boucles for
			System.out.println(size);
			int moitie1[]=new int[size/2];//Premi�re moiti� avant le pli
			int moitie2[]=new int[size/2];//Seconde moiti� avant le pli
			int pli_tab[]=new int[size/2];//R�sultat du pli
			
			

			for(int i=0;i<size/2;i++)
			{
				moitie1[i]=list.get(i);//Lit la premiere moiti� des points
				moitie2[i]=list.get(size-i);//Lit la seconde moiti� des points
				point = (int)(moitie1[i]+moitie2[i])/2;//Calcule la moyenne des 2 points oppos�s avant le pli
				pli_tab[i]=point;
			}
			
			  /*Ecriture dans un fichier */
				  File pli = new File("pli.txt");
				  File plidest =  new File("E:/Travail/Projet Almoss/"+"pli.txt");
				  pli.createNewFile();
				  copyFile(pli,plidest);
				  
				  double[] x = new double[list.size()];
				  double[] y = new double[list.size()];
				  Plot2DPanel plot = new Plot2DPanel();
				  
				  FileWriter writer = new FileWriter(pli);
				  
				  for(int i =0;i<256;i++){
					  writer.write(debut[i]);
				  }
				  
				  for (int i=256; i<list.size()-1;i++){
					  writer.write(list.get(i).toString());
					  writer.write("\r\n");
					  x[i-256] = (double)i-256;
					  y[i-256]= (double) list.get(i);
					  System.out.println(y[i-256]);
				  }
				  
				  writer.close();
				  plot.addLinePlot("graphe",x,y);
				  graphe.add(plot);
				  graphe.repaint();
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



