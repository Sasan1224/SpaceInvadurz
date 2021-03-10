package ressources;

public abstract class Constantes {
	
	
	
	public static final int LARGO_SCREEN = 600;
	public static final int ALTO_SCREEN = 600;
	public static final int MARCO_SCREEN = 50;
	

	public static final int LARGO_PLAYER = 39;
	public static final int ALTO_PLAYER = 24;
	
	
	public final static int X_POS_INIT_PLAYER = (LARGO_SCREEN - LARGO_PLAYER)/ 2;
	public final static int Y_POS_PLAYER = 490;	
	

	public final static int DX_PLAYER = 1;
	
	
	public final static int LIMITE_IZQ_PLAYER = 60;
	public final static int LIMITE_DER_PLAYER = 500;	
	
	public static final int LARGO_ALIEN = 33;
	public static final int ALTO_ALIEN= 25;	
	
	public final static int ALT_INIT_ALIEN = 120;
	public final static int X_POS_INIT_ALIEN = 29 + MARCO_SCREEN;
	public final static int DIF_LINEAS_ALIEN = 40;
	public final static int DIF_COLUMNAS_ALIEN = 10;
	
	
	public final static int DX_ALIEN = 2;
	public final static int DY_ALIEN = 20;
	public final static int ALIVE_ALIEN = 1;	
	

	public final static int NOMBRE_ALIENS = 50;
	

	public static final int LARGO_TIRO_PLAYER = 3;
	public static final int ALTO_TIRO_PLAYER = 13;	
	
	
	public final static int DY_TIRO_PLAYER = 2;
	

	public static final int DIMENSION_BRICK = 2;
	
	
	public static final int LARGO_HOME = 72;
	public static final int ALTO_HOME = 54;
		
	
	public final static int Y_POS_HOME = 400;
	public final static int X_POS_INIT_HOME = 39;
	public final static int DIF_HOME = 42;
	
	
	public static final int LARGO_TIRO_ALIEN = 5;
	public static final int ALTO_TIRO_ALIEN = 15;	
	
	
	public final static int DY_TIRO_ALIEN = 3;

	public static final int LARGO_SUPERALIEN = 42;
	public static final int ALTO_SUPERALIEN = 22;

	
	public final static int X_POS_INIT_SUPERALIEN = LARGO_SCREEN;
	public final static int Y_POS_SUPERALIEN = 50;	

	
	public final static int DX_SUPERALIEN = 1;
	
	
	public static final int VALOR_ALIEN_UP = 50;
	public static final int VALOR_ALIEN_MIDDLE = 40;
	public static final int VALOR_ALIEN_DOWN = 20;
	public static final int VALOR_SUPERALIEN = 100;
}


