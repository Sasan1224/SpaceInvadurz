package entidades;

import java.awt.Graphics;
import java.util.Random;

import game.Main;
import ressources.Audio;
import ressources.Chrono;
import ressources.Constantes;

public class GrupoAliens {

/**** VARIABLES ****/	

	
	private Alien tabAlien[][] = new Alien [5][10];
	private boolean vaADroite, pos1;
	private int vitesse;

	private int[] tabAlienMort = {-1,-1}; 
	
	Random hasard = new Random();
	
	private int nombreAliens = Constantes.NOMBRE_ALIENS;
	
	private int compteurSonAlien = 0;
	
	
/**** CONSTRUCTEUR ****/
	
	public GrupoAliens() {	
		
		this.initTableauAliens();
		this.vaADroite = true;
		this.pos1 =true;
		this.vitesse = Constantes.ALIVE_ALIEN;
	}

	
/**** METHODES ****/
		
	private void initTableauAliens() {
		
		for(int colonne=0; colonne<10; colonne++) {
			this.tabAlien[0][colonne] = new Alien(Constantes.X_POS_INIT_ALIEN + (Constantes.LARGO_ALIEN + Constantes.DIF_COLUMNAS_ALIEN) * colonne, 
					Constantes.ALT_INIT_ALIEN, "/images/alienHaut1.png", "/images/alienHaut2.png");
			for(int ligne=1; ligne<3; ligne++) {
				this.tabAlien[ligne][colonne] = new Alien(Constantes.X_POS_INIT_ALIEN + (Constantes.LARGO_ALIEN + Constantes.DIF_COLUMNAS_ALIEN) *
						colonne, Constantes.ALT_INIT_ALIEN + Constantes.DIF_LINEAS_ALIEN * ligne, "/images/alienMilieu1.png", "/images/alienMilieu2.png");
			}
			for(int ligne=3; ligne<5; ligne++) {	
				this.tabAlien[ligne][colonne] = new Alien(Constantes.X_POS_INIT_ALIEN + (Constantes.LARGO_ALIEN + Constantes.DIF_COLUMNAS_ALIEN)
					* colonne, Constantes.ALT_INIT_ALIEN + Constantes.DIF_LINEAS_ALIEN * ligne, "/images/alienBas1.png", "/images/alienBas2.png");
			}	
		}
	}
	
	public void dessinAliens(Graphics g) {
		if(Chrono.compteTours % (100 - 10 * this.vitesse) == 0) {this.deplacementAliens();}
		
		for(int colonne=0; colonne<10; colonne++) {
			for(int ligne=0; ligne<5; ligne++) {
				if(this.tabAlien[ligne][colonne] != null) {
					this.tabAlien[ligne][colonne].choixImage(pos1);
					g.drawImage(this.tabAlien[ligne][colonne].getImg(), this.tabAlien[ligne][colonne].getxPos(), this.tabAlien[ligne][colonne].getyPos(),
						null);
				}
			}
		}		
	}
		
	private boolean touchBordeIzq() {
		
		boolean reponse = false;
		for(int colonne=0; colonne<10; colonne++) {
			for(int ligne=0; ligne<5; ligne++) {
				if(this.tabAlien[ligne][colonne] != null) {
					if(this.tabAlien[ligne][colonne].getxPos() < Constantes.MARCO_SCREEN){
						reponse = true;
						break;
					}
				}
			}
		}
		return reponse;
	}
	
	private boolean touchBordeDer() {
	
		boolean reponse = false;
		for(int colonne=0; colonne<10; colonne++) {
			for(int ligne=0; ligne<5; ligne++) {
				if(this.tabAlien[ligne][colonne] != null) {
					if(this.tabAlien[ligne][colonne].getxPos() > 
					Constantes.LARGO_SCREEN - Constantes.LARGO_ALIEN - Constantes.DX_ALIEN - Constantes.MARCO_SCREEN) {
						reponse = true;
						break;
					}
				}
			}
		}
		return reponse;
	}
	
	public void alienTourneEtDescend() {
		// Méthode qui change le sens de déplacement de l'alien et le descend d'un cran
		if(this.touchBordeDer() == true) {			
			for(int colonne=0; colonne<10; colonne++) {
				for(int ligne=0; ligne<5; ligne++) {
					if(this.tabAlien[ligne][colonne] != null) {
						this.tabAlien[ligne][colonne].setyPos(this.tabAlien[ligne][colonne].getyPos() + Constantes.DY_ALIEN);
					}
				}
			}
			this.vaADroite = false;
			if(this.vitesse < 9) {this.vitesse++;}
		} else {
			if(this.touchBordeIzq() == true) {			
				for(int colonne=0; colonne<10; colonne++) {
					for(int ligne=0; ligne<5; ligne++) {
						if(this.tabAlien[ligne][colonne] != null) {
						this.tabAlien[ligne][colonne].setyPos(
							this.tabAlien[ligne][colonne].getyPos() + Constantes.DY_ALIEN);
						}
					}
				}
				this.vaADroite = true;
				if(this.vitesse < 9) {this.vitesse++;}
			}
		}
	}
	
