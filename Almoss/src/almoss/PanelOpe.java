package almoss;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/* 
 * Cette classe premet de creer l'ensemble des composants
 * de l'onglet sur les opérations
 */

public class PanelOpe extends JPanel{
	
	private JCheckBox valide1 = new JCheckBox();
	private JCheckBox valide2 = new JCheckBox();
	
	JComboBox choixOpe = new JComboBox();
	JComboBox type = new JComboBox(); // choix du type de fichier sortit
	
	
	
	
	public PanelOpe() throws IOException{
		
		SelecFichier ouvr1 = new SelecFichier("Selection Fichier 1",this,1); // Bouton offrant la possiblité d'ouvrire le premier fichier
		SelecFichier ouvr2 = new SelecFichier("Selection Fichier 2",this,2); // Bouton offrant la possiblité d'ouvrire le second fichier
		ExecOpe ope = new ExecOpe("Operation",choixOpe,ouvr1.getFichier(),ouvr2.getFichier(),type);
		
		JPanel panelChoix = new JPanel();// Panel pour inserer les elements de choix de l'opération
		JPanel panelSelec = new JPanel();// Panel pour inserer les elements de selection de fichier
		
		
		
		
		
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
		panelSelec.add(ouvr2);
		panelSelec.add(valide2);
		
		
		this.setLayout(new BorderLayout());
		this.add(panelSelec, BorderLayout.NORTH);
		this.add(panelChoix, BorderLayout.CENTER);

	}
	
	public void changeCouleur(int num){
		/*
		 * Change la couleur de la checkBox numéros num
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
