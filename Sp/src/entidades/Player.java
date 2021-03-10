package entidades;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import game.Main;
import ressources.Chrono;
import ressources.Constantes;

public class Player extends Entite {
	

	private int contador = 0;
	
	
		
		public Player() {
			
			
			super.xPos = Constantes.X_POS_INIT_PLAYER;
			super.yPos = Constantes.Y_POS_PLAYER;
			super.largo = Constantes.LARGO_PLAYER;
			super.alto= Constantes.ALTO_PLAYER;
			super.dx = 0;
			super.dy = 0;
			
			super.strImg1 = "/images/vaisseau.png";
			super.strImg2 = "/images/vaisseauDetruit1.png";
			super.strImg3 = "/images/vaisseauDetruit2.png";
			
			super.ico = new ImageIcon(getClass().getResource(super.strImg1));
			super.img = this.ico.getImage();
			super.alive = true;
		}
		
		
	/**** METHODES ****/
	public int deplacementVaisseau() {
		
		if(this.dx < 0){if(this.xPos > Constantes.LIMITE_IZQ_PLAYER) {this.xPos = this.xPos + this.dx;}
		}else if(dx > 0) {if(this.xPos + this.dx < Constantes.LIMITE_DER_PLAYER) {this.xPos = this.xPos + this.dx;}}
		return this.xPos;
	}
	
	public void dessinVaisseau(Graphics g) {
		if(this.alive == false) {this.destructionVaisseau();}
		g.drawImage(this.img, this.deplacementVaisseau(), this.yPos, null);
	}
	
	public void destructionVaisseau() {
		if(contador < 300) {
			if(Chrono.compteTours % 2 == 0) {super.ico = new ImageIcon(getClass().getResource(super.strImg2));}
			else {super.ico = new ImageIcon(getClass().getResource(super.strImg3));}
			contador++;
		}else {Main.game = false;}		
		super.img = this.ico.getImage();
	}
}
