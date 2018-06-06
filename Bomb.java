import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Bomb {
	private static int x = -300;
	private static int y = -300;
	private static boolean explode = false;
	private static Image bomb = (new ImageIcon("bomb.gif")).getImage();
	private static Image explode1 = (new ImageIcon("explode1.gif")).getImage();
	private static Image explode2 = (new ImageIcon("explode2.gif")).getImage();
	private static Image explode3 = (new ImageIcon("explode3.gif")).getImage();
	private static Image explode4 = (new ImageIcon("explode4.gif")).getImage();
	private static Image explode5 = (new ImageIcon("explode5.gif")).getImage();
	private static boolean bombSet = false;
	private static int explode1X, explode2X, explode3X, explode4X, explode5X, explode1Y, explode2Y, explode3Y, explode4Y, explode5Y;

	public static boolean isExplode() {
		return explode;
	}

	public static void setExplode(boolean explode) {
		Bomb.explode = explode;
	}

	public static boolean isBombSet() {
		return bombSet;
	}

	public static void setBombSet(boolean bombSet) {
		Bomb.bombSet = bombSet;
	}

	public static void on() {
		bombSet = true;
	}

	public static void off() {
		bombSet = false;
	}

	public static int getX() {
		return x;
	}

	public static void ExplodeEvent(Graphics g) {
		g.drawImage(explode1, explode1X, explode1Y, 35, 35, null);
		g.drawImage(explode2, explode2X, explode2Y, 58, 35, null);
		g.drawImage(explode3, explode3X, explode3Y, 35, 58, null);
		g.drawImage(explode4, explode4X, explode4Y, 58, 35, null);
		g.drawImage(explode5, explode5X, explode5Y, 35, 58, null);
	}

	public static void setX(int x) {

		if (x % 50 > 34)
			x += 25;
		while (x % 50 < 8)
			x++;
		while (x % 50 > 8)
			x--;
		Bomb.x = x;

		explode1X = Bomb.x;
		explode2X = Bomb.x - 58;
		explode3X = Bomb.x;
		explode4X = Bomb.x + 35;
		explode5X = Bomb.x;

	}

	public static int getY() {
		return y;
	}

	public static void setY(int y) {

		if (y % 50 > 34)
			y += 25;
		while (y % 50 < 8)
			y++;
		while (y % 50 > 8)
			y--;
		Bomb.y = y;

		explode1Y = Bomb.y;
		explode2Y = Bomb.y;
		explode3Y = Bomb.y - 58;
		explode4Y = Bomb.y;
		explode5Y = Bomb.y + 35;
	}

	public static Image getBomb() {
		return bomb;
	}

	public static void setBomb(Image bomb) {
		Bomb.bomb = bomb;
	}

	public static Image getExplode1() {
		return explode1;
	}

	public static void setExplode1(Image explode1) {
		Bomb.explode1 = explode1;
	}

	public static Image getExplode2() {
		return explode2;
	}

	public static void setExplode2(Image explode2) {
		Bomb.explode2 = explode2;
	}

	public static Image getExplode3() {
		return explode3;
	}

	public static void setExplode3(Image explode3) {
		Bomb.explode3 = explode3;
	}

	public static Image getExplode4() {
		return explode4;
	}

	public static void setExplode4(Image explode4) {
		Bomb.explode4 = explode4;
	}

	public static Image getExplode5() {
		return explode5;
	}

	public static void setExplode5(Image explode5) {
		Bomb.explode5 = explode5;
	}

	public static int getExplode1X() {
		return explode1X;
	}

	public static int getExplode2X() {
		return explode2X;
	}

	public static int getExplode3X() {
		return explode3X;
	}

	public static int getExplode4X() {
		return explode4X;
	}

	public static int getExplode5X() {
		return explode5X;
	}

	public static int getExplode1Y() {
		return explode1Y;
	}

	public static int getExplode2Y() {
		return explode2Y;
	}

	public static int getExplode3Y() {
		return explode3Y;
	}

	public static int getExplode4Y() {
		return explode4Y;
	}

	public static int getExplode5Y() {
		return explode5Y;
	}

}
