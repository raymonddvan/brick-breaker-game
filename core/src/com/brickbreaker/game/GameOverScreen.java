package com.brickbreaker.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameOverScreen implements Screen {
	final BrickBreaker game;
	final GameScreen playScreen;
	
	
	public GameOverScreen(final BrickBreaker game, final GameScreen playScreen) {
		this.game = game;
		this.playScreen = playScreen;
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
		game.shape.begin(ShapeRenderer.ShapeType.Filled);
		
		game.batch.draw(playScreen.heartImage, playScreen.heart.x, playScreen.heart.y);
		game.subFont.draw(game.batch, ": " + playScreen.lives, 60, 615);
		game.subFont.draw(game.batch, "Points: " + playScreen.points, 600, 615);
		
		for (Brick brick : playScreen.bricks) {
			game.batch.draw(brick.texture, brick.x, brick.y, brick.width, brick.height);
		}
//		game.batch.draw(playScreen.paddleImage, 350, playScreen.paddle.y, playScreen.paddle.width, playScreen.paddle.height);
		this.playScreen.paddle.draw(game.shape);
		game.menuFont.draw(game.batch, "Game Over!", 300, 250);
		game.subFont.draw(game.batch, "(touch/press anywhere to restart)", 160, 200);
		
		if (Gdx.input.isTouched()) {
			this.playScreen.resetLives();
			this.playScreen.resetPoints();
			this.playScreen.bricks.clear();
			this.playScreen.addBricks(playScreen.brickWidth, playScreen.brickHeight);
			this.game.setScreen(playScreen);
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
