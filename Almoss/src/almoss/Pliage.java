package almoss;
import java.awt.BufferCapabilities.FlipContents;
import java.io.*;

//Pour l'instant le pliage prend 521 points et le replie donc en 256 au centre
//Devra être amélioré en pouvant changer le point de pli(de symétrie)

public class Pliage {
	File file;//Récupérer le fichier ouvert via la barre de menu
	int point;
	byte point2;
	FileInputStream input;
	
	public Pliage(File file){
		this.file=file;
	}
	
		public void pli(File file) throws IOException //Méthode de pli 
		{
			this.input = new FileInputStream("E:/Travail/Projet Almoss/spectre3.MCS");
			File pliage = new File("pli.mcs");
			pliage.createNewFile();
			FileWriter ecPli1 = new FileWriter(pliage);
			
			File pliage2 = new File("pli.txt");
			pliage2.createNewFile();
			FileWriter ecPli2  = new FileWriter(pliage2);
			
			System.out.println("test2");
			int size=512;//Doit être pair sinon erreur dans les boucles for
			int moitie1[]=new int[256];//Première moitié avant le pli
			int moitie2[]=new int[256];//Seconde moitié avant le pli
			int pli[]=new int[256];//Résultat du pli
			
			for(int i=0;i<size/2;i++)
			{
				moitie1[i]= input.read();//Lit la premiere moitié des points
			}
			System.out.println("test3");
			for(int i=0;i<size/2;i++)
			{
				moitie2[i]=input.read();//Lit la seconde moitié des points
			}
			input.close();//Fermeture du Stream de lecture (DataInputStream)
			
			System.out.println("test4");
			for(int i=0;i<size/2;i++)
			{
				point = (int)(moitie1[i]+moitie2[size-i])/2;//Calcule la moyenne des 2 points opposés avant le pli
				point2 = (byte) ((byte)(moitie1[i]+moitie2[size-i])/2);
				pli[i]=point;
				ecPli1.write(pli[i]);//Ecriture du fichier pli
				ecPli2.write(point2);
			}
			ecPli1.close();
			ecPli2.close();
			System.out.println(pliage.getAbsolutePath());
			
		}
}
