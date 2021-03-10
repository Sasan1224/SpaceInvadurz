package entidades;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import game.Main;
import ressources.Audio;
import ressources.Constantes;

public class TiroPlayer extends Entite {
	

	
	private boolean PlayerTire = false;
	

	
	public TiroPlayer() {
		
		
		super.xPos = 0;
		super.yPos = Constantes.Y_POS_PLAYER - Constantes.ALTO_TIRO_PLAYER;
		super.largo = Constantes.LARGO_TIRO_PLAYER;
		super.alto = Constantes.ALTO_TIRO_PLAYER;
		super.dx = 0;
		super.dy = Constantes.DY_TIRO_ALIEN;
		
		super.strImg1 = "/images/tirVaisseau.png";
		super.strImg2 = "";
		super.strImg3 = "";
		
		super.ico = new ImageIcon(getClass().getResource(super.strImg1));
		super.img = this.ico.getImage();
	}
	
	public boolean isPlayerTire() {return PlayerTire;}

	public void setPlayerTire(boolean PlayerTire) {this.PlayerTire = PlayerTire;}

	public int deplacementTirVaisseau() {
		if(this.PlayerTire == true) {
			if(this.yPos > 0) {this.yPos = this.yPos - Constantes.DY_TIRO_ALIEN;}
			else {this.PlayerTire = false;}
		}		
		return yPos;
	}
	
	public void dessinTirVaisseau(Graphics g) {
		if(this.PlayerTire == true) {
			g.drawImage(this.img, this.xPos, this.deplacementTirVaisseau(), null);}	
	}
	
	public boolean tueAlien(Alien alien) {
		
		if(this.yPos < alien.getyPos() + alien.getAlto() 
			&& this.yPos + this.alto > alien.getyPos() 
			&& this.xPos + this.largo > alien.getxPos() 
			&& this.xPos < alien.getxPos() + alien.getLargo()){
			Audio.playSound("/sons/sonAlienMeurt.wav");
				return true;
			} 
		else{return false;}
	}
	
	private boolean tirVaisseauAHauteurDeChateau() { 
		
		if(this.yPos < Constantes.Y_POS_HOME + Constantes.ALTO_HOME && this.yPos + this.alto > Constantes.Y_POS_HOME) {return true;}
		else {return false;}	
	}
	
	private int chateauProche() {
		
		int numeroChateau = -1;
		int colonne = -1;
		while (numeroChateau == -1 && colonne < 4) {
			colonne++;			
			if(this.xPos + this.largo > Constantes.MARCO_SCREEN + Constantes.X_POS_INIT_HOME + colonne * 
					(Constantes.DIF_HOME + Constantes.DIF_HOME) 
			   && this.xPos < Constantes.MARCO_SCREEN + Constantes.X_POS_INIT_HOME + Constantes.DIF_HOME + colonne * 
			   (Constantes.DIF_HOME + Constantes.DIF_HOME)) {	
				numeroChateau = colonne;
			}
		}
		return numeroChateau;
	}
		
	private int abscisseContactTirChateau(Home home) {
		
		int xPosTirVaisseau = -1;
		if(this.xPos + this.largo > home.getxPos() && this.xPos < home.getxPos() + Constantes.DIF_HOME){xPosTirVaisseau = this.xPos;}	
		return xPosTirVaisseau;
	}
	
	public int[] tirVaisseauToucheChateau() {
		
		int[] tabRep = {-1, -1}; 
		if(this.tirVaisseauAHauteurDeChateau() == true) { 	
			tabRep[0] = this.chateauProche();
			if(tabRep[0] != -1) {
				
				tabRep[1] = this.abscisseContactTirChateau(Main.scene.tabHome[tabRep[0]]);
			}		 
		}		
		return tabRep;
	}
	
	public void tirVaisseauDetruitChateau(Home tabHome[]) {
		int[] tab = this.tirVaisseauToucheChateau(); 
		if(tab[0] != -1) {
			if(tabHome[tab[0]].findBrick(tabHome[tab[0]].findColumnaHome(tab[1])) != -1) {
				tabHome[tab[0]].rotoBrickHouse(tab[1]);								
				this.yPos = -1; 
			}
		}
	}
	
	public boolean destroySuperalien(Superalien superalien) { 
		
		if(this.yPos < superalien.getyPos() + superalien.getAlto() && this.yPos + this.alto > superalien.getyPos() 
			&& this.xPos + this.largo > superalien.getxPos() && this.xPos < superalien.getxPos() + superalien.getLargo()){
				this.PlayerTire = false; 
				return true;
			} 
		else{return false;}
	}
}
