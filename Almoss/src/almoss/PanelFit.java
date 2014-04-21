package almoss;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.developpez.adiguba.shell.Shell;

public class PanelFit extends JPanel{
	
	JComboBox choixType = new JComboBox();
	
	public PanelFit(final MenuBarAlmoss filePara){
		super();
		JPanel pan = new JPanel(); // Panel contenant les boutons
		JPanel pan2 = new JPanel();
		
		choixType.addItem("1");
		choixType.addItem("2");
		choixType.addItem("3");
		
		JButton fit = new JButton("fit");
		pan2.add(choixType);
		pan2.add(fit);
		
		GridLayout grid = new GridLayout(2,3);
		grid.setHgap(20);
		grid.setVgap(20);
		pan.setLayout(grid);
		
		final SelecFichier ouvr = new SelecFichier("Selection Fichier",this,1); // Bouton offrant la possiblité d'ouvrire un fichier
		
		fit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Runtime runtime = Runtime.getRuntime();
				try {
					Shell.system("E:\\Travail\\Projet_Almoss\\full\\fullham.exe " + ouvr.getFichier().getAbsolutePath() + " " + filePara.getFilePara().getAbsolutePath() + " "+ choixType.getSelectedItem());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		pan.add(ouvr);
		pan.add(pan2);
		this.add(pan);
	}

}
