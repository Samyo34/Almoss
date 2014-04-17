package almoss;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/* 
 * Cette classe premet de creer l'ensemble des composants
 * de l'onglet sur les opï¿½rations
 */

public class PanelOpe extends JPanel{
	
	private JCheckBox valide1 = new JCheckBox();
	private JCheckBox valide2 = new JCheckBox();
	
	JComboBox choixOpe = new JComboBox();
	JComboBox type = new JComboBox(); // choix du type de fichier sortit
	
	JPanel panelGraphe = new JPanel(); // Panel qui contient le graphe
	JPanel panelList = new JPanel();// Panel affichant les fichiers sélectionés
	
	ArrayList<File>listFichier = new ArrayList<File>();
	
	
	
	public PanelOpe() throws IOException{
		
		SelecFichier ouvr1 = new SelecFichier("Ajouter un fichier",this,1,listFichier,panelList); // Bouton offrant la possiblitï¿½ d'ouvrir un fichier
		ExecOpe ope = new ExecOpe("Operation",choixOpe,listFichier,type,panelGraphe); // Bouton exécutant les opérations
		
		
		JPanel panelChoix = new JPanel();// Panel pour inserer les elements de choix de l'opï¿½ration
		JPanel panelSelec = new JPanel();// Panel pour inserer les elements de selection de fichier
		JPanel panelGroup = new JPanel(); // Panel qui regroupe panelChoix et panelSelec
		
		panelGroup.setLayout(new GridLayout(3,1));
		panelGroup.add(panelSelec);
		panelGroup.add(panelList);
		panelGroup.add(panelChoix);
		
		panelGraphe.setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));
		panelGraphe.setLayout(new GridLayout());
		
		
		
		
		valide1.setEnabled(false);
		valide1.setFocusable(false);
		valide2.setEnabled(false);
		valide2.setFocusable(false);
		
		type.addItem(".txt");
		type.addItem(".mcs");
		
		
		choixOpe.addItem("Addition");
		choixOpe.addItem("Soustraction");
		JLabel info = new JLabel ("Choix de l'operation");
		
		panelChoix.add(info);
		panelChoix.add(choixOpe);
		panelChoix.add(type);
		panelChoix.add(ope);
		
		panelSelec.add(ouvr1);
		panelSelec.add(valide1);

		
		
		this.setLayout(new GridLayout(2, 1));
		this.add(panelGroup);
		this.add(panelGraphe);

	}
	
	public void changeCouleur(int num){
		/*
		 * Change la couleur de la checkBox numï¿½ros num
		 * repaint ensuite
		 */
		
		if (num == 1){
			this.valide1.setForeground(Color.RED);
			this.repaint();
		}else{
			this.valide2.setForeground(Color.RED);
			this.repaint();
		}
	}

}
