package game;

import java.awt.event.KeyEvent;


public class Player extends Sprites implements SharedData {

    private final int START_Y = 400;
    private final int START_X = 270;


    private int width = 20;

    public Player() {

        initPlayer();
    }

    public  void setShipSprite(int shipNum) { 
    	switch (shipNum) {
    	case 1: setImage(GalctacusMini.getImage());
    		break;
    	case 2: setImage(UltronMini.getImage());
    		break;
    	case 3: setImage(GammaMini.getImage());
    		break;
    	}
    }
    
    private void initPlayer() {
        

        setX(START_X);
        setY(START_Y);
    }

    public void act() {
        
        x += dx;
        
        if (x <= 2) {
            x = 2;
        }
        
        if (x >= BOARD_WIDTH -  60) {
            x = BOARD_WIDTH -  60;
        } if (x <= width) { 
        	x = width;
        }
    }

    public void keyPressed(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        
            dx = -3;
        }

        if (key == KeyEvent.VK_RIGHT) {
        
            dx = 3;
        }
    }

    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
        
            dx = 0;
        }
    }
}