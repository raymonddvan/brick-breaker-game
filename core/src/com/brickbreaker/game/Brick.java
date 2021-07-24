package com.brickbreaker.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Brick extends GameElement{
	boolean destroyed;
	
	public Brick(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.destroyed = false;
	}
	
}
