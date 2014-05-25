package panel;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;

import almoss.FenetreAlmoss;

public class AfficheGraphe extends JPanel{

	FileInputStream file;
	static byte[] buffer = new byte[4];
	static int byteLu;
	int oct;
	Plot2DPanel plot = new Plot2DPanel();
	String curDir = System.getProperty("user.dir"); // premet de connaitre le repertoire courant de l'application

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
	
	/*
	 * Panel Affichage : ouvre un fichier gnuPlot avec le fichier delta en parametre
	 */
	public void affGnuPlot(File fileDelta) throws HeadlessException, FileNotFoundException, IOException{
		String cmd="plot '"+fileDelta.getAbsolutePath()+"' using 1:2 with points ls 1, \"\" u 1:3 w l ls 3, \"\" u 1:(1.005+column(2) -column(3))with points ls 3, \"\" u 1:(0.005+column(5))w l ls 1";;
		Object[] options = {"1 doublet","2 doublets","3 doublets", "4 doublets"};
		int n = JOptionPane.showOptionDialog(FenetreAlmoss.getInstance(), "Choisissez le nombre de doublet :", "Choix doublet", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		switch(n){
		case 0 : // 1 doublet
			cmd= "plot '"+fileDelta.getAbsolutePath()+"' using 1:2 with points ls 1, \"\" u 1:3 w l ls 3, \"\" u 1:(1.005+column(2) -column(3))with points ls 3, \"\" u 1:(0.005+column(5))w l ls 1";
		case 1:// 2 doublets
			cmd= "plot '"+fileDelta.getAbsolutePath()+"' using 1:2 with points ls 1,\"\" u 1:3 w l ls 1, \"\" u 1:4 w l ls 3,\"\" u 1:5 w l ls 6, \"\" u 1:(1.005+column(2) -column(3))with points ls 3, \"\" u 1:(0.005+column(6))w l ls 1";
		case 2:// 3 doublets
			cmd= "plot '"+fileDelta.getAbsolutePath()+"' using 1:2 with points ls 1,\"\" u 1:3 w l ls 1, \"\" u 1:4 w l ls 3,\"\" u 1:5 w l ls 6,\"\" u 1:6 w l ls 4, \"\" u 1:(1.005+column(2) -column(3))with points ls 3, \"\" u 1:(0.005+column(7))w l ls 1";
		case 3:// 4 doublets
			cmd= "plot '"+fileDelta.getAbsolutePath()+"' using 1:2 with points ls 1,\"\" u 1:3 w l ls 1, \"\" u 1:4 w l ls 3,\"\" u 1:5 w l ls 6,\"\" u 1:6 w l ls 4,\"\" u 1:7 w l ls 5, \"\" u 1:(1.005+column(2) -column(3))with points ls 3, \"\" u 1:(0.005+column(8))w l ls 1";
		}
		// Execution de gnuPlot
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec(curDir+"/Log/GnuPlot/pgnuplot.exe");
		OutputStream opStream = process.getOutputStream();
		// Excriture de la ligne de commande
        PrintWriter gp =new PrintWriter(new BufferedWriter(new OutputStreamWriter(opStream)));
        gp.println(cmd);
        gp.flush();
		
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
