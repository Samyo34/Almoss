package panel;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;

public class AfficheGraphe extends JPanel{

	FileInputStream file;
	static byte[] buffer = new byte[4];
	static int byteLu;
	int oct;
	Plot2DPanel plot = new Plot2DPanel();

	public AfficheGraphe(){
		super();
	}

	/*
	 * Creation d'un graphe à partire d'un fichier MCS
	 */
	public void CalculeGraphe(File fichier, JPanel pane) throws FileNotFoundException{
		file = new FileInputStream(fichier.getAbsolutePath());

		if(fichier.getName().toLowerCase().endsWith("mcs")){// test de l'extension du fichier
			ArrayList<Integer>valeurs = new ArrayList<Integer>();
			try {
				file.skip(256); // saut des 256 premier bit
	
				while((byteLu=file.read(buffer))!=-1){ // recuperation des valeurs
					oct = byteArrayToInt(buffer);
					valeurs.add(oct);
				}
				int m=(valeurs.get(0)+valeurs.get(1)+valeurs.get(2)+valeurs.get(3)+valeurs.get(4)+valeurs.get(5)+valeurs.get(6)+valeurs.get(7))/8;
				valeurs.remove(0);
				valeurs.add(0,m);
				double[] x = new double[valeurs.size()-1];
				double[] y = new double[valeurs.size()-1];
	
	
				// Creation des donnees pour tracer la courbe
				for (int i=0; i<valeurs.size()-1;i++){
					x[i] = (double)i;	
					y[i]= (double) valeurs.get(i);
				}
	
				plot.addScatterPlot("graphe", x, y);
				pane.setLayout(new BorderLayout());
				pane.removeAll();
				pane.add(plot, BorderLayout.CENTER);
				pane.revalidate();
				pane.repaint();
				file.close();
	
	
	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Affichage d'un graphe à partire d'un fichier DAT
	 */
	public void calculeGrapheDat(File fichier, JPanel pane) throws IOException{
		file = new FileInputStream(fichier.getAbsolutePath());
		
		System.out.println(fichier.getName().toLowerCase());
		
		if(fichier.getName().toLowerCase().endsWith("dat")){// test de l'extension du fichier
			
			InputStreamReader fileStream =new InputStreamReader(file);
			BufferedReader buffer=new BufferedReader(fileStream);
			double[] x = new double[countRow(fichier)-1];
			double[] y = new double[countRow(fichier)-1];
			
			buffer.readLine(); // On saute la première ligne
			String ligne;
			double[] tab = new double[2];
			int i =0;
			
			// Creation des donnees pour tracer la courbe
			while((ligne = buffer.readLine()) != null){
				tab = litValeur(ligne);
				x[i] = tab[0];
				y[i] = tab[1];
				i++;
	
			}
			
			plot.addScatterPlot("graphe", x, y);
			pane.setLayout(new BorderLayout());
			pane.removeAll();
			pane.add(plot, BorderLayout.CENTER);
			pane.revalidate();
			pane.repaint();
			file.close();
		}
		
	}
	
	public int countRow(File fichier) throws IOException{
		file = new FileInputStream(fichier.getAbsolutePath());
		InputStreamReader fileStream =new InputStreamReader(file);
		BufferedReader buffer=new BufferedReader(fileStream);
		
		int i =0;
		String ligne;
		while ((ligne = buffer.readLine()) != null){
			i++;
		}
		return i;
	}
	
	/*
	 * Recupere la valeur Integer à partir d'une string
	 */
	public double[] litValeur(String s){
		System.out.println(s);
		double [] tab = new double[2];
		int debut, fin;
		double x,y;
		String s1,s2;
		debut = 0;
		fin = s.indexOf("	");
		
		s1=s.substring(debut, fin);
		x = Double.valueOf(s1);
		
		debut = fin +2;
		fin = s.length();
		s2=s.substring(debut, fin);
		y = Double.valueOf(s2);
		
		tab[0] = x;
		tab[1]= y;
		return tab;
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
