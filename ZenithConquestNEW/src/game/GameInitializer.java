package game;


import javax.swing.JFrame;
import javax.swing.JLabel;


public class GameInitializer extends JFrame implements SharedData {
	int shipNum;
	String difficulty, userName;
	
	    public GameInitializer(int shipNum, String difficulty,String userName) {
	    	this.shipNum = shipNum;
	    	this.difficulty = difficulty;
	    	this.userName = userName;
	    	
	    	setTitle("Zenith Conquest");
	        add(new GamePanel(shipNum, difficulty, userName));
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setSize(BOARD_WIDTH, BOARD_HEIGHT);
	        setLocationRelativeTo(null);
	        setResizable(false);
	        setIconImage(TASKBAR_ICON);
	    }

	}


