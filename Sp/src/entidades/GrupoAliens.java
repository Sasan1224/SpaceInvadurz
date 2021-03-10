package entidades;

import java.awt.Graphics;
import java.util.Random;

import game.Main;
import ressources.Audio;
import ressources.Chrono;
import ressources.Constantes;

public class GrupoAliens {



	
	private Alien tabAlien[][] = new Alien [5][10];
	private boolean vaADroite, pos1;
	private int vitesse;

	private int[] tabAlienMort = {-1,-1}; 
	
	Random hasard = new Random();
	
	private int nombreAliens = Constantes.NOMBRE_ALIENS;
	
	private int compteurSonAlien = 0;
	
	

	
	public GrupoAliens() {	
		
		this.initTableauAliens();
		this.vaADroite = true;
		this.pos1 =true;
		this.vitesse = Constantes.ALIVE_ALIEN;
	}

	

		
	private void initTableauAliens() {
		
		for(int columna=0; columna<10; columna++) {
			this.tabAlien[0][columna] = new Alien(Constantes.X_POS_INIT_ALIEN + (Constantes.LARGO_ALIEN + Constantes.DIF_COLUMNAS_ALIEN) * columna, 
					Constantes.ALT_INIT_ALIEN, "/images/alienHaut1.png", "/images/alienHaut2.png");
			for(int linea=1; linea<3; linea++) {
				this.tabAlien[linea][columna] = new Alien(Constantes.X_POS_INIT_ALIEN + (Constantes.LARGO_ALIEN + Constantes.DIF_COLUMNAS_ALIEN) *
						columna, Constantes.ALT_INIT_ALIEN + Constantes.DIF_LINEAS_ALIEN * linea, "/images/alienMilieu1.png", "/images/alienMilieu2.png");
			}
			for(int linea=3; linea<5; linea++) {	
				this.tabAlien[linea][columna] = new Alien(Constantes.X_POS_INIT_ALIEN + (Constantes.LARGO_ALIEN + Constantes.DIF_COLUMNAS_ALIEN)
					* columna, Constantes.ALT_INIT_ALIEN + Constantes.DIF_LINEAS_ALIEN * linea, "/images/alienBas1.png", "/images/alienBas2.png");
			}	
		}
	}
	
	public void dessinAliens(Graphics g) {
		if(Chrono.compteTours % (100 - 10 * this.vitesse) == 0) {this.deplacementAliens();}
		
		for(int columna=0; columna<10; columna++) {
			for(int linea=0; linea<5; linea++) {
				if(this.tabAlien[linea][columna] != null) {
					this.tabAlien[linea][columna].choixImage(pos1);
					g.drawImage(this.tabAlien[linea][columna].getImg(), this.tabAlien[linea][columna].getxPos(), this.tabAlien[linea][columna].getyPos(),
						null);
				}
			}
		}		
	}
		
	private boolean touchBordeIzq() {
		
		boolean reponse = false;
		for(int columna=0; columna<10; columna++) {
			for(int linea=0; linea<5; linea++) {
				if(this.tabAlien[linea][columna] != null) {
					if(this.tabAlien[linea][columna].getxPos() < Constantes.MARCO_SCREEN){
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
		for(int columna=0; columna<10; columna++) {
			for(int linea=0; linea<5; linea++) {
				if(this.tabAlien[linea][columna] != null) {
					if(this.tabAlien[linea][columna].getxPos() > 
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
		
		if(this.touchBordeDer() == true) {			
			for(int columna=0; columna<10; columna++) {
				for(int linea=0; linea<5; linea++) {
					if(this.tabAlien[linea][columna] != null) {
						this.tabAlien[linea][columna].setyPos(this.tabAlien[linea][columna].getyPos() + Constantes.DY_ALIEN);
					}
				}
			}
			this.vaADroite = false;
			if(this.vitesse < 9) {this.vitesse++;}
		} else {
			if(this.touchBordeIzq() == true) {			
				for(int columna=0; columna<10; columna++) {
					for(int linea=0; linea<5; linea++) {
						if(this.tabAlien[linea][columna] != null) {
						this.tabAlien[linea][columna].setyPos(
							this.tabAlien[linea][columna].getyPos() + Constantes.DY_ALIEN);
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
			for(int columna=0; columna<10; columna++) {
				for(int linea=0; linea<5; linea++) {	
					if(this.tabAlien[linea][columna] != null) {
						this.tabAlien[linea][columna].setxPos(this.tabAlien[linea][columna].getxPos() + Constantes.DX_ALIEN);
					}
				}
			}
		}else{ 
			for(int columna=0; columna<10; columna++) {
				for(int linea=0; linea<5; linea++) {
					if(this.tabAlien[linea][columna] != null) {
						this.tabAlien[linea][columna].setxPos(this.tabAlien[linea][columna].getxPos() - Constantes.DX_ALIEN);
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
		
		for(int columna=0; columna<10; columna++) {
			for(int linea=0; linea<5; linea++) {
				if(this.tabAlien[linea][columna] != null) {
					if(tiroPlayer.tueAlien(this.tabAlien[linea][columna]) == true) {
						this.tabAlien[linea][columna].alive = false; 
						tiroPlayer.yPos = -1; 
						
						this.tabAlienMort[0] = linea;
						this.tabAlienMort[1] = columna; 
						if(linea == 0) {
							Main.scene.score = Main.scene.score + Constantes.VALOR_ALIEN_UP;}
						else if(linea>0 && linea<3) {
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
			do {int columna = hasard.nextInt(10); 
				for(int linea=4;linea>=0;linea--) {
					if(tabAlien[linea][columna]!= null) {
						positionAlien[0] = this.tabAlien[linea][columna].getxPos();
						positionAlien[1] = this.tabAlien[linea][columna].getyPos();
						break;
					}
				}
			} while(positionAlien[0] == -1);
		}	
		return positionAlien;
	}
	
	private void joueSonAlien() { 
		int contador = this.compteurSonAlien % 4;
		if(contador==0) {Audio.playSound("/sons/sonAlien1.wav");}
		else if(contador==1) {Audio.playSound("/sons/sonAlien2.wav");}
		else if(contador==2) {Audio.playSound("/sons/sonAlien3.wav");}
		else {Audio.playSound("/sons/sonAlien4.wav");}
	}
	
	public int getNombreAliens() {return nombreAliens;}
	
	public int positionAlienLePlusBas() {
		
		int posBas = 0, posBasFinal = 0;
		for(int columna=1; columna<10;columna++) {
			for(int linea=4; linea>=0;linea--) {
				if(this.tabAlien[linea][columna] != null) {
					posBas = this.tabAlien[linea][columna].yPos + this.tabAlien[linea][columna].alto;
					break;
				}			
			}
			if(posBas > posBasFinal) {posBasFinal = posBas;}
		}
		return posBasFinal;
	}
}




