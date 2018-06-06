import java.awt.Image;

import javax.swing.ImageIcon;

public class Player {
	private static int x = 0;
	private static int y = 0;
	private static int speed = 7;
	private static boolean dead = false;
	private static Image player = (new ImageIcon("playerSteady.png")).getImage();
	private static Image playerUp = (new ImageIcon("playerUp.gif")).getImage();
	private static Image playerDown = (new ImageIcon("playerDown.gif")).getImage();
	private static Image playerRight = (new ImageIcon("playerRight.gif")).getImage();
	private static Image playerLeft = (new ImageIcon("playerLeft.gif")).getImage();
	private static Image playerSteady = (new ImageIcon("playerSteady.png")).getImage();
	private static Image playerDead = (new ImageIcon("playerDead.gif")).getImage();

	public static boolean isDead() {
		return dead;
	}

	public static void setDead(boolean dead) {
		Player.dead = dead;
	}

	public static boolean meetDoor() {
		boolean state = false;
		boolean west, east, north, south;
		west = x - 700 < 30;
		east = 700 - x < 15;
		north = 500 - y < 15;
		south = y - 500 < 30;
		state = state || (west && south && north && east);
		return state;
	}

	public static boolean meetExplode() {
		boolean state = false;
		boolean west, east, north, south;

		if (Bomb.getExplode1X() > 0 && Bomb.getExplode1Y() > 0) {
			west = x - Bomb.getExplode1X() < 35;
			east = Bomb.getExplode1X() - x < 35;
			north = Bomb.getExplode1Y() - y < 35;
			south = y - Bomb.getExplode1Y() < 35;
			state = state || (west && south && north && east);
		}
		if (Bomb.getExplode2X() >= 0 && Bomb.getExplode2Y() >= 0) {
			west = x - Bomb.getExplode2X() < 35;
			east = Bomb.getExplode2X() - x < 35;
			north = Bomb.getExplode2Y() - y < 35;
			south = y - Bomb.getExplode2Y() < 35;
			state = state || (west && south && north && east);
		}
		if (Bomb.getExplode3X() >= 0 && Bomb.getExplode3Y() >= 0) {
			west = x - Bomb.getExplode3X() < 35;
			east = Bomb.getExplode3X() - x < 35;
			north = Bomb.getExplode3Y() - y < 35;
			south = y - Bomb.getExplode3Y() < 35;
			state = state || (west && south && north && east);
		}
		if (Bomb.getExplode4X() > 0 && Bomb.getExplode4Y() > 0) {
			west = x - Bomb.getExplode4X() < 35;
			east = Bomb.getExplode4X() - x < 35;
			north = Bomb.getExplode4Y() - y < 35;
			south = y - Bomb.getExplode4Y() < 35;
			state = state || (west && south && north && east);
		}
		if (Bomb.getExplode5X() > 0 && Bomb.getExplode5Y() > 0) {
			west = x - Bomb.getExplode5X() < 35;
			east = Bomb.getExplode5X() - x < 35;
			north = Bomb.getExplode5Y() - y < 35;
			south = y - Bomb.getExplode5Y() < 35;
			state = state || (west && south && north && east);
		}
		return state;
	}

	public static boolean meetEnemy(Enemy[] enemy) {
		boolean state = false;
		for (int i = 0; i < enemy.length; i++) {

			if (enemy[i].isDead())
				state = state || false;

			boolean west, east, north, south;
			west = x - enemy[i].getX() < 35;
			east = enemy[i].getX() - x < 35;
			north = enemy[i].getY() - y < 35;
			south = y - enemy[i].getY() < 35;
			state = state || (west && south && north && east);
		}
		return state;
	}

	public static boolean outOfPanel() {
		return x < 0 || y < 0 || x > 715 || y > 510;
	}

	public static boolean meetWall() {
		boolean state = false;
		boolean west, east, north, south;
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 5; j++) {
				west = x - 50 * (2 * i + 1) < 50;
				east = 50 * (2 * i + 1) - x < 35;
				north = 50 * (2 * j + 1) - y < 35;
				south = y - 50 * (2 * j + 1) < 50;
				state = state || (west && south && north && east);
			}
		return state;
	}

	public static boolean meetBomb() {
		boolean state = false;
		boolean west, east, north, south;
		west = x - Bomb.getX() < 35;
		east = Bomb.getX() - x < 35;
		north = Bomb.getY() - y < 35;
		south = y - Bomb.getY() < 35;
		state = state || (west && south && north && east);
		return state;
	}

	public static void moveUp() {
		y -= speed;
	}

	public static void moveDown() {
		y += speed;
	}

	public static void moveRight() {
		x += speed;
	}

	public static void moveLeft() {
		x -= speed;
	}

	public static int getSpeed() {
		return speed;
	}

	public static void setSpeed(int speed) {
		Player.speed = speed;
	}

	public static Image getPlayer() {
		return player;
	}

	public static void setPlayer(int ch) {
		switch (ch) {
		case 1:
			player = playerUp;
			break;
		case 2:
			player = playerDown;
			break;
		case 3:
			player = playerRight;
			break;
		case 4:
			player = playerLeft;
			break;
		case 5:
			player = playerSteady;
			break;
		case 6:
			player = playerDead;
			break;
		}
	}

	public static int getX() {
		return x;
	}

	public static void setX(int x) {
		Player.x = x;
	}

	public static int getY() {
		return y;
	}

	public static void setY(int y) {
		Player.y = y;
	}

}
