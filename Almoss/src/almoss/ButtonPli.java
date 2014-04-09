package almoss;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;

public class ButtonPli extends JButton{
	
	public ButtonPli(String s, File fichier){
		
		super(s);
		addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//TODO: lancer le pli (new Pli(fichier))
			}
		});
	}

}
