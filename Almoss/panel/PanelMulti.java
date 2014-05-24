package panel;

import java.awt.BorderLayout;
import java.io.File;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import bouton.ButtonFit;
import bouton.SelecFichier;

/*
 * Onglet permettant d'excuter un fit avec fullham sur plusieurs fichiers
 */
public class PanelMulti extends JPanel{
	
	JPanel panBouton = new JPanel(); // Panel contenant les boutons
	File[] files;
	SelecFichier[] pars;
	JComboBox<String> choixType = new JComboBox<String>(); // Choix du type de Fit (3,2,1)
	ButtonFit fit = new ButtonFit(choixType); // Bouton excutant le fit pour l'ensemble des fichiers selectionnes
	PanelList listFichier = new PanelList(fit, this);// Panel contenant la liste des fichiers selectionnes
	SelecFichier select = new SelecFichier("Ajouter des fichiers", this);// Bouton de selection de fichiers (selection multliple)
	
	
	
	private static final long serialVersionUID = 1L;

	public PanelMulti(){
		super();
		fit.setEnabled(false);
		setLayout(new BorderLayout());
		
		choixType.addItem("3");
		choixType.addItem("2");
		choixType.addItem("1");
		
		panBouton.add(select);
		panBouton.add(choixType);
		panBouton.add(fit);

		add(panBouton, BorderLayout.NORTH);
		add(listFichier);
	}
	
	/*
	 * Ajoute une ligne (info fichiers, boutons, avancement du fit) ) a la liste des fichiers
	 */
	public void addLines(File[] fichiers){
		files = new File[fichiers.length];
		pars = new SelecFichier[fichiers.length];
		for (int i=0; i<fichiers.length;i++){
			listFichier.addLine(fichiers[i], "En attente");
		}
		fit.setFiles(listFichier.getListFichier());
		fit.setEnabled(true);
		this.repaint();
	}
	
	public void setFilesFit(HashMap<Integer,PanelLFichier> map){
		fit.setFiles(map);
	}

}
