package almoss;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

//Pour obtenir la liste des fichiers selectionn�s 
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SelecFichier extends JButton {
	public File fichier;
	JFileChooser choix;
	int returnVal;
	static ArrayList<File> listF;

	public SelecFichier(String s, final PanelOpe pane, final int num,
			final ArrayList<File> list) {
		super(s);
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix = new JFileChooser(".");
				choix.setMultiSelectionEnabled(true);// Permet la selection
														// simultan�e de
														// plusieurs fichiers

				choix.addPropertyChangeListener(new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						if (JFileChooser.SELECTED_FILES_CHANGED_PROPERTY
								.equals(evt.getPropertyName())) {
							JFileChooser choix = (JFileChooser) evt.getSource();
							File[] oldFiles = (File[]) evt.getOldValue();
							File[] newFiles = (File[]) evt.getNewValue();

							// Obtenir la liste des fichiers s�lectionn�s
							File[] files = choix.getSelectedFiles();
							/*
							 * --Afficher la liste des fichiers s�lectionn�s
							 * 
							 * for(int i=0;i<files.length;i++){
							 * System.out.println(files[i]);
							 * 
							 * }
							 */

						}
					}
				});

				returnVal = choix.showOpenDialog(null); // on fait apparaitre la
														// fenetre de selection
														// des fichiers
				// returnVal permet de tester si l'utilisateur a cliqu� sur OK
				// ou annuler (ou autres...)

				fichier = new File(choix.getSelectedFile().getAbsolutePath()); // R�cup�ration
																				// du
																				// fichier
																				// s�l�ctionner
				list.add(fichier);
				listF = list;
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					pane.changeCouleur(num);
				}
			}
		});

	}

	public SelecFichier(String s, final PanelPliage pane, final int num) {
		super(s);
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix = new JFileChooser();
				choix.setMultiSelectionEnabled(true);

				returnVal = choix.showOpenDialog(null); // on fait apparaitre la
														// fenetre de selection
														// des fichiers
				// returnVal permet de tester si l'utilisateur a cliqu� sur OK
				// ou annuler (ou autres...)
				fichier = new File(choix.getSelectedFile().getAbsolutePath()); // R�cup�ration
																				// du
																				// fichier
																				// s�l�ctionner
			}
		});

	}

	public File getFichier() {
		return this.fichier;

	}

	public ArrayList<File> getListF() {
		return listF;
	}
}
