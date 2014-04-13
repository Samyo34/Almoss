package almoss;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class ExecOpe extends JButton{
	
	File fich = new File("D:/1.MCS");
	File fi2 = new File("D:/2.Mcs");

	public ExecOpe(String s,final JComboBox box, final SelecFichier fich1, final SelecFichier fich2, final JComboBox type) throws IOException{
		super(s);
		addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(box.getSelectedItem()=="Addition"){
					if (type.getSelectedItem()==".txt"){
						try {
							System.out.println(fich1.getFichier().getAbsolutePath());
							Addition add = new Addition(fich1.getFichier(),fich2.getFichier(),0);
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
					}else{
						try {
							Addition add = new Addition(fich1.getFichier(),fich2.getFichier(),1);
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
					}
					
				}else{
					if (type.getSelectedItem()==".txt"){
						try {
							Soustraction add = new Soustraction(fich1.getFichier(),fich2.getFichier(),0);
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
					}else{
						try {
							Soustraction add = new Soustraction(fich1.getFichier(),fich2.getFichier(),1);
						} catch (IOException e1) {

							e1.printStackTrace();
						}
					}
					
				}
			}

		});
	}
}
	
	

