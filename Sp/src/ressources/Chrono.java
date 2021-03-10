package ressources;

import game.Main;

public class Chrono implements Runnable {

/**** VARIABLES ****/
	
	private final int PAUSE = 5; 
	public static int compteTours = 0;
	
	
/**** METHODES ****/
	
	@Override
	public void run() {		
		while(Main.game == true){ 
			compteTours++;
			Main.scene.repaint(); 
			try {Thread.sleep(PAUSE);}
			catch (InterruptedException e) {}
		}
	}	
		
}