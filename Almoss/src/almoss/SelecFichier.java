package almoss;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class SelecFichier extends JButton{
	private File fichier;
	JFileChooser choix;
	int returnVal;
	
	public SelecFichier(String s, final PanelOpe pane, final int num){
		super(s);
		this.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				choix = new JFileChooser();
				returnVal = choix.showOpenDialog(null); // on fait apparaitre la fenetre de selection des fichiers
				// returnVal permet de tester si l'utilisateur a cliqu� sur OK ou annuler (ou autres...)
				fichier = choix.getSelectedFile(); // R�cup�ration du fichier s�l�ctionner
				if (returnVal == JFileChooser.APPROVE_OPTION){
					 pane.changeCouleur(num);
				}
			}
		});

	}
	
	public SelecFichier(String s, final PanelPliage pane, final int num){
		super(s);
		this.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				choix = new JFileChooser();
				returnVal = choix.showOpenDialog(null); // on fait apparaitre la fenetre de selection des fichiers
				// returnVal permet de tester si l'utilisateur a cliqu� sur OK ou annuler (ou autres...)
				fichier = choix.getSelectedFile(); // R�cup�ration du fichier s�l�ctionner
			}
		});

	}
	
	public File getFichier(){
		return fichier;
	}
}
