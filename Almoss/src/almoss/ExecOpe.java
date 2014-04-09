package almoss;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class ExecOpe extends JButton{
	
	public ExecOpe(String s,final JComboBox box, final File fich1, final File fich2, final JComboBox type) throws IOException{
		super(s);
		addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(box.getSelectedItem()=="Addition"){
					if (type.getSelectedItem()==".txt"){
						try {
							Addition add = new Addition(fich1,fich2,0);
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
					}else{
						try {
							Addition add = new Addition(fich1,fich2,1);
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
					}
					
				}else{
					if (type.getSelectedItem()==".txt"){
						try {
							Soustraction add = new Soustraction(fich1,fich2,0);
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
					}else{
						try {
							Soustraction add = new Soustraction(fich1,fich2,1);
						} catch (IOException e1) {

							e1.printStackTrace();
						}
					}
					
				}
			}

		});
	}
}
	
	

