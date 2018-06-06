import java.awt.Image;

import javax.swing.ImageIcon;

public class Enemy {
	private int x = 100 * (int) (1 + Math.random() * 7) + 8;
	private int y = 100 * (int) (1 + Math.random() * 5) + 8;
	private Image enemyRight = (new ImageIcon("enemyRight.gif")).getImage();
	private Image enemyLeft = (new ImageIcon("enemyLeft.gif")).getImage();
	private Image enemyDead = (new ImageIcon("enemyDead(1.5).gif")).getImage();
	private Image enemy = enemyRight;
	private int direction = (int) (1 + Math.random() * 2);
	private int speed = 3;
	private boolean dead = false;

	public void move(boolean type) {
		if (dead)
			return;
		if (meetBomb() && Bomb.isBombSet()) {
			if (type)
				switch (direction) {
				case 1:
					x += speed;
					setEnemy(enemyRight);
					break;
				case 2:
					x -= speed;
					setEnemy(enemyLeft);
					break;
				}
			else
				switch (direction) {
				case 1:
					y += speed;
					setEnemy(enemyRight);
					break;
				case 2:
					y -= speed;
					setEnemy(enemyLeft);
					break;
				}
		} else {
			if (type)
				switch (direction) {
				case 1:
					x += speed;
					setEnemy(enemyRight);
					if (outOfPanel() || meetBomb() && Bomb.isBombSet())
						direction = 2;
					break;
				case 2:
					x -= speed;
					setEnemy(enemyLeft);
					if (outOfPanel() || meetBomb() && Bomb.isBombSet())
						direction = 1;
					break;
				}
			else
				switch (direction) {
				case 1:
					y += speed;
					setEnemy(enemyRight);
					if (outOfPanel() || meetBomb() && Bomb.isBombSet())
						direction = 2;
					break;
				case 2:
					y -= speed;
					setEnemy(enemyLeft);
					if (outOfPanel() || meetBomb() && Bomb.isBombSet())
						direction = 1;
					break;
				}
		}
	}

	public static boolean allEnemiesDead(Enemy[] enemy) {
		boolean state = true;
		for (int i = 0; i < enemy.length; i++)
			state = state && enemy[i].isDead();
		return state;
	}

	public boolean meetExplode() {
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

	public boolean meetBomb() {
		boolean state = false;
		boolean west, east, north, south;
		west = x - Bomb.getX() < 35;
		east = Bomb.getX() - x < 35;
		north = Bomb.getY() - y < 35;
		south = y - Bomb.getY() < 35;
		state = state || (west && south && north && east);

		return state;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public boolean outOfPanel() {
		return x < 0 || y < 0 || x > 715 || y > 510;
	}

	public Image getEnemyRight() {
		return enemyRight;
	}

	public void setEnemyRight(Image enemyRight) {
		this.enemyRight = enemyRight;
	}

	public Image getEnemyLeft() {
		return enemyLeft;
	}

	public void setEnemyLeft(Image enemyLeft) {
		this.enemyLeft = enemyLeft;
	}

	public Image getEnemyDead() {
		return enemyDead;
	}

	public void setEnemyDead(Image enemyDead) {
		this.enemyDead = enemyDead;
	}

	public Image getEnemy() {
		return enemy;
	}

	public void setEnemy(Image enemy) {
		this.enemy = enemy;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
