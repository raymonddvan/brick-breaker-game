package com.brickbreaker.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen implements Screen {
	final BrickBreaker game;
	
	Ball ball;
	Texture ballImage;
	Paddle paddle;
	Texture paddleImage;
	Random r = new Random();
	ArrayList<Brick> bricks = new ArrayList<>();
	Texture heartImage;
	Rectangle heart;
	int lives;
	int points;
	int brickWidth = 72;
	int brickHeight = 20;
	
	public GameScreen(final BrickBreaker game) {
		this.game = game;
		this.lives = 1;
		this.points = 0;
		this.ball = new Ball(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 3, 10, 0, 0);
		this.paddle = new Paddle(50, 25, 100, 20);
		
		ballImage = new Texture(Gdx.files.internal("58-Breakout-Tiles.png"));
		paddleImage = new Texture(Gdx.files.internal("50-Breakout-Tiles.png"));
		
		heartImage = new Texture(Gdx.files.internal("heart.png"));
		heart = new Rectangle();
		heart.setSize(64, 64);
		heart.x = 0;
		heart.y = Gdx.graphics.getHeight() - 64;
		
		addBricks(brickWidth, brickHeight);
				
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		
		game.batch.draw(heartImage, heart.x, heart.y);
		game.subFont.draw(game.batch, ": " + lives, 60, 615);
		game.subFont.draw(game.batch, "Points: " + points, 600, 615);
//		game.batch.draw(ballImage, ball.x, ball.y, 15, 15);
		game.batch.draw(paddleImage, paddle.x, paddle.y, paddle.width, paddle.height);
		
		if (ball.playing == false) {
			game.menuFont.draw(game.batch, "Press space to start", 200, 275);
		}
		
		ball.update();
		if (ball.getFallen()) {
			if (lives > 0) {
				lives--;
			}
			if (lives == 0) {
				ball.setNotPlaying();
				this.game.setScreen(new GameOverScreen(game, this));
				dispose();
			}
			ball.setNotPlaying();
			ball.setUnfallen();
		}
		displayBricks(bricks);
		game.batch.end();
		paddle.update();
		ball.checkCollision(paddle);
		game.shape.begin(ShapeRenderer.ShapeType.Filled);
		ball.draw(game.shape);
		game.shape.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}
	
	public void addBricks(int brickWidth, int brickHeight) {
		int i = 1;
		for (int y = Gdx.graphics.getHeight() / 2; y < Gdx.graphics.getHeight() - 50; y+= brickHeight + 10) {
			i+=2;
			for (int x = 0; x < Gdx.graphics.getWidth(); x+= brickWidth + 10) {
				bricks.add(new Brick(x, y, brickWidth, brickHeight, new Texture(Gdx.files.internal( i + "-Breakout-Tiles.png"))));
			}
		}
	}
	
	private void displayBricks(ArrayList<Brick> bricks) {
		for (Brick brick : bricks) {
			this.game.batch.draw(brick.texture, brick.x, brick.y, brickWidth, brickHeight);
			ball.checkCollision(brick);
		}
		
		for (int i = 0; i < bricks.size(); i++) {
			Brick brick = bricks.get(i);
			if (brick.destroyed) {
				bricks.remove(brick);
				points += 100;
				i--;
			}
		}
	}
	
	public void resetLives() {
		this.lives = 3;
	}
	
	public void resetPoints() {
		this.points = 0;
	}
	

}
