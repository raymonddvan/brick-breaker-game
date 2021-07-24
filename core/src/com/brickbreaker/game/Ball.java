package com.brickbreaker.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.math.MathUtils;

public class Ball {
	int x;
	int y;
	int size;
	int xSpeed;
	int ySpeed;
	boolean isFallen;
	Color color = Color.WHITE;
	
	
	public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.isFallen = false;
	}
	
	public void update() {
		x += xSpeed;
		y += ySpeed;
		if (x < size || x > Gdx.graphics.getWidth() - size) {
			xSpeed = -xSpeed;
		}
		if (y > Gdx.graphics.getHeight() - 64) {
			ySpeed = -ySpeed;
		}
		if (y < size) {
			x = Gdx.graphics.getWidth() / 2;
			y = Gdx.graphics.getHeight() / 3;
			xSpeed = 0;
			ySpeed = 0;
			isFallen = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isTouched()) {
			if (xSpeed == 0) {
				switch (MathUtils.random(2)) {
					case 0:
						xSpeed = -MathUtils.random(3, 4);
						break;
					case 1:
						xSpeed = MathUtils.random(3,4);
						break;
					case 2:
						xSpeed = MathUtils.randomSign();
						break;	
				}
				ySpeed = -5;
				isFallen = false;
			}
		}
	}  
	
	public void checkCollision(GameElement element) {
		if (collidesWith(element)) {
			ySpeed = -ySpeed;
			if (element instanceof Brick) {
				((Brick) element).destroyed = true;
			}
		}
	}
	
	public void draw(ShapeRenderer shape) {
		shape.setColor(color);
		shape.circle(x, y, size);
	}
	
	private boolean collidesWith(GameElement element) {
		double checkX = this.x;
		double checkY = this.y;
		
		if ((element.x + element.width) < this.x) {
			checkX = element.x + element.width;
		}
		else if ((element.x > this.x)) {
			checkX = element.x;
		}
		
		if ((element.y + element.height) < this.y) {
			checkY = element.y + element.height;
		}
		else if (element.y > this.y) {
			checkY = element.y;
		}
		
		double distX = this.x - checkX;
		double distY = this.y - checkY;
		double distance = Math.sqrt((distX*distX) + (distY*distY));
		
		if (distance <= this.size) {
			return true;
		}
		return false;
		
	}
	
	public boolean getFallen() {
		return this.isFallen;
	}
	
	public void setUnfallen() {
		this.isFallen = false;
	}
}
