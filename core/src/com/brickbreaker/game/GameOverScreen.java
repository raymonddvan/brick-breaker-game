package com.brickbreaker.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameOverScreen implements Screen {
	final BrickBreaker game;
	final GameScreen screen;
	
	
	public GameOverScreen(final BrickBreaker game, final GameScreen screen) {
		this.game = game;
		this.screen = screen;
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
		game.batch.draw(screen.heartImage, screen.heart.x, screen.heart.y);
		game.subFont.draw(game.batch, ": " + screen.lives, 60, 615);
		game.subFont.draw(game.batch, "Points: " + screen.points, 600, 615);
		game.shape.begin(ShapeRenderer.ShapeType.Filled);
		for (Brick brick : screen.bricks) {
			brick.draw(game.shape);
		}
		screen.paddle.draw(game.shape);
		game.menuFont.draw(game.batch, "Game Over!", 300, 250);
		game.subFont.draw(game.batch, "(touch/press anywhere to restart)", 160, 200);
		if (Gdx.input.isTouched()) {
			this.screen.resetLives();
			this.screen.resetPoints();
			this.screen.bricks.clear();
			this.screen.addBricks(screen.brickWidth, screen.brickHeight);
			this.game.setScreen(screen);
		}
		game.batch.end();
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

}
