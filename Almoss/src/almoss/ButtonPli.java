package almoss;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;

public class ButtonPli extends JButton{
	
	public ButtonPli(String s, final File fichier){
		
		super(s);
		addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("test");
				Pliage pli = new Pliage(fichier);
				try {
					pli.pli(fichier);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

}
