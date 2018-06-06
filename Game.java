import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements ActionListener {

	private Image wall = (new ImageIcon("wall.png")).getImage();
	private Image door = (new ImageIcon("door.png")).getImage();
	private Enemy[] enemy = new Enemy[2];
	private Timer time;
	private long periodBomb = 0, periodBang = 0, periodEnemyDead = 0, periodDead = 0;

	public Game() {
		//System.out.println((int)(Math.ceil((1 + Math.random() * 1))));
		for (int i = 0; i < enemy.length; i++)
			enemy[i] = new Enemy();
		addKeyListener(new XListener());
		add(new GamePanel());
		time = new Timer(25, this);
	}

	public static void main(String args[]) {
		Game game = new Game();
		game.setTitle("Bomberman");
		game.setSize(755, 580);
		game.setLocationRelativeTo(null);
		game.setDefaultCloseOperation(EXIT_ON_CLOSE);
		game.setResizable(false);
		game.setVisible(true);
		game.time.start();
	}

	class GamePanel extends JPanel {

		public GamePanel() {
			setBackground(new Color(255, 255, 102));
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.setFont(new Font("Helvetica", Font.BOLD, 80));
			if (Character.meetDoor() && Enemy.allEnemiesDead(enemy)) {
				time.stop();
				g.setColor(Color.BLACK);
				g.drawString("Well Done!!", getWidth() / 2 - 100,
					getHeight() / 2 - 20);
				return;
			}
			if (Character.isDead()) {
				if (System.currentTimeMillis() - periodDead > 1000) {
					time.stop();
					g.setColor(Color.red);
					g.drawString("Game Over!!", getWidth() / 2 - 100,
						getHeight() / 2 - 20);
					return;
				}
				Character.setCharacter(6);
			}
			g.drawImage(door, 700, 500, 50, 50, null);
			
			if (Bomb.isBang() && (System.currentTimeMillis() - periodBang < 1000))
				Bomb.BangEvent(g);

			else if (Bomb.isBombSet()) {
				g.drawImage(Bomb.getBomb(), Bomb.getX(), Bomb.getY(), 35, 35, null);
				if (System.currentTimeMillis() - periodBomb > 3000) {
					periodBang = System.currentTimeMillis();
					Bomb.setBang(true);
					Bomb.off();
				}
			}

			else
				Bomb.setBang(false);

			g.drawImage(Character.getCharacter(), Character.getX(), Character.getY(), 35, 35, null);
			for (int i = 0; i < enemy.length; i++) {
				if ((enemy[i].isDead() && System.currentTimeMillis() - periodEnemyDead > 1200)) {
					enemy[i].setX(1000);
					enemy[i].setY(1000);
				}
				g.drawImage(enemy[i].getEnemy(), enemy[i].getX(), enemy[i].getY(), 35, 35, null);
			}
			for (int i = 0; i < 7; i++)
				for (int j = 0; j < 5; j++)
					g.drawImage(wall, 50 * (2 * i + 1), 50 * (2 * j + 1), 50, 50, null);
		}
	}

	class XListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			if (Character.isDead() || (Character.meetDoor() && Enemy.allEnemiesDead(enemy)))
				return;
			if (Character.meetBomb() && Bomb.isBombSet()) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					Character.moveUp();
					Character.setCharacter(1);
					if (Character.meetWall() || Character.outOfPanel())
						Character.moveDown();
					break;
				case KeyEvent.VK_DOWN:
					Character.setCharacter(2);
					Character.moveDown();
					if (Character.meetWall() || Character.outOfPanel())
						Character.moveUp();
					break;
				case KeyEvent.VK_RIGHT:
					Character.setCharacter(3);
					Character.moveRight();
					if (Character.meetWall() || Character.outOfPanel())
						Character.moveLeft();
					break;
				case KeyEvent.VK_LEFT:
					Character.setCharacter(4);
					Character.moveLeft();
					if (Character.meetWall() || Character.outOfPanel())
						Character.moveRight();
					break;
				}
			} else
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					Character.moveUp();
					Character.setCharacter(1);
					if (Character.meetWall() || Character.outOfPanel()
							|| Character.meetBomb() && Bomb.isBombSet())
						Character.moveDown();
					break;
				case KeyEvent.VK_DOWN:
					Character.setCharacter(2);
					Character.moveDown();
					if (Character.meetWall() || Character.outOfPanel()
							|| Character.meetBomb() && Bomb.isBombSet())
						Character.moveUp();
					break;
				case KeyEvent.VK_RIGHT:
					Character.setCharacter(3);
					Character.moveRight();
					if (Character.meetWall() || Character.outOfPanel()
							|| Character.meetBomb() && Bomb.isBombSet())
						Character.moveLeft();
					break;
				case KeyEvent.VK_LEFT:
					Character.setCharacter(4);
					Character.moveLeft();
					if (Character.meetWall() || Character.outOfPanel()
							|| Character.meetBomb() && Bomb.isBombSet())
						Character.moveRight();
					break;
				case KeyEvent.VK_SPACE:
					if (!Bomb.isBombSet() && !Bomb.isBang()) {
						Bomb.setX(Character.getX());
						Bomb.setY(Character.getY());
						Bomb.on();
						periodBomb = System.currentTimeMillis();
					}
					break;
				}
			repaint();
		}

		public void keyReleased(KeyEvent e) {
			Character.setCharacter(5);
			repaint();
		}

		public void keyTyped(KeyEvent e) {
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < enemy.length; i++) {
			enemy[i].move(i % 2 == 1);
			if (enemy[i].meetBang() && Bomb.isBang()) {
				enemy[i].setEnemy(enemy[i].getEnemyDead());
				enemy[i].setDead(true);
				periodEnemyDead = System.currentTimeMillis();
			}
		}

		if (Character.meetEnemy(enemy) || (Character.meetBang() && Bomb.isBang())) {
			Character.setDead(true);
			periodDead = System.currentTimeMillis();
		}
		repaint();
	}

}
