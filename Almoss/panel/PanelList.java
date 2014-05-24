package panel;

import java.awt.GridLayout;
import java.io.File;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;

import bouton.ButtonDel;
import bouton.ButtonFit;
import bouton.SelecFichier;

/*
 * Panel contenant la liste des fichiers selectionne par l'utilisateur dans l'onglet PanelMutli
 * La liste des fichiers est conetnu dans un HashMap (map) avec le numeros (i) et le Panel PanelLFichier
 */
public class PanelList extends JPanel{

	private static final long serialVersionUID = 1L;
	JLabel textNom;
	JLabel textEtat;
	File fichier;
	SelecFichier select;
	HashMap<Integer, PanelLFichier> map = new HashMap<Integer, PanelLFichier>();
	ButtonFit bFit;
	PanelLFichier pane;
	PanelMulti paneMulti;
	
	static int i =0;
	
	public PanelList(ButtonFit fit, PanelMulti pane){
		super();
		setLayout(new GridLayout(10,1));
		bFit = fit;
		paneMulti= pane;

	}
	
	public void addLine(File file, String etat){
		textNom = new JLabel(file.getName());
		textEtat = new JLabel(etat);
		select = new SelecFichier(".par");
		
		ButtonDel del = new ButtonDel(this, i);// Bouton premettant de supprimer le fichier de la liste
		
		JPanel paneBut = new JPanel();
		GridLayout gl = new GridLayout(2,2);
		gl.setHgap(3); //Trois pixels d'espace entre les colonnes
		gl.setVgap(3);//Trois pixels d'espace entre les lignes
		paneBut.setLayout(gl);
		
		// Ajout des boutons de selection de fichier .par et suppression du fichier
		paneBut.add(select);
		paneBut.add(del);
		
		
		pane = new PanelLFichier(textNom, textEtat,paneBut);
		pane.setFiles(file, select);
		
		map.put((Integer) i, pane);
		i++;
		
		add(pane);
		revalidate();
		repaint();
	}

	public void setFilesPaneMulti(){
		paneMulti.setFilesFit(map);
	}
	
	public JPanel getPanelByKey(int key){
		return map.get(key);
	}
	
	public HashMap<Integer, PanelLFichier> getListFichier(){
		return map;
	}
	
	public SelecFichier getPar(){
		return select;
	}
	
	public boolean isEmpty(){
		return map.isEmpty();
	}
	
	public ButtonFit getFit(){
		return bFit;
	}
	
	public void removeMap(int key){
		map.remove(key);
	}
}
