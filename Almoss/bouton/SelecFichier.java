package bouton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;








import javax.swing.filechooser.FileFilter;







import fonction.Filtre;
import panel.AfficheGraphe;
import panel.PanelAffiche;
import panel.PanelMulti;
import panel.PanelOpe;
import panel.PanelPliage;



//Pour obtenir la liste des fichiers selectionn�s
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/*
 * Classe premettant, au travers de ses constructeurs de creer et afficher
 * un slectionneur de fichier
 */
public class SelecFichier extends JButton implements ListSelectionListener {
	public File fichier;
	JFileChooser choix;
	int returnVal;
	static ArrayList<File> listF;
	File[] files;
	JList listClick;
	File dir;

	Filtre filtreMcs = new Filtre("Fichier mcs");
	//filtreMcs.addExtension(".mcs");
	//filtreMcs.addExtension(".MCS");

	Filtre filtreDat = new Filtre("Fichier dat");
	//filtreDat.addExtension(".dat");
	//filtreDat.addExtenion(".DAT");
	
	// Selectionneur de fichier simple
	public SelecFichier(String s){
		super(s);
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix = new JFileChooser();
				choix.setCurrentDirectory(dir);
				choix.setMultiSelectionEnabled(true);
				// on fait apparaitre la fenetre de selection des fichiers returnVal permet de tester si l'utilisateur a clique sur OK ou annuler (ou autres...)
				returnVal = choix.showOpenDialog(null);
				fichier = new File(choix.getSelectedFile().getAbsolutePath()); // Recuperation du fichier selectionne
				dir = choix.getCurrentDirectory();
			}
		});
	}

	/*
	 * Selectionneur de fichier pour les operations (choix multiple)
	 */
	public SelecFichier(String s, final PanelOpe pane, final int num,final ArrayList<File> list, final JPanel panFich) {
		super(s);
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix = new JFileChooser(".");
				choix.setCurrentDirectory(dir);
				choix.setMultiSelectionEnabled(true);// Permet la selection simultane de plusieurs fichiers

				choix.addPropertyChangeListener(new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						if (JFileChooser.SELECTED_FILES_CHANGED_PROPERTY
								.equals(evt.getPropertyName())) {
							JFileChooser choix = (JFileChooser) evt.getSource();
							File[] oldFiles = (File[]) evt.getOldValue();
							File[] newFiles = (File[]) evt.getNewValue();

							// Obtenir la liste des fichiers selectionnes
							files = choix.getSelectedFiles();
							dir = choix.getCurrentDirectory();

						}
					}
				});

				returnVal = choix.showOpenDialog(null); // on fait apparaitre la
				// fenetre de selection
				// des fichiers
				// returnVal permet de tester si l'utilisateur a clique sur OK
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
				panFich.revalidate();
				panFich.repaint();


				listClick.setBackground(panFich.getBackground());

				listClick.addListSelectionListener(new ListSelectionListener(){
					public void valueChanged(ListSelectionEvent arg0) {
						if(listClick.getValueIsAdjusting() && !listClick.isSelectionEmpty()){
							// Recuperation du Model de la list
							DefaultListModel model2 = (DefaultListModel) listClick.getModel();
							// Suppression de l'element selectione
							list.remove(model2.indexOf(listClick.getSelectedValue()));
							model2.remove(listClick.getSelectedIndex());

							// Affectation du nouveau model (donc nouvelle list)
							listClick.setModel(model2);
							panFich.remove(scroll);
							panFich.add(scroll);
							panFich.revalidate();
							panFich.repaint();
						}
					}
				});
				panFich.removeAll();
				panFich.add(scroll);
				panFich.revalidate();
				panFich.repaint();

				listF = list;
			}
		});

	}

	/*
	 * Pour le pliage
	 */
	public SelecFichier(String s, final PanelPliage pane, final int num) {
		super(s);
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix = new JFileChooser();
				choix.setCurrentDirectory(dir);
				choix.setMultiSelectionEnabled(true);

				returnVal = choix.showOpenDialog(null); // on fait apparaitre la
				// fenetre de selection
				// des fichiers
				// returnVal permet de tester si l'utilisateur a cliqu� sur OK
				// ou annuler (ou autres...)
				fichier = new File(choix.getSelectedFile().getAbsolutePath()); // Recuperation du fichier selectionne
				dir = choix.getCurrentDirectory();
				AfficheGraphe graphe=new AfficheGraphe();
				try {
					graphe.CalculeGraphe(fichier,pane);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}
	
	/*
	 * 
	 */
	public SelecFichier(String s, final JPanel pane, final int num){
		super(s);
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix = new JFileChooser();
				choix.setCurrentDirectory(dir);
				choix.setMultiSelectionEnabled(true);

				returnVal = choix.showOpenDialog(null); // on fait apparaitre la
				// fenetre de selection
				// des fichiers
				// returnVal permet de tester si l'utilisateur a clique sur OK
				// ou annuler (ou autres...)
				fichier = new File(choix.getSelectedFile().getAbsolutePath()); // Recuperation du fichier selectionne
				dir = choix.getCurrentDirectory();
				AfficheGraphe graphe=new AfficheGraphe();
				try {
					graphe.calculeGrapheDat(fichier,pane);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

	/*
	 * Pour le panel Affiche
	 */
	public SelecFichier(String s, final PanelAffiche affiche){
		super(s);
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix = new JFileChooser();
				choix.setCurrentDirectory(dir);
				choix.setMultiSelectionEnabled(true);
				choix.addChoosableFileFilter(filtreMcs);
				choix.addChoosableFileFilter(filtreMcs);
				returnVal = choix.showOpenDialog(null); // on fait apparaitre la
				// fenetre de selection
				// des fichiers
				// returnVal permet de tester si l'utilisateur a cliqu� sur OK
				// ou annuler (ou autres...)
				fichier = new File(choix.getSelectedFile().getAbsolutePath()); // Recuperation du fichier selectionne
				dir = choix.getCurrentDirectory();

				try {
					affiche.affGraphe(fichier);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		


	}
	
	/*
	 * Pour le Panel du Fit (mutli selection)
	 */
	public SelecFichier(String s , final PanelMulti pane){
		super(s);
		
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix = new JFileChooser(".");
				choix.setCurrentDirectory(dir);
				choix.setMultiSelectionEnabled(true);// Permet la selection
				// simultan�e de
				// plusieurs fichiers

				choix.addPropertyChangeListener(new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						if (JFileChooser.SELECTED_FILES_CHANGED_PROPERTY.equals(evt.getPropertyName())) {
							JFileChooser choix = (JFileChooser) evt.getSource();
							File[] oldFiles = (File[]) evt.getOldValue();
							File[] newFiles = (File[]) evt.getNewValue();

							// Obtenir la liste des fichiers selectionnes
							files = choix.getSelectedFiles();
							// Memorise le dernier dossier visiter
							dir = choix.getCurrentDirectory();
				

						}
					}
				});
				returnVal = choix.showOpenDialog(null);
				pane.addLines(files);
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

	}

}
