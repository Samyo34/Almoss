package almoss;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;



import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;








import javax.swing.filechooser.FileFilter;

//Pour obtenir la liste des fichiers selectionn�s 
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SelecFichier extends JButton implements ListSelectionListener {
	public File fichier;
	JFileChooser choix;
	int returnVal;
	static ArrayList<File> listF;
	File[] files;
	JList listClick;
	
	Filtre filtreMcs = new Filtre("Fichier mcs");
	//filtreMcs.addExtension(".mcs");
	//filtreMcs.addExtension(".MCS");
	
	Filtre filtreDat = new Filtre("Fichier dat");
	//filtreDat.addExtension(".dat");
	//filtreDat.addExtenion(".DAT");

	public SelecFichier(String s, final PanelOpe pane, final int num,final ArrayList<File> list, final JPanel panFich) {
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
							files = choix.getSelectedFiles();
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
				for(int i=0;i<files.length;i++){
					fichier = new File(files[i].getAbsolutePath()); // Recuperation du fichier selectionner
					list.add(fichier);
				}
				String[] s = new String[list.size()]; //Pour stocker la liste des fichiers (dans le but de les afficher)
				
				/* On passe par un Modele pour contstruire la liste */
				DefaultListModel model = new DefaultListModel();
				for (int i=0; i<list.size();i++){
					model.addElement(list.get(i).getName());
				}
				
				// Ajout du Model ) la list
				listClick = new JList(model);
				final JScrollPane scroll = new JScrollPane();
				scroll.setViewportView(listClick);
				panFich.add(scroll);
				
				
				listClick.setBackground(panFich.getBackground());
				
				listClick.addListSelectionListener(new ListSelectionListener(){
					public void valueChanged(ListSelectionEvent arg0) {
						if(listClick.getValueIsAdjusting() && !listClick.isSelectionEmpty()){
							// R�cup�ration du Model de la list
							DefaultListModel model2 = (DefaultListModel) listClick.getModel();
							litList(list);
							// Suppr�ssion de l'�lement s�l�ction�
							list.remove(model2.indexOf(listClick.getSelectedValue()));
							litList(list);
							model2.remove(listClick.getSelectedIndex());
							
							// Affectation du nouveau model (donc nouvelle list)
							listClick.setModel(model2);
							panFich.remove(scroll);
							panFich.add(scroll);
							panFich.repaint();
						}
					}
				});
				panFich.removeAll();
				panFich.add(scroll);
				panFich.repaint();
				
				listF = list;
			}
		});

	}

	public SelecFichier(String s, final PanelPliage pane, final int num, final JPanel pan) {
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
				AfficheGraphe graphe=new AfficheGraphe();
				try {
					graphe.CalculeGraphe(fichier,pan);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}
	
	public SelecFichier(String s, final PanelFit pane, final int num, final JPanel pan){
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
				AfficheGraphe graphe=new AfficheGraphe();
				try {
					graphe.CalculeGraphe(fichier,pan);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}
	
	public SelecFichier(String s, final PanelAffiche affiche){ //Panel Affiche
		super(s);
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix = new JFileChooser();
				choix.setMultiSelectionEnabled(true);
				choix.addChoosableFileFilter(filtreMcs);
				choix.addChoosableFileFilter(filtreMcs);
				returnVal = choix.showOpenDialog(null); // on fait apparaitre la
														// fenetre de selection
														// des fichiers
				// returnVal permet de tester si l'utilisateur a cliqu� sur OK
				// ou annuler (ou autres...)
				fichier = new File(choix.getSelectedFile().getAbsolutePath()); // R�cup�ration du fichier selectionner
				
				try {
					affiche.affGraphe(fichier);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
	}

	public File getFichier() {
		return this.fichier;

	}

	public ArrayList<File> getListF() {
		return listF;
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		//listClick.remove(listClick.getAnchorSelectionIndex());
		
	}
	
	public void litList(ArrayList<File> list){
		for(int i=0; i<list.size();i++){
			System.out.print(list.get(i) + " * ");
		}
		System.out.println();
	}
}
