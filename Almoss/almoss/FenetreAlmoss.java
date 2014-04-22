package almoss;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class FenetreAlmoss extends JFrame{
	public static final long serialVersionUID= 2005L;
	private PanelAlmoss onglets;
	
	public FenetreAlmoss() throws FileNotFoundException, IOException {
		this.setTitle("Almoss");
	    this.setSize(700, 600);
	    this.setLocationRelativeTo(null);
	    MenuBarAlmoss menu = new MenuBarAlmoss(this);
	    setJMenuBar(menu);
	    onglets = new PanelAlmoss(menu);
	    this.setContentPane(onglets);               
	    this.setVisible(true);
	    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	

}
