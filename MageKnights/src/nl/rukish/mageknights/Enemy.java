package nl.rukish.mageknights;

import java.util.Random;

import android.graphics.Rect;

public class Enemy extends Player {

	private int health, movingSpeed, movingFrames, spawnTime;
	private boolean visible;
	Random rand;

	public Enemy(int xPos, int yPos, int width, int height) {
		super(xPos, yPos, width, height);

		health = 3;
		movingSpeed = 1;
		rand = new Random();
		movingFrames = 0;
		spawnTime = 200;
		visible = true;
	}

	public void updateEnemy() {
		//KO SENSOR
		if (health <= 0){
			visible = false;
			if (spawnTime > 0){
				spawnTime--;
			}
			else {
				health = 3;
				spawnTime = 60;
				movingSpeed++;
				moveTo(400, 200);
				visible = true;
			}
			return;
		}
		// Artificial intelligence
		Player player1 = GameView.player1;
		Rect player1rect = player1.getRect();
		Rect enemyrect = getRect();

		if (rand.nextInt(100) <= 3) {
			if (player1rect.centerX() < enemyrect.centerX())
				moveLeft();
			else if (player1rect.centerX() > enemyrect.centerX())
				moveRight();
			else if (player1rect.centerY() == enemyrect.centerY())
				stop();
			else if (rand.nextInt(100) <= 3)
				moveLeft();
			else
				moveRight();
				
		}

		movingFrames += 1;

		if (movingFrames > 30) {
			stop();
			movingFrames = 0;
		}

		if (rand.nextInt(100) == 1)
			attack();
		if (rand.nextInt(100) == 1)
			jump();
		if (rand.nextInt(100) <= 3) {
			if (player1rect.centerY() <= enemyrect.centerY())
				jump();
			else
				moveLeft();
		}

		// update
		update();
	}

}