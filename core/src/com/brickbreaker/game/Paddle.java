package com.brickbreaker.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle extends GameElement {
	
	public Paddle(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void update() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) x -= 400 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x += 400 * Gdx.graphics.getDeltaTime();
		if (x < 0) x = 0;
		if (x > Gdx.graphics.getWidth() - width) x = Gdx.graphics.getWidth() - width;
	}
	
}
