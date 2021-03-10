package game;

import javax.swing.JFrame;

import ressources.Constantes;

public class Main {
	
/**** VARIABLES ****/
	
	public static Scene scene;
	public static boolean game = true;
	
	
/**** METHODES ****/
	public static void main(String[] args) {
		
		JFrame screen = new JFrame("Space Invaders");
		screen.setSize(Constantes.LARGO_SCREEN, Constantes.ALTO_SCREEN);
		screen.setResizable(false);
		screen.setLocationRelativeTo(null);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setAlwaysOnTop(true);	

		
		scene = new Scene();		
		screen.setContentPane(scene);
		
		screen.setVisible(true);
	}

}



