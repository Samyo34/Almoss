package almoss;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import com.developpez.adiguba.shell.Shell;

public class PanelFit extends JPanel{
	JPanel panGraphe=new JPanel();
	JComboBox choixType = new JComboBox();
	
	public PanelFit(final MenuBarAlmoss filePara){
		super();
		JPanel panGroupe=new JPanel();
		JPanel pan = new JPanel(); // Panel contenant les boutons et le premier graphe
		JPanel pan1= new JPanel();  // Panel contenant le bouton de selection du fichier
		JPanel pan2 = new JPanel(); // Panel contenant le bonton fit
		JPanel pan3= new JPanel(); // Panel contenant le premier graphe
		pan1.setLayout(new BorderLayout());
		pan.setLayout(new BorderLayout());
		choixType.addItem("1");
		choixType.addItem("2");
		choixType.addItem("3");
		
		JButton fit = new JButton("fit");
		pan2.add(choixType);
		pan2.add(fit);
		
		GridLayout grid = new GridLayout(2,3);
		grid.setHgap(20);
		grid.setVgap(20);
		
		final SelecFichier ouvr = new SelecFichier("Selection Fichier",this,1,pan3); // Bouton offrant la possiblit� d'ouvrire un fichier
		
		fit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Runtime runtime = Runtime.getRuntime();
				try {
					Shell.system("\\media\\tayeb\\Disque local\\work\\Almoss\\full\\fullham.exe " + ouvr.getFichier().getAbsolutePath() + " " + filePara.getFilePara().getAbsolutePath() + " "+ choixType.getSelectedItem());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		panGraphe.setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));
		panGraphe.setLayout(new GridLayout(1,2));
		
		pan1.add(ouvr, BorderLayout.CENTER);
		pan.add(pan1, BorderLayout.NORTH);
		pan.add(pan3, BorderLayout.CENTER);
		pan.add(pan2, BorderLayout.SOUTH);
		panGroupe.setLayout(new BorderLayout());
		panGroupe.add(pan, BorderLayout.CENTER);
		this.setLayout(new GridLayout(2,1));
		this.add(panGroupe);
		this.add(panGraphe);
		
	}

}
