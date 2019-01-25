package game;

import javax.swing.ImageIcon;

public class Alien extends Sprites implements SharedData {

	private Bomb bomb;

	public Alien(int x, int y) {

		initAlien(x, y);
	}

	private void initAlien(int x, int y) {

		this.x = x;
		this.y = y;

		bomb = new Bomb(x, y);
		setImage(alienSprite.getImage());
	}

	public void act(int direction) {

		this.x += direction;
	}

	public Bomb getBomb() {

		return bomb;
	}

	public class Bomb extends Sprites implements SharedData {

		private boolean destroyed;

		public Bomb(int x, int y) {

			initBomb(x, y);
		}

		private void initBomb(int x, int y) {

			setDestroyed(true);
			this.x = x;
			this.y = y;

			setImage(alienMissle.getImage());

		}

		public void setDestroyed(boolean destroyed) {

			this.destroyed = destroyed;
		}

		public boolean isDestroyed() {

			return destroyed;
		}
	}
}