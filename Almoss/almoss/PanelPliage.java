package almoss;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class PanelPliage extends JPanel {
	
	JPanel panGraphe = new JPanel(); // Panel contenant le graphe;
	
	public PanelPliage(){
		JPanel pan = new JPanel(); // Panel contenant les boutons
		JPanel pan1= new JPanel(); // Panel contenant le bouton de selection du fichier
		JPanel pan2= new JPanel(); // Panel contenant le bonton de pliage
		JPanel pan3=new JPanel(); // Panel contenant le premier graphe
		JPanel panGroupe =  new JPanel();// Panel contenant le panel pan (facilite le positionnement)
		
		GridLayout grid = new GridLayout(2,3);
		grid.setHgap(20);
		grid.setVgap(20);
		pan1.setLayout(new BorderLayout());
		pan2.setLayout(new BorderLayout());
		pan.setLayout(new BorderLayout());
		
		SelecFichier ouvr = new SelecFichier("Selection Fichier",this,1,pan3); // Bouton offrant la possiblit� d'ouvrire un fichier
		ButtonPli pli = new ButtonPli("Plier !", ouvr, panGraphe);
		pan1.add(ouvr, BorderLayout.CENTER);
		pan2.add(pli, BorderLayout.CENTER);
		pan.add(pan1, BorderLayout.NORTH);
		pan.add(pan2, BorderLayout.SOUTH);
		pan.add(pan3, BorderLayout.CENTER);
		
		panGraphe.setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));
		panGraphe.setLayout(new GridLayout(1,2));
		
		panGroupe.setLayout(new BorderLayout());
		panGroupe.add(pan, BorderLayout.CENTER);

		this.setLayout(new GridLayout(2,1));
		this.add(panGroupe);
		this.add(panGraphe);
	}
	

}
