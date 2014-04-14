package almoss;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPli extends JButton{
	
	public ButtonPli(String s, final SelecFichier ouvr,final JPanel graphe){
		
		super(s);
		addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("test");
				Pliage pli = new Pliage(ouvr.getFichier());
				try {
					pli.pli(ouvr.getFichier(),graphe);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

}
