package almoss;
import java.io.*;

//Pour l'instant le pliage prend 521 points et le replie donc en 256 au centre
//Devra être amélioré en pouvant changer le point de pli(de symétrie)

public class Pliage {
	File file;//Récupérer le fichier ouvert via la barre de menu
	DataInputStream input;
	DataOutputStream output;
	int point;
	
	public Pliage(File file){
		this.file=file;
	}
	
		public void pli(File file) throws IOException //Méthode de pli 
		{
			DataInputStream input=new DataInputStream(new FileInputStream(file));
			
			DataOutputStream writer = new DataOutputStream (new FileOutputStream (file.getName()+".mcs"));
			
			int size=512;//Doit être pair sinon erreur dans les boucles for
			int moitie1[]=new int[256];//Première moitié avant le pli
			int moitie2[]=new int[256];//Seconde moitié avant le pli
			int pli[]=new int[256];//Résultat du pli
			
			for(int i=0;i<size/2;i++)
			{
				moitie1[i]=input.readInt();//Lit la premiere moitié des points
			}
			
			for(int i=0;i<size/2;i++)
			{
				moitie2[i]=input.readInt();//Lit la seconde moitié des points
			}
			input.close();//Fermeture du Stream de lecture (DataInputStream)
			
	
			for(int i=0;i<size/2;i++)
			{
				point = (int)(moitie1[i]+moitie2[size-i])/2;//Calcule la moyenne des 2 points opposés avant le pli
				pli[i]=point;
				writer.writeInt(pli[i]);//Ecriture du fichier pli
			}
			writer.close();
		}
}
