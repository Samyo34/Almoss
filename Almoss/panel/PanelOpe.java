package panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import bouton.SelecFichier;
import fonction.ExecOpe;

/* 
 * Cette classe premet de creer l'ensemble des composants
 * de l'onglet sur les opï¿½rations
 */

public class PanelOpe extends JPanel{
	
	private static final long serialVersionUID = 1L;
	JComboBox choixOpe = new JComboBox();
	JComboBox type = new JComboBox(); // choix du type de fichier sortit
	
	JPanel panelGraphe = new JPanel(); // Panel qui contient le graphe
	JPanel panelList = new JPanel();// Panel affichant les fichiers sélectionés
	
	ArrayList<File>listFichier = new ArrayList<File>();
	
	
	
	public PanelOpe() throws IOException{
		SelecFichier ouvr1 = new SelecFichier("Ajouter un fichier",this,1,listFichier,panelList); // Bouton offrant la possiblitï¿½ d'ouvrir un fichier
		ExecOpe ope = new ExecOpe("Operation",choixOpe,listFichier,type,panelGraphe); // Bouton exécutant les opérations
		
		
		JPanel panelChoix = new JPanel();// Panel pour inserer les elements de choix de l'operation
		JPanel panelFich = new JPanel(); //Panel pour regrouper panelSelec et panelList
		JPanel panelSelec = new JPanel();// Panel pour inserer les elements de selection de fichier
		JPanel panelGroup = new JPanel(); // Panel qui regroupe panelFich et panelChoix
		
		
		
		panelFich.setLayout(new BorderLayout());
		panelFich.add(panelSelec, BorderLayout.NORTH);
		panelFich.add(panelList, BorderLayout.CENTER);

		
		panelGroup.setLayout(new BorderLayout());
		panelGroup.add(panelFich,BorderLayout.CENTER);
		panelGroup.add(panelChoix, BorderLayout.SOUTH);
		
		panelGraphe.setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));
		panelGraphe.setLayout(new GridLayout());
		
		
		// Choix du type de fichier sortie
		type.addItem(".txt");
		type.addItem(".mcs");
		
		// Choix de l'operation
		choixOpe.addItem("Addition");
		choixOpe.addItem("Soustraction");
		JLabel info = new JLabel ("Choix de l'operation");
		
		// Ajout des elements
		panelChoix.add(info);
		panelChoix.add(choixOpe);
		panelChoix.add(type);
		panelChoix.add(ope);
		
		panelSelec.add(ouvr1);

		
		GridLayout gridL = new GridLayout(2,1);
		
		this.setLayout(gridL);
		this.add(panelGroup);
		this.add(panelGraphe);

	}
	
	

}
