package ressources;

public abstract class Constantes {
	
	/************************************* FENETRE *************************************/	
	// Dimensions de la fenêtre
	public static final int LARGO_SCREEN = 600;
	public static final int ALTO_SCREEN = 600;
	public static final int MARCO_SCREEN = 50;
	
	/************************************* VAISSEAU *************************************/	
	// Dimensions du vaisseau
	public static final int LARGO_PLAYER = 39;
	public static final int ALTO_PLAYER = 24;
	
	// Position initiale du vaisseau
	public final static int X_POS_INIT_PLAYER = (LARGO_SCREEN - LARGO_PLAYER)/ 2;
	public final static int Y_POS_PLAYER = 490;	
	
	// Unité de déplacement du vaisseau
	public final static int DX_PLAYER = 1;
	
	// Limite de déplacement du vaisseau
	public final static int LIMITE_IZQ_PLAYER = 60;
	public final static int LIMITE_DER_PLAYER = 500;	
	
	/************************************* ALIEN ***************************************/	
	// Dimensions de l'alien
	public static final int LARGO_ALIEN = 33;
	public static final int ALTO_ALIEN= 25;	
	
	// Paramètres de position des aliens
	public final static int ALT_INIT_ALIEN = 120;
	public final static int X_POS_INIT_ALIEN = 29 + MARCO_SCREEN;
	public final static int DIF_LINEAS_ALIEN = 40;
	public final static int DIF_COLUMNAS_ALIEN = 10;
	
	// Unité de déplacement de l'alien
	public final static int DX_ALIEN = 2;
	public final static int DY_ALIEN = 20;
	public final static int ALIVE_ALIEN = 1;	
	
	// Nombre total d'aliens
	public final static int NOMBRE_ALIENS = 50;
	
	/************************************ TIR VAISSEAU **********************************/	
	// Dimensions du tir
	public static final int LARGO_TIRO_PLAYER = 3;
	public static final int ALTO_TIRO_PLAYER = 13;	
	
	// Unité de déplacement du tir
	public final static int DY_TIRO_PLAYER = 2;
	
	/************************************* CHATEAU *************************************/
	// Dimensions de la brique
	public static final int DIMENSION_BRICK = 2;
	
	// Dimensions du château (multiples des dimensions de la brique)
	public static final int LARGO_HOME = 72;
	public static final int ALTO_HOME = 54;
		
	// Paramètres de position des châteaux
	public final static int Y_POS_HOME = 400;
	public final static int X_POS_INIT_HOME = 39;
	public final static int DIF_HOME = 42;
	
	/************************************ TIR ALIEN ************************************/
	// Dimensions du tir
	public static final int LARGO_TIRO_ALIEN = 5;
	public static final int ALTO_TIRO_ALIEN = 15;	
	
	// Unité de déplacement du tir
	public final static int DY_TIRO_ALIEN = 3;

	/************************************* SOUCOUPE *************************************/	
	// Dimensions de la soucoupe
	public static final int LARGO_SUPERALIEN = 42;
	public static final int ALTO_SUPERALIEN = 22;

	// Position initiale de la soucoupe
	public final static int X_POS_INIT_SUPERALIEN = LARGO_SCREEN;
	public final static int Y_POS_SUPERALIEN = 50;	

	// Unité de déplacement de la soucoupe
	public final static int DX_SUPERALIEN = 1;
	
	/************************************* POINTS *************************************/	
	// Points attribués pour la destruction des aliens
	public static final int VALOR_ALIEN_UP = 50;
	public static final int VALOR_ALIEN_MIDDLE = 40;
	public static final int VALOR_ALIEN_DOWN = 20;
	public static final int VALOR_SUPERALIEN = 100;
}


