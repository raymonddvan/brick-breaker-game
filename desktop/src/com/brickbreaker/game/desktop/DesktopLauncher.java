package com.brickbreaker.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.brickbreaker.game.BrickBreaker;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Brick Breaker";
		config.width = 810;
		config.height = 640;
		new LwjglApplication(new BrickBreaker(), config);
	}
}
