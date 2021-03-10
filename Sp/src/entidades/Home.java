package entidades;

import java.awt.Color;
import java.awt.Graphics;

import ressources.Audio;
import ressources.Constantes;

public class Home extends Entite {

	/**** VARIABLES ****/

	private final int NBRE_LINEAS = Constantes.ALTO_HOME / Constantes.DIMENSION_BRICK;
	private final int NBRE_COLUMNAS = Constantes.LARGO_HOME / Constantes.DIMENSION_BRICK;

	// tableau contenant les briques du ch�teau (la case contient true pour une
	// brique et false pour vide)
	boolean tabHome[][] = new boolean[NBRE_LINEAS][NBRE_COLUMNAS];

	/**** CONSTRUCTEUR ****/

	public Home(int xPos) {
		super.xPos = xPos; // Abscisse du point le plus � gauche du ch�teau
		super.yPos = Constantes.Y_POS_HOME; // Ordonn�e du sommet du ch�teau

		this.initTabHome();
	}

	/**** METHODES ****/

	// Cr�ation du tableau initial associ� au ch�teau sans d�g�t
	public void initTabHome() {
		// On remplit toutes les cases du tableau avec true
		for (int linea = 0; linea < NBRE_LINEAS; linea++) {
			for (int columna = 0; columna < NBRE_COLUMNAS; columna++) {
				tabHome[linea][columna] = true;
			}
		}
		// On remplit toutes les cases sans brique du tableau avec false
		// Biseautage du haut du ch�teau
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
		// Entr�e du ch�teau
		for (int linea = 18; linea < NBRE_LINEAS; linea++) {
			for (int columna = 10; columna < NBRE_COLUMNAS - 10; columna++) {
				tabHome[linea][columna] = false;
			}
		}
		// Biseautage entr�e du ch�teau
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

	// Dessin du ch�teau
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
		// Trouve la colonne du tableau associ� au ch�teau touch� par le tir
		int columna = -1;
		columna = (xMissile - this.xPos) / Constantes.DIMENSION_BRICK;
		return columna;
	}

	public int findBrick(int columna) {
		// Trouve la premi�re brique en paratnt du bas de la colonne du tableau associ�
		// au ch�teau ou renvoie -1
		int linea = NBRE_LINEAS - 1;
		while (linea >= 0 && tabHome[linea][columna] == false) {
			linea--;
		}
		return linea;
	}

	private void exitBrick(int linea, int columna) {
		// Elimination des 6 premi�res briques de la colonne en partant du bas si elles
		// existent
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
		// R�capitule les 3 m�thodes qui pr�c�dent
		Audio.playSound("/sons/sonCasseBrique.wav");
		int columna = this.findColumnaHome(xTiro);
		this.exitBrick(findBrick(columna), columna);
	}

	public int findBrickHome(int columna) {
		// Trouve la premi�re brique en partant du haut de la colonne du tableau
		// associ� au ch�teau ou renvoie -1
		int linea = 0;
		if (columna != -1) {
			while (linea < NBRE_LINEAS && tabHome[linea][columna] == false) {
				linea++;
			}
		}
		return linea;
	}

	private void exitBricksHome(int linea, int columna) {
		// Elimination des 6 premi�res briques de la colonne en partant du haut si elles
		// existent
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
		// R�capitule les 3 m�thodes qui pr�c�dent
		Audio.playSound("/sons/sonCasseBrique.wav");
		int columna = this.findColumnaHome(xTiro);
		this.exitBricksHome(findBrickHome(columna), columna);
	}
}
