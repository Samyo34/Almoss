package almoss;

import java.awt.BorderLayout;
import java.io.File;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import com.developpez.adiguba.shell.Shell;

public class PanelMulti extends JPanel{
	
	JPanel panBouton = new JPanel();
	File[] files;
	SelecFichier[] pars;
	JComboBox<String> choixType = new JComboBox<String>();
	ButtonFit fit = new ButtonFit(choixType);
	PanelList listFichier = new PanelList(fit, this);
	SelecFichier select = new SelecFichier("Ajouter des fichiers", this);
	
	
	
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
