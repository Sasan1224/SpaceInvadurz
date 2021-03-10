package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entidades.Home;
import entidades.GrupoAliens;
import entidades.Superalien;
import entidades.TiroAlien;
import entidades.TiroPlayer;
import entidades.Player;
import ressources.Chrono;
import ressources.Teclado;
import ressources.Constantes;

public class Scene extends JPanel {
	

	
	public Player player = new Player();
	public GrupoAliens grupoAliens = new GrupoAliens();
	public TiroPlayer tiroPlayer = new TiroPlayer();
	
	public Home tabHome[] = new Home[4]; // Création d'un tableau contenant les 4 châteaux
	
	public TiroAlien tirAlien1, tirAlien2, tirAlien3;
	
	public Superalien superalien;
	
	private Font afficheScore = new Font("Arial", Font.PLAIN, 20);
	private Font afficheTexte = new Font("Arial", Font.PLAIN, 80);
	
	public int score = 0;
	

	
	public Scene() {
		super();
		
		
		for(int colonne=0; colonne<4; colonne++) {
			this.tabHome[colonne] = new Home(Constantes.MARCO_SCREEN + 
					Constantes.X_POS_INIT_HOME + colonne * (Constantes.LARGO_HOME + Constantes.DIF_HOME));
		}
		
		
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(new Teclado());
		
		
		Thread chronoEcran = new Thread(new Chrono());
		chronoEcran.start();
	}


	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics g2 = (Graphics2D) g;
		
	
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, Constantes.LARGO_SCREEN, Constantes.ALTO_SCREEN);
		
		
		g2.setColor(Color.GREEN);
		g2.fillRect(30, 530, 535, 5);
		
		
		g.setFont(afficheScore);
		g.drawString("SCORE : " + score, 400, 25);
		
		
		this.player.dessinVaisseau(g2);
		
		
		this.grupoAliens.dessinAliens(g2);
		
		
		this.tiroPlayer.dessinTirVaisseau(g2);
		
		
		this.grupoAliens.tirVaisseauToucheAlien(this.tiroPlayer);
		
		
		for(int columna=0; columna<4; columna++) {this.tabHome[columna].designHome(g2);}
		
	
		if(Chrono.compteTours < 500) {
			g.setFont(afficheTexte);
		    g.drawString("Good luck !", 95, 100);
		}
		
		
		if(this.player.isAlive() == false) {
			g.setFont(afficheTexte);
			g.drawString("GAME OVER", 50, 100);
		}
		
		
		this.tiroPlayer.tirVaisseauDetruitChateau(tabHome);	
		
		
		if(Chrono.compteTours % 500 == 0) {
			tirAlien1 = new TiroAlien(this.grupoAliens.choixAlienQuiTire());}
		if(this.tirAlien1 != null) {
			this.tirAlien1.dessinTirAlien(g2);
			this.tirAlien1.TirAlienDetruitChateau(tabHome);
			if(this.tirAlien1.touchPlayer(player) == true) {this.player.setAlive(false);}
		}
		if(Chrono.compteTours % 750 == 0) {
			tirAlien2 = new TiroAlien(this.grupoAliens.choixAlienQuiTire());}
		if(this.tirAlien2 != null) {
			this.tirAlien2.dessinTirAlien(g2);
			this.tirAlien2.TirAlienDetruitChateau(tabHome); 
			if(this.tirAlien2.touchPlayer(player) == true) {this.player.setAlive(false);}
		}
		if(Chrono.compteTours % 900 == 0) {
			tirAlien3 = new TiroAlien(this.grupoAliens.choixAlienQuiTire());}
		if(this.tirAlien3 != null) {
			this.tirAlien3.dessinTirAlien(g2);
			this.tirAlien3.TirAlienDetruitChateau(tabHome);
			if(this.tirAlien3.touchPlayer(player) == true) {this.player.setAlive(false);}
		}
			
		if(Chrono.compteTours % 2500 == 0) {superalien = new Superalien();}		
		if(this.superalien != null) {
			if(this.superalien.getxPos()>0) {	
			
				if(this.tiroPlayer.destroySuperalien(this.superalien) == true) {
					if(this.superalien.getDx() != 0) {this.score = this.score + Constantes.VALOR_SUPERALIEN;}
					this.superalien.setDx(0);
					this.superalien.setAlive(false);
					this.superalien.musiqueSoucoupe.stop();
					this.superalien.musiqueDestructionSoucoupe.play();
				}
				this.superalien.designSuperalien(g2);
			}else {this.superalien = null;}
		}
		
		if(this.grupoAliens.getNombreAliens() == 0) {grupoAliens = new GrupoAliens();}
	
		if(this.grupoAliens.positionAlienLePlusBas() > Constantes.Y_POS_PLAYER) {this.player.destructionVaisseau();}			
	}	
}
