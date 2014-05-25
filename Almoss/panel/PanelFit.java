package panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import almoss.MenuBarAlmoss;
import bouton.SelecFichier;

import com.developpez.adiguba.shell.Shell;

public class PanelFit extends JPanel{
	
	JComboBox choixType = new JComboBox();
	String curDir = System.getProperty("user.dir");
	
	public PanelFit(final MenuBarAlmoss filePara){
		super();
		JPanel pan = new JPanel(); // Panel contenant les boutons
		JPanel pan2 = new JPanel();
		JPanel panSelec = new JPanel();// Panel contenant le bouton de selection de fichier
		JPanel panGraphe = new JPanel();// Panel contenant le graphe
		
		panGraphe.setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));
		
		// Les differents choix
		choixType.addItem("3");
		choixType.addItem("2");
		choixType.addItem("1");
		
		JButton fit = new JButton("fit");
		pan2.add(choixType);
		pan2.add(fit);
		
		BorderLayout grid = new BorderLayout();
		grid.setHgap(20);
		grid.setVgap(20);
		pan.setLayout(grid);
		
		final SelecFichier ouvr = new SelecFichier("Selection Fichier",panGraphe,1); // Bouton offrant la possiblite d'ouvrir un fichier
		
		
		
		panSelec.setLayout(new GridLayout());
		panSelec.add(ouvr);
		
		pan.add(panSelec,BorderLayout.NORTH);
		pan.add(pan2, BorderLayout.CENTER);
		
		this.setLayout(new BorderLayout());
		this.add(pan, BorderLayout.NORTH);
		this.add(panGraphe, BorderLayout.CENTER);
		fit.addActionListener(new ActionListener(){
			
		public void actionPerformed(ActionEvent e) {
			try {
				int res = Shell.system(curDir+"\\Log\\fullham.exe" + ouvr.getFichier().getAbsolutePath() + " " + filePara.getFilePara().getAbsolutePath() + " "+ choixType.getSelectedItem());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			
		});
	}

}
