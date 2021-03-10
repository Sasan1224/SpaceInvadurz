package entidades;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import ressources.Audio;
import ressources.Chrono;
import ressources.Constantes;

public class Superalien extends Entite {

	/**** VARIABLES ****/

	public Audio musiqueSoucoupe = new Audio("/sons/sonSoucoupePasse.wav");
	public Audio musiqueDestructionSoucoupe = new Audio("/sons/sonDestructionSoucoupe.wav");

	private int contador = 0;
	
	
/**** CONSTRUCTEUR ****/
	
	public Superalien() {		
		// Initialisation des variables de la classe mère
		super.xPos = Constantes.X_POS_INIT_SUPERALIEN;
		super.yPos = Constantes.Y_POS_SUPERALIEN;
		super.largo = Constantes.LARGO_SUPERALIEN;
		super.alto = Constantes.ALTO_SUPERALIEN;
		super.dx = Constantes.DX_SUPERALIEN;
		super.dy = 0;
		// Adresse des images du vaisseau
		this.strImg1 = "/images/soucoupe.png";
		this.strImg2 = "/images/soucoupe100.png";
		this.strImg3 = "";
		// Chargement de l'image du vaisseau
		super.ico = new ImageIcon(getClass().getResource(strImg1));
		super.img = this.ico.getImage();
		super.alive = true;

		this.musiqueSoucoupe.play();
		this.musiqueDestructionSoucoupe.stop();
		this.contador = 0;
	}
	
/**** METHODES ****/	
	
	public int despSuperalien() {
		// Renvoie la nouvelle position de la soucoupe après déplacement éventuel
		if(this.alive && Chrono.compteTours % 2 == 0) {
			if (this.xPos > 0) {this.xPos = this.xPos - this.dx;}
			else {this.xPos = Constantes.X_POS_INIT_SUPERALIEN;}	
		}
		return this.xPos;
	}
		
	public void designSuperalien(Graphics g) {	
		if(this.alive == false) {this.destructionSuperalien();}
		g.drawImage(this.img, this.despSuperalien(), this.yPos, null);			
	}	
	
	public void destructionSuperalien() {
		if(contador < 300) {
			super.ico = new ImageIcon(getClass().getResource(super.strImg2));
			super.img = this.ico.getImage();
			contador++;
		}else {this.xPos = Constantes.X_POS_INIT_SUPERALIEN;}		
		
	}
}
