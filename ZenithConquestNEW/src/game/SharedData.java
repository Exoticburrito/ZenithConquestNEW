package game;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;

public interface SharedData {
	public static final int BOARD_WIDTH = 550;
	public static final int BOARD_HEIGHT = 550;
	public static final int GROUND = 520;
	public static final int BOMB_HEIGHT = 5;
	public static final int ALIEN_HEIGHT = 40;
	public static final int ALIEN_WIDTH = 40;
	public static final int BORDER_RIGHT = 2;
	public static final int BORDER_LEFT = 2;
	public static int GO_DOWN = 15;
	public static final int CHANCE = 5;
	public static final int DELAY = 17;
	public static final int PLAYER_WIDTH = 69;
	public static final int PLAYER_HEIGHT = 60;
	
	
	// launch screen images
		Image TASKBAR_ICON = new ImageIcon("images/TASKBARICON.png").getImage();		
		
		ImageIcon titleScr = new ImageIcon("images/spacegif.gif");
		ImageIcon easyImg = new ImageIcon("images/easyButton.png");
		ImageIcon hardImg = new ImageIcon("images/hardButton.png");

		// info prompt images & fonts
		public ImageIcon infoBack = new ImageIcon("images/infoScreenBack.png");
		public ImageIcon infoBackHARD = new ImageIcon("images/infoScreenBackHARD.jpg");				// remember to fix images at school, add " " to file path
		
		public ImageIcon namePrompt = new ImageIcon("images/namePrompt.png");
		public ImageIcon shipPrompt = new ImageIcon("images/shipPrompt.png");
		
		public ImageIcon shipGalactacus = new ImageIcon("images/Galactacus.png");
		public ImageIcon shipUltron = new ImageIcon("images/OmegaUltron.png");
		public ImageIcon shipGamma = new ImageIcon("images/GammaOdyssey.png");
		
		Font CALIBRI = new Font("Calibri", Font.ITALIC, 16);
		Font VCR_OSD = new Font("VCR OSD MONO", Font.PLAIN, 24);
		
		// for game itself 
		public Image sadBoye = new ImageIcon("images/sadEnd.png").getImage();
		
		
		
		// for aliens 
		public ImageIcon alienSprite = new ImageIcon("images/alien.png");
		public ImageIcon alienMissle = new ImageIcon("images/missle.png");
		
		// for ships
		
		public ImageIcon GalctacusMini = new ImageIcon("images/GalactacusMINI.png");
		public ImageIcon UltronMini = new ImageIcon("images/UltronMINI.png");
		public ImageIcon GammaMini = new ImageIcon("images/GammaMINI.png");
		
		public ImageIcon playerShot = new ImageIcon("images/shot.png");
}
