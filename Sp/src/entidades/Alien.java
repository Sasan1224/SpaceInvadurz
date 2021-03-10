package entidades;

import javax.swing.ImageIcon;

import ressources.Constantes;

public class Alien extends Entite {
	
/**** VARIABLES ****/
	
	
/**** CONSTRUCTEUR ****/
	
	public Alien(int xPos, int yPos, String strImg1, String strImg2) {
		
		
		super.xPos = xPos;
		super.yPos = yPos;
		super.largo = Constantes.LARGO_ALIEN;
		super.alto = Constantes.ALTO_ALIEN;
		super.dx = 0;
		super.dy = 0;
		super.alive = true;
		
		super.strImg1 = strImg1;
		super.strImg2 = strImg2;
		super.strImg3 = "/images/alienMeurt.png";
		
		super.ico = new ImageIcon(getClass().getResource(super.strImg1));
		super.img = this.ico.getImage();
	}
	
	
/**** METHODES ****/
	public void choixImage(boolean pos1) {
		
		if(this.alive == true) {
		 if(pos1 == true) {super.ico = new ImageIcon(getClass().getResource(strImg1));} 
		  else {super.ico = new ImageIcon(getClass().getResource(strImg2));}
		}
		else {super.ico = new ImageIcon(getClass().getResource(strImg3));}		
		super.img = this.ico.getImage(); 
	}

}
