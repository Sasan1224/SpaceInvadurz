package entidades;

import java.awt.Color;
import java.awt.Graphics;

import ressources.Audio;
import ressources.Constantes;

public class Home extends Entite {

	/**** VARIABLES ****/

	private final int NBRE_LINEAS = Constantes.ALTO_HOME / Constantes.DIMENSION_BRICK;
	private final int NBRE_COLUMNAS = Constantes.LARGO_HOME / Constantes.DIMENSION_BRICK;

	
	boolean tabHome[][] = new boolean[NBRE_LINEAS][NBRE_COLUMNAS];

	/**** CONSTRUCTEUR ****/

	public Home(int xPos) {
		super.xPos = xPos; 
		super.yPos = Constantes.Y_POS_HOME;

		this.initTabHome();
	}

	/**** METHODES ****/

	
	public void initTabHome() {
		
		for (int linea = 0; linea < NBRE_LINEAS; linea++) {
			for (int columna = 0; columna < NBRE_COLUMNAS; columna++) {
				tabHome[linea][columna] = true;
			}
		}
	
		for (int columna = 0; columna < 6; columna++) {
			for (int linea = 0; linea < 2; linea++) {
				tabHome[linea][columna] = false;
				tabHome[linea][NBRE_COLUMNAS - columna - 1] = false;
			}
		}
		for (int columna = 0; columna < 4; columna++) {
			for (int linea = 2; linea < 4; linea++) {
				tabHome[linea][columna] = false;
				tabHome[linea][NBRE_COLUMNAS - columna - 1] = false;
			}
		}
		for (int columna = 0; columna < 2; columna++) {
			for (int linea = 4; linea < 6; linea++) {
				tabHome[linea][columna] = false;
				tabHome[linea][NBRE_COLUMNAS - columna - 1] = false;
			}
		}
		
		for (int linea = 18; linea < NBRE_LINEAS; linea++) {
			for (int columna = 10; columna < NBRE_COLUMNAS - 10; columna++) {
				tabHome[linea][columna] = false;
			}
		}
		
		for (int columna = 12; columna < NBRE_COLUMNAS - 12; columna++) {
			for (int linea = 16; linea < 18; linea++) {
				tabHome[linea][columna] = false;
				tabHome[linea][NBRE_COLUMNAS - columna - 1] = false;
			}
		}
		for (int columna = 14; columna < NBRE_COLUMNAS - 14; columna++) {
			for (int linea = 14; linea < 16; linea++) {
				tabHome[linea][columna] = false;
				tabHome[linea][NBRE_COLUMNAS - columna - 1] = false;
			}
		}
		for (int columna = 0; columna < 2; columna++) {
			for (int linea = 4; linea < 6; linea++) {
				tabHome[linea][columna] = false;
				tabHome[linea][NBRE_COLUMNAS - columna - 1] = false;
			}
		}
	}

	
	public void designHome(Graphics g2) {
		for (int linea = 0; linea < NBRE_LINEAS; linea++) {
			for (int columna = 0; columna < NBRE_COLUMNAS; columna++) {
				if (tabHome[linea][columna] == true) {
					g2.setColor(Color.GREEN);
				} else {
					g2.setColor(Color.BLACK);
				}
				g2.fillRect(this.xPos + Constantes.DIMENSION_BRICK * columna,
						this.yPos + Constantes.DIMENSION_BRICK * linea, Constantes.DIMENSION_BRICK,
						Constantes.DIMENSION_BRICK);
			}
		}
	}

	public int findColumnaHome(int xMissile) {
		
		int columna = -1;
		columna = (xMissile - this.xPos) / Constantes.DIMENSION_BRICK;
		return columna;
	}

	public int findBrick(int columna) {
		
		int linea = NBRE_LINEAS - 1;
		while (linea >= 0 && tabHome[linea][columna] == false) {
			linea--;
		}
		return linea;
	}

	private void exitBrick(int linea, int columna) {
		
		for (int contador = 0; contador < 6; contador++) {
			if (linea - contador >= 0) {
				tabHome[linea - contador][contador] = false;
				if (columna < NBRE_COLUMNAS - 1) {
					tabHome[linea - contador][columna + 1] = false;
				}
			}
		}
	}

	public void rotoBricks(int xTiro) {
		
		Audio.playSound("/sons/sonCasseBrique.wav");
		int columna = this.findColumnaHome(xTiro);
		this.exitBrick(findBrick(columna), columna);
	}

	public int findBrickHome(int columna) {
		
		int linea = 0;
		if (columna != -1) {
			while (linea < NBRE_LINEAS && tabHome[linea][columna] == false) {
				linea++;
			}
		}
		return linea;
	}

	private void exitBricksHome(int linea, int columna) {
		
		for (int contador = 0; contador < 6; contador++) {
			if (linea + contador < NBRE_LINEAS && columna != -1) {
				tabHome[linea + contador][columna] = false;
				if (columna < NBRE_COLUMNAS - 1) {
					tabHome[linea + contador][columna + 1] = false;
				}
			}
		}
	}

	public void rotoBrickHouse(int xTiro) {
		
		Audio.playSound("/sons/sonCasseBrique.wav");
		int columna = this.findColumnaHome(xTiro);
		this.exitBricksHome(findBrickHome(columna), columna);
	}
}
