package almoss;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/*
 * Bouton permettant de supprimer une ligne dans la liste des fichiers
 */

public class ButtonDel extends JButton implements MouseListener {
	int posi;
	PanelList panel;
	
	public ButtonDel(PanelList pane, int num){
		super("suprimer");
		posi =num;// On récupère la clé pour pouvoir récupérer le JPanel correspondant (dans Le HashMap de pane)
		panel = pane;
		addMouseListener(this);
	}
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		panel.remove(panel.getPanelByKey(posi)); // Supprime le JPanel récupérer grace à la clé
		panel.removeMap(posi);
		panel.setFilesPaneMulti();
		panel.revalidate();
		panel.repaint();
		if(panel.isEmpty()){ // si il n'y a plus de fichier dans la liste :
			panel.getFit().setEnabled(false); // on met le bouton "lancer fit" non disponible
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
