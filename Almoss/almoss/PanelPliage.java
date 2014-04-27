package almoss;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class PanelPliage extends JPanel {
	
	JPanel panGraphe = new JPanel(); // Panel contenant le graphe;
	
	public PanelPliage(){
		JPanel pan = new JPanel(); // Panel contenant les boutons
		JPanel panGroupe =  new JPanel();// Panel contenant le panel pan (facilite le positionnement)
		
		//BorderLayout grid = new BorderLayout();
		//pan.setLayout(grid);
		
		SelecFichier ouvr = new SelecFichier("Selection Fichier",this,1); // Bouton offrant la possiblité d'ouvrire un fichier
		ButtonPli pli = new ButtonPli("Plier !", ouvr, panGraphe);
		
		pan.add(ouvr, BorderLayout.NORTH);
		pan.add(pli, BorderLayout.SOUTH);
		
		panGraphe.setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));
		panGraphe.setLayout(new GridLayout());
		
		panGroupe.setLayout(new BorderLayout());
		panGroupe.add(pan, BorderLayout.CENTER);

		this.setLayout(new GridLayout(2,1));
		this.add(panGroupe);
		this.add(panGraphe);
	}
	

}
