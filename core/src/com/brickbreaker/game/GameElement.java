package com.brickbreaker.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameElement {
	int x;
	int y;
	int width;
	int height;
	
	public GameElement(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(ShapeRenderer shape) {
		shape.rect(x, y, width, height);
	}
	
	public int getX() {
		return this.x;
	}
}
