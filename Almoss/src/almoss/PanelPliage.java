package almoss;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class PanelPliage extends JPanel {
	
	public PanelPliage(){
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout());
		SelecFichier ouvr = new SelecFichier("Selection Fichier 1",this,1); // Bouton offrant la possiblité d'ouvrire un fichier
		ButtonPli pli = new ButtonPli("Plier !", ouvr.getFichier());
		
		pan.add(ouvr, BorderLayout.NORTH);
		pan.add(pli, BorderLayout.CENTER);
		
		this.add(pan);
	}
	

}
