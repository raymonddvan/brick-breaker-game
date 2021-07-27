package com.brickbreaker.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
	final BrickBreaker game;
	OrthographicCamera camera;
	TextButton button;
	Texture menuBackground;
	
	
	public MainMenuScreen(final BrickBreaker game) {
		this.game = game;
		this.menuBackground = new Texture(Gdx.files.internal("space-pic.png"));
		
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(Color.BLACK);
		
		this.game.batch.begin();
		this.game.batch.draw(menuBackground, 0, 0, game.WIDTH, game.HEIGHT);
		this.game.titleFont.draw(game.batch, "Brick Breaker!", 200, 450);
		this.game.subFont.draw(game.batch, "Press anywhere to start", 250, 380);
		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
		this.game.batch.end();
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

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
