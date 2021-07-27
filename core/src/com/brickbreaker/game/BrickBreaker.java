package com.brickbreaker.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class BrickBreaker extends Game {
	public static final int HEIGHT = 640;
	public static final int WIDTH = 810;
	SpriteBatch batch;
	BitmapFont titleFont;
	BitmapFont subFont;
	BitmapFont menuFont;
	ShapeRenderer shape;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		titleFont = new BitmapFont(Gdx.files.internal("menu_title.fnt"));
		subFont = new BitmapFont(Gdx.files.internal("sub_title.fnt"));
		menuFont = new BitmapFont(Gdx.files.internal("mid_title.fnt"));
		
		
		shape = new ShapeRenderer();
		
		this.setScreen(new MainMenuScreen(this));
		
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		titleFont.dispose();
		subFont.dispose();
		menuFont.dispose();
		shape.dispose();
		
	}
}
