package com.brickbreaker.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Brick extends GameElement{
	boolean destroyed;
	Texture texture;
	
	public Brick(int x, int y, int width, int height, Texture texture) {
		super(x, y, width, height);
		this.destroyed = false;
		this.texture = texture;
	}
	
}
