package bouton;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import almoss.FenetreAlmoss;

public class ButtonGnu extends JButton implements MouseListener{

	private static final long serialVersionUID = 1L;
	File fileDelta;
	public static boolean ouvert =false; // Vrai si gnuplot est d�ja ouvert
	String curDir = System.getProperty("user.dir"); // premet de connaitre le repertoire courant de l'application
	
	
	public ButtonGnu(File fichier){
		super("gnuPlot");
		fileDelta = fichier;
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		try {
				// Commande GnuPlot par defaut (1 doublet)
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
