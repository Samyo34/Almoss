package almoss;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
	
	public void CalculeGraphe(File fichier, JPanel pane) throws FileNotFoundException{
		file = new FileInputStream(fichier.getAbsolutePath());
		
		ArrayList<Integer>valeurs = new ArrayList<Integer>();
		
		try {
			file.skip(256);
			
			while((byteLu=file.read(buffer))!=-1){
				oct = byteArrayToInt(buffer);
				valeurs.add(oct);	
			}
			int m=(valeurs.get(0)+valeurs.get(1)+valeurs.get(2)+valeurs.get(3)+valeurs.get(4)+valeurs.get(5)+valeurs.get(6)+valeurs.get(7))/8;
			valeurs.remove(0);
			valeurs.add(0,m);
			double[] x = new double[valeurs.size()-1];
			double[] y = new double[valeurs.size()-1];
			
			
			
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
