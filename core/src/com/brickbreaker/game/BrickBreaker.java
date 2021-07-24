package com.brickbreaker.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class BrickBreaker extends ApplicationAdapter {
	ShapeRenderer shape;
	Ball ball;
	Paddle paddle;
	Random r = new Random();
	ArrayList<Brick> bricks = new ArrayList<>();
	BitmapFont font;
	SpriteBatch batch;
	Texture heartImage;
	Rectangle heart;
	int lives;
	int points;
	
	@Override
	public void create () {
		lives = 3;
		points = 0;
		batch = new SpriteBatch();
		font = new BitmapFont();
;		shape = new ShapeRenderer();
		ball = new Ball(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 3, 10, 0, 0);
		paddle = new Paddle(50, 25, 100, 10);
		
		heartImage = new Texture(Gdx.files.internal("heart.png"));
		heart = new Rectangle();
		heart.setSize(64, 64);
		heart.x = 0;
		heart.y = Gdx.graphics.getHeight() - 64;
				
		int brickWidth = 72;
		int brickHeight = 20;
		
		for (int y = Gdx.graphics.getHeight() / 2; y < Gdx.graphics.getHeight() - 50; y+= brickHeight + 10) {
			for (int x = 0; x < Gdx.graphics.getWidth(); x+= brickWidth + 10) {
				bricks.add(new Brick(x, y, brickWidth, brickHeight));
			}
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.draw(heartImage, heart.x, heart.y);
		font.draw(batch, ": " + lives, 60, 615);
		font.draw(batch, "Points: " + points, 700, 615);
		batch.end();
		
		ball.update();
		if (ball.getFallen()) {
			if (lives > 0) {
				lives--;
			}
			ball.setUnfallen();
		}
		paddle.update();
		ball.checkCollision(paddle);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		for (Brick brick : bricks) {
			brick.draw(shape);
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
		ball.draw(shape);
		paddle.draw(shape);
		shape.end();
	}
	
	@Override
	public void dispose () {
		
	}
}
