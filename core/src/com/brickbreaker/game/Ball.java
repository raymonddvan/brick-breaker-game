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
	double xSpeed;
	double ySpeed;
	boolean isFallen;
	boolean playing;
	Color color = Color.WHITE;
	int position;
	boolean inside;
	
	
	public Ball(int x, int y, int size, double xSpeed, double ySpeed) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.isFallen = false;
		this.playing = false;
		this.position = 0;
		this.inside = false;
	}
	
	public void update() {
		x += xSpeed;
		y += ySpeed;
		if (x < size || x > Gdx.graphics.getWidth() - size) {
			xSpeed = -xSpeed;
			this.inside = false;
		}
		if (y > Gdx.graphics.getHeight() - 64) {
			ySpeed = -ySpeed;
			this.inside = false;
		}
		if (y < 0) {
			this.resetBall();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			if (xSpeed == 0) {
				xSpeed = MathUtils.randomSign();
				ySpeed = -4;
				isFallen = false;
				playing = true;
			}
		}
	}  
	
	public void checkCollision(GameElement element) {
		if (collidesWith(element)) {
//			System.out.print(inside);
//			System.out.println(position);
			if (inside) {
				while (checkSide(element) > 0) {
					if (checkSide(element) == 1) {
						xSpeed = -5;
						break;
					}
					else if (checkSide(element) == 3 || checkSide(element) == 2) {
						xSpeed = 5;
						break;
					}
				}
				ySpeed = 5;
				if (element instanceof Brick) {
					((Brick) element).destroyed = true;
					this.inside = false;
					ySpeed = -ySpeed;
				}
			}
			else {
				switch (position) {
				case 3:
					ySpeed = -ySpeed;
					switch (checkSide(element)) {
					case 1:
						xSpeed = -5;
						break;
					case 2:
						xSpeed = 1;
						break;
					case 3:
						xSpeed = 5;
						break;
					}
//					System.out.println(xSpeed);
					break;
				case 2:
					xSpeed = -xSpeed;
					ySpeed = -ySpeed;
					break;
				case 1:
					xSpeed = -xSpeed;
					ySpeed = -ySpeed;
					break;
				}
				if (element instanceof Brick) {
					((Brick) element).destroyed = true;
				}
			}

		}
	}
	
	public void draw(ShapeRenderer shape) {
		shape.setColor(color);
		shape.circle(x, y, size);
	}
	
	private boolean collidesWith(GameElement element) {
		// left = 1, right = 2
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
		
		if ((this.x - this.size / 2) < (element.x + element.width) && (this.x + this.size / 2) > element.x && (this.y - this.size / 2) < (element.y + element.height) && (this.y + this.size / 2) > element.y) {
			if (element instanceof Paddle) {
				this.inside = true;
				return true;
			}
		}
		
		if (distance <= this.size) {
			if ((this.x) < (element.x + element.width) && (this.x) > element.x) {
				position = 3;
			}
			
			else if ((this.y - this.size) <= (element.y + element.height) && (this.y - this.size) >= element.y) {
				position = 1;
			}
			else {
				this.inside = false;
				position = 2;
			}
			return true;
		}
		return false;	
	}
	
	private int checkSide(GameElement element) {
		if (element instanceof Brick) {
			return 0;
		}
		if (this.x < (element.x + element.width / 3)) {
			return 1; //left
		}
		else if (this.x > (element.x + element.width / 3 * 2)) {
			return 3; //right
		}
		else {
			return 2; // middle
		}
	}
	
	public boolean getFallen() {
		return this.isFallen;
	}
	
	public void setUnfallen() {
		this.isFallen = false;
	}
	
	public void setNotPlaying() {
		this.playing = false;
	}
	
	public void reverseYSpeed() {
		this.ySpeed = -ySpeed;
	}
	
	public void resetBall() {
		x = Gdx.graphics.getWidth() / 2;
		y = Gdx.graphics.getHeight() / 3;
		xSpeed = 0;
		ySpeed = 0;
		isFallen = true;
	}
}
