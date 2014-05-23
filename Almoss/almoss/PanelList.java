package almoss;

import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class PanelList extends JPanel{
	
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
		
		ButtonDel del = new ButtonDel(this, i);
		
		JPanel paneBut = new JPanel();
		GridLayout gl = new GridLayout(2,2);
		gl.setHgap(3); //Cinq pixels d'espace entre les colonnes (H comme Horizontal)
		gl.setVgap(3);
		paneBut.setLayout(gl);
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
	
	public HashMap getListFichier(){
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
