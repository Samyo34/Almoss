package fonction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ExecOpe extends JButton{
	

	public ExecOpe(String s,final JComboBox box, final ArrayList<File> list, final JComboBox type, final JPanel graphe) throws IOException{
		super(s);
		addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(list.size()>=2){
					if(box.getSelectedItem()=="Addition"){// si l'utilisateur a selectionne addition
						if (type.getSelectedItem()==".txt"){// si l'utilisateur a demander un fichier txt en sortie
							try {
								Addition add = new Addition(list,0,graphe); // execution de l'addition avec le parametre 0 (pour le fichier txt)
							} catch (IOException e1) {
								
								e1.printStackTrace();
							}
						}else{
							try {
								Addition add = new Addition(list,1,graphe);// execution de l'addition avec le parametre 1 (pour le fichier mcs)
							} catch (IOException e1) {
								
								e1.printStackTrace();
							}
						}
						
					}else{
						if (type.getSelectedItem()==".txt"){
							try {
								Soustraction add = new Soustraction(list,0,graphe);// execution de la soustraction avec le parametre 0 (pour le fichier txt)
							} catch (IOException e1) {
								
								e1.printStackTrace();
							}
						}else{
							try {
								Soustraction add = new Soustraction(list,1,graphe);// execution de la soustraction avec le parametre 1 (pour le fichier mcs)
							} catch (IOException e1) {
	
								e1.printStackTrace();
							}
						}
						
					}
				}else{
					JOptionPane.showMessageDialog(graphe,"Vous n'avez sélectionner qu'un seul fichier",null, JOptionPane.ERROR_MESSAGE);
				}
			}

		});
	}
}
	
	

