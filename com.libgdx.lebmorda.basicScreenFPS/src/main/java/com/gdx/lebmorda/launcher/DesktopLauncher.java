package com.gdx.lebmorda.launcher;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gdx.lebmorda.main.BasicGame;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.backgroundFPS = 0;
		config.foregroundFPS = 0;
		new LwjglApplication(new BasicGame(), config);
	}
}
