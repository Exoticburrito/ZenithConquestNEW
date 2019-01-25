package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable, SharedData{

	private Dimension d;
	private ArrayList<Alien> aliens;
	private Player player;
	private Shot shot;

	private final int ALIEN_INIT_X = 150;
	private final int ALIEN_INIT_Y = 5;
	private int direction = -1;
	private int kills = 0;

	private boolean ingame = true;
	private int shipNum;
	
	private Thread animator;
	private int GO_DOWN;
	private String difficulty, userName;
	Music mu = new Music();
	
	public GamePanel(int shipNum, String difficulty, String userName) {
		this.shipNum = shipNum;
		this.difficulty = difficulty;
		this.userName = userName;
		if(difficulty == "easy") { 
			setGO_DOWN(15);
		} else if (difficulty == "hard") { 
			setGO_DOWN(30);
		}
		initBoard();
	}
	
	public void setGO_DOWN(int gD) { 
		GO_DOWN = gD;
	}
	
	private void initBoard() {

		addKeyListener(new Handler());
		setFocusable(true);
		d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
		

		gameInit();
		setDoubleBuffered(true);
	}

	@Override
	public void addNotify() {

		super.addNotify();
		gameInit();
	}
	
	// initializes alien objects in their aliens array, spawns 24 using a for loop (almost like a 2D array)
	public void gameInit() {

		aliens = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {

				Alien alien = new Alien(ALIEN_INIT_X + 45 * j, ALIEN_INIT_Y + 45 * i);
				aliens.add(alien);
			}
		}

		player = new Player();
		player.setShipSprite(shipNum);
		shot = new Shot();

		if (animator == null || !ingame) {

			animator = new Thread(this);
			animator.start();
		}
	}

	public void drawAliens(Graphics g) {

		Iterator it = aliens.iterator();

		for (Alien alien : aliens) {

			if (alien.isVisible()) {

				g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
			}

			if (alien.isDying()) {

				alien.die();
			}
		}
	}

	public void drawPlayer(Graphics g) {

		if (player.isVisible()) {

			g.drawImage(player.getImage(), player.getX(), player.getY(), this);
		}

		if (player.isDying()) {

			player.die();
			ingame = false;
		}
	}

	public void drawShot(Graphics g) {

		if (shot.isVisible()) {

			g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
		}
	}

	public void drawBombing(Graphics g) {

		for (Alien a : aliens) {

			Alien.Bomb b = a.getBomb();

			if (!b.isDestroyed()) {

				g.drawImage(b.getImage(), b.getX(), b.getY(), this);
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (difficulty == "easy") { 
			g.drawImage(infoBack.getImage(), 0, 0, null);
		} else if (difficulty == "hard") { 
			g.drawImage(infoBackHARD.getImage(), 0, 0, null);
		}

		if (ingame) {

			g.drawLine(0, GROUND, BOARD_WIDTH, GROUND);
			drawAliens(g);
			drawPlayer(g);
			drawShot(g);
			drawBombing(g);
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	// ends game, produces screen with message of LOSS
	public void gameOver() {
		
		Graphics g = this.getGraphics();
		g.setColor(new Color(0,25,51));
		g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
		g.setFont(VCR_OSD);
		g.setColor(Color.white);
		g.drawString("GAME OVER! Zenith was invaded by", 50, 150);
		g.drawString("the Conquestrians! Captain " + userName + ",", 50,200);
		g.drawString("You have failed Zenith...", 50, 250);
		g.drawImage(sadBoye, 200,300, null);
		LaunchScreen.mu.stopMusic();						// add stop music to stop 0 mile
		mu.play("music/death.wav");
		ingame = false;
	}
	/// ends game, produces screen with message of WIN!
	public void gameWin() { 
		Graphics g = this.getGraphics();
		g.setColor(new Color(255, 153,204));
		g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
		g.setFont(VCR_OSD);
		g.setColor(Color.white);
		g.drawString("Captain " + userName + "!!!!", 50, 150);
		g.drawString("You have saved Zenith from the " + userName + ",", 50,200);
		g.drawString("Conquestrians! ", 50, 250);
		g.drawString("Thank you for playing!" , 50, 400);
		ingame = false;
	}
	
	// animates the game, originally checking of player has reached 24 alien kills
	// then spawns player, shot, aliens
	public void animationCycle() {

		if (kills == 24) {
			gameWin();
			ingame = false;
		}

		// player
		player.act();

		// shot
		if (shot.isVisible()) {

			int shotX = shot.getX();
			int shotY = shot.getY();

			for (Alien alien : aliens) {

				int alienX = alien.getX();
				int alienY = alien.getY();

				if (alien.isVisible() && shot.isVisible()) {
					if (shotX >= (alienX) && shotX <= (alienX + ALIEN_WIDTH) && shotY >= (alienY)
							&& shotY <= (alienY + ALIEN_HEIGHT)) {
						alien.setDying(true);
						kills++;
						shot.die();
					}
				}
			}

			int y = shot.getY();
			y -= 4;

			if (y < 0) {
				shot.die();
			} else {
				shot.setY(y);
			}
		}

		// the following moves aliens

		for (Alien alien : aliens) {

			int x = alien.getX();

			if (x >= BOARD_WIDTH - BORDER_RIGHT && direction != -1) {

				direction = -1;
				Iterator i1 = aliens.iterator();

				while (i1.hasNext()) {

					Alien a2 = (Alien) i1.next();
					a2.setY(a2.getY() + GO_DOWN);
				}
			}

			if (x <= BORDER_LEFT && direction != 1) {

				direction = 1;

				Iterator i2 = aliens.iterator();

				while (i2.hasNext()) {

					Alien a = (Alien) i2.next();
					a.setY(a.getY() + GO_DOWN);
				}
			}
		}

		Iterator it = aliens.iterator();

		while (it.hasNext()) {

			Alien alien = (Alien) it.next();

			if (alien.isVisible()) {

				int y = alien.getY();

				if (y > GROUND - ALIEN_HEIGHT) {
					gameOver();
				}

				alien.act(direction);
			}
		}

		// spawns alien missles randomly based off of psuedorandom generation
		// if randomly generated number is 5, the alien will shoot. this is a 1/3 chance of success
		Random generator = new Random();

		for (Alien alien : aliens) {

			int shot = generator.nextInt(15);
			Alien.Bomb b = alien.getBomb();

			if (shot == CHANCE && alien.isVisible() && b.isDestroyed()) {

				b.setDestroyed(false);
				b.setX(alien.getX());
				b.setY(alien.getY());
			}

			int bombX = b.getX();
			int bombY = b.getY();
			int playerX = player.getX();
			int playerY = player.getY();

			if (player.isVisible() && !b.isDestroyed()) {

				if (bombX >= (playerX) && bombX <= (playerX + PLAYER_WIDTH) && bombY >= (playerY)
						&& bombY <= (playerY + PLAYER_HEIGHT)) {
					player.setDying(true);
					b.setDestroyed(true);
				}
			}

			if (!b.isDestroyed()) {

				b.setY(b.getY() + 1);

				if (b.getY() >= GROUND - BOMB_HEIGHT) {
					b.setDestroyed(true);
				}
			}
		}
	}

	// runnable thread that controls the game
	@Override
	public void run() {

		long beforeTime, timeDiff, sleep;

		beforeTime = System.currentTimeMillis();

		while (ingame) {

			repaint();
			animationCycle();

			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - timeDiff;

			if (sleep < 0) {
				sleep = 2;
			}

			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}

			beforeTime = System.currentTimeMillis();
		}

		gameOver();
	}
	// handles key presses 
	private class Handler extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {

			player.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {

			player.keyPressed(e);				

			int x = player.getX();					// checks fo current player position to spawn the shots
			int y = player.getY();

			int key = e.getKeyCode();

			if (key == KeyEvent.VK_SPACE) {

				if (ingame) {
					if (!shot.isVisible()) {
						shot = new Shot(x, y);				// checks for space bar shot, if pressed, will shoot
					}
				}
			}
		}
	}
}
