package ressources;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.Main;

public class Teclado implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		if(Main.scene.player.isAlive() == true) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){Main.scene.player.setDx(Constantes.DX_PLAYER);}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT){Main.scene.player.setDx(-Constantes.DX_PLAYER);}
			else if(e.getKeyCode() == KeyEvent.VK_SPACE){
				if(Main.scene.tiroPlayer.isPlayerTire() == false) {	
					Audio.playSound("/sons/sonTirVaisseau.wav");
					Main.scene.tiroPlayer.setyPos(Constantes.Y_POS_PLAYER - Constantes.ALTO_TIRO_PLAYER);
					Main.scene.tiroPlayer.setxPos(Main.scene.player.getxPos() + Constantes.LARGO_PLAYER/2 - 1);	
					Main.scene.tiroPlayer.setPlayerTire(true);
				}
		    }
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {Main.scene.player.setDx(0);}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