	public void deplacementAliens() {
		
		if(this.tabAlienMort[0] != -1) { 
			elimineAlienMort(tabAlienMort);
			tabAlienMort[0] = -1; 
		}
		if(this.vaADroite == true) { 
			for(int colonne=0; colonne<10; colonne++) {
				for(int ligne=0; ligne<5; ligne++) {	
					if(this.tabAlien[ligne][colonne] != null) {
						this.tabAlien[ligne][colonne].setxPos(this.tabAlien[ligne][colonne].getxPos() + Constantes.DX_ALIEN);
					}
				}
			}
		}else{ 
			for(int colonne=0; colonne<10; colonne++) {
				for(int ligne=0; ligne<5; ligne++) {
					if(this.tabAlien[ligne][colonne] != null) {
						this.tabAlien[ligne][colonne].setxPos(this.tabAlien[ligne][colonne].getxPos() - Constantes.DX_ALIEN);
					}
				}
			}
		}
		
		this.joueSonAlien();
		
		this.compteurSonAlien++;
		
		if(this.pos1 == true) {this.pos1 = false;} 
		else {this.pos1 = true;}
		
		this.alienTourneEtDescend();
	}
	
	public void tirVaisseauToucheAlien(TiroPlayer tiroPlayer) {
		
		for(int colonne=0; colonne<10; colonne++) {
			for(int ligne=0; ligne<5; ligne++) {
				if(this.tabAlien[ligne][colonne] != null) {
					if(tiroPlayer.tueAlien(this.tabAlien[ligne][colonne]) == true) {
						this.tabAlien[ligne][colonne].alive = false; 
						tiroPlayer.yPos = -1; 
						
						this.tabAlienMort[0] = ligne;
						this.tabAlienMort[1] = colonne; 
						if(ligne == 0) {
							Main.scene.score = Main.scene.score + Constantes.VALOR_ALIEN_UP;}
						else if(ligne>0 && ligne<3) {
							Main.scene.score = Main.scene.score + Constantes.VALOR_ALIEN_MIDDLE;}
						else {
							Main.scene.score = Main.scene.score + Constantes.VALOR_ALIEN_DOWN;}	
						break;
					}
				}					
			}					
		}
	}

	private void elimineAlienMort(int[] tabAlienMort) {
		
		this.tabAlien[tabAlienMort[0]][tabAlienMort[1]] = null;
		this.nombreAliens--;
	}
	
	public int[] choixAlienQuiTire() {
	
		int positionAlien[] = {-1,-1};		
		if(this.nombreAliens != 0) { 
			do {int colonne = hasard.nextInt(10); 
				for(int ligne=4;ligne>=0;ligne--) {
					if(tabAlien[ligne][colonne]!= null) {
						positionAlien[0] = this.tabAlien[ligne][colonne].getxPos();
						positionAlien[1] = this.tabAlien[ligne][colonne].getyPos();
						break;
					}
				}
			} while(positionAlien[0] == -1);
		}	
		return positionAlien;
	}
	
	private void joueSonAlien() { 
		int compteur = this.compteurSonAlien % 4;
		if(compteur==0) {Audio.playSound("/sons/sonAlien1.wav");}
		else if(compteur==1) {Audio.playSound("/sons/sonAlien2.wav");}
		else if(compteur==2) {Audio.playSound("/sons/sonAlien3.wav");}
		else {Audio.playSound("/sons/sonAlien4.wav");}
	}
	
	public int getNombreAliens() {return nombreAliens;}
	
	public int positionAlienLePlusBas() {
		
		int posBas = 0, posBasFinal = 0;
		for(int colonne=1; colonne<10;colonne++) {
			for(int ligne=4; ligne>=0;ligne--) {
				if(this.tabAlien[ligne][colonne] != null) {
					posBas = this.tabAlien[ligne][colonne].yPos + this.tabAlien[ligne][colonne].alto;
					break;
				}			
			}
			if(posBas > posBasFinal) {posBasFinal = posBas;}
		}
		return posBasFinal;
	}
}




