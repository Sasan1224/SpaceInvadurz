package entidades;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

import game.Main;
import ressources.Audio;
import ressources.Chrono;
import ressources.Constantes;

public class TiroAlien extends Entite {

/**** VARIABLES ****/	
	
	Random hasard = new Random();


/**** CONSTRUCTEUR ****/	
	
	public TiroAlien(int [] tabPositionAlien) {
		
		// Initialisation des variables de la super classe
		super.xPos = tabPositionAlien[0] + Constantes.LARGO_ALIEN /2 - 1;
		super.yPos = tabPositionAlien[1] + Constantes.ALTO_ALIEN;
		super.largo = Constantes.LARGO_TIRO_ALIEN;
		super.alto = Constantes.ALTO_TIRO_ALIEN;
		super.dx = 0;
		super.dy = Constantes.DY_TIRO_ALIEN;
		// Adresse des images du vaisseau
		super.strImg1 = "/images/tirAlien1.png";
		super.strImg2 = "/images/tirAlien2.png";
		super.strImg3 = "";
		// Chargement de l'image du tir de l'alien
		if(hasard.nextInt(2) == 0) {
			super.ico = new ImageIcon(getClass().getResource(super.strImg1));}
		else {super.ico = new ImageIcon(getClass().getResource(super.strImg2));}
		super.img = this.ico.getImage();
	}
	
	
/**** METHODES ****/
	
	public int deplacementTirAlien() {
		if(Chrono.compteTours % 4 == 0) {
			if(this.yPos < 600) {this.yPos = this.yPos + Constantes.DY_TIRO_ALIEN;}			
		}
		return yPos;
	}	
	
	public void dessinTirAlien(Graphics g) {
		g.drawImage(this.img, this.xPos, this.deplacementTirAlien(), null);
	}		
	
	private boolean tirAlienAHauteurDeChateau() { 
		// Renvoie vrai si le tir du vaisseau est à hauteur des châteaux
		if(this.yPos < Constantes.Y_POS_HOME + Constantes.ALTO_HOME && this.yPos + this.alto > Constantes.Y_POS_HOME) {return true;}
		else {return false;}	
	}
	
	private int chateauProche() {
		// Renvoie le numéro du château (0,1,2 ou 3) dans la zone de tir du vaisseau
		int numeroChateau = -1;
		int colonne = -1;
		while (numeroChateau == -1 && colonne < 4) {
			colonne++;			
			if(this.xPos + this.largo - 1 > Constantes.MARCO_SCREEN + Constantes.X_POS_INIT_HOME + colonne * (Constantes.LARGO_HOME + 
			Constantes.DIF_HOME) 
			   && this.xPos + 1 < Constantes.MARCO_SCREEN + Constantes.X_POS_INIT_HOME + Constantes.LARGO_HOME + 
			   colonne * (Constantes.LARGO_HOME + Constantes.DIF_HOME)) {	
				numeroChateau = colonne;
			}
		}
		return numeroChateau;
	}
	
	private int abscisseContactTirAlienChateau(Home home) {		
		int xPosTirAlien = -1;
		if(this.xPos + this.largo > home.getxPos() && this.xPos < home.getxPos() + Constantes.LARGO_HOME){
			xPosTirAlien = this.xPos;}					
		return xPosTirAlien;
	}
	
	public int[] tirAlienToucheChateau() {
		int[] tabRep = {-1,-1}; 
		if(this.tirAlienAHauteurDeChateau() == true) { 
			tabRep[0] = this.chateauProche(); 
			if(tabRep[0] != -1) {
				tabRep[1] = this.abscisseContactTirAlienChateau(
				Main.scene.tabHome[tabRep[0]]);}		 
		}		
		return tabRep;
	}	
	
	public void TirAlienDetruitChateau(Home tabHome[]) {
		int[] tab = this.tirAlienToucheChateau(); 
		if(tab[0] != -1) { 
			if(tabHome[tab[0]].findBrickHome(tabHome[tab[0]].findColumnaHome(tab[1])) != -1
				&& tabHome[tab[0]].findBrickHome(tabHome[tab[0]].findColumnaHome(tab[1])) != 27) {
				tabHome[tab[0]].rotoBrickHouse(tab[1]); 								
				this.yPos = 700; 
			}
		}
	}
	
	public boolean touchPlayer(Player player) {
		
		if(this.yPos < player.getyPos() + player.getAlto() && this.yPos + this.alto > player.getyPos() 
			&& this.xPos + this.largo > player.getxPos() && this.xPos < player.getxPos() + player.getLargo()){
			    this.yPos = 700;
			    Audio.playSound("/sons/sonDestructionVaisseau.wav");
				return true;
			} 
		else{return false;}
	}
}
