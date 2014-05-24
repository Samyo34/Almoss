package fonction;

import java.io.IOException;

import com.developpez.adiguba.shell.Shell;

/*
 * Thread qui lance l'execution de Fullham avec la commande
 */
public class ExecFit extends Thread{
	
	String cmd;
	
	public ExecFit(){
		super();
	}
	
	public void setCmd(String s){
		cmd=s;
	}
	
	public void run(){
		try {
			Shell.system(cmd);
			//this.notifyAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
