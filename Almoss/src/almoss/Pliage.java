package almoss;
import java.io.*;

//Pour l'instant le pliage prend 521 points et le replie donc en 256 au centre
//Devra �tre am�lior� en pouvant changer le point de pli(de sym�trie)

public class Pliage {
	File file;//R�cup�rer le fichier ouvert via la barre de menu
	DataInputStream input;
	DataOutputStream output;
	int point;
	
	public Pliage(File file){
		this.file=file;
	}
	
		public void pli(File file) throws IOException //M�thode de pli 
		{
			DataInputStream input=new DataInputStream(new FileInputStream(file));
			
			DataOutputStream writer = new DataOutputStream (new FileOutputStream (file.getName()+".mcs"));
			
			int size=512;//Doit �tre pair sinon erreur dans les boucles for
			int moitie1[]=new int[256];//Premi�re moiti� avant le pli
			int moitie2[]=new int[256];//Seconde moiti� avant le pli
			int pli[]=new int[256];//R�sultat du pli
			
			for(int i=0;i<size/2;i++)
			{
				moitie1[i]=input.readInt();//Lit la premiere moiti� des points
			}
			
			for(int i=0;i<size/2;i++)
			{
				moitie2[i]=input.readInt();//Lit la seconde moiti� des points
			}
			input.close();//Fermeture du Stream de lecture (DataInputStream)
			
	
			for(int i=0;i<size/2;i++)
			{
				point = (int)(moitie1[i]+moitie2[size-i])/2;//Calcule la moyenne des 2 points oppos�s avant le pli
				pli[i]=point;
				writer.writeInt(pli[i]);//Ecriture du fichier pli
			}
			writer.close();
		}
}
