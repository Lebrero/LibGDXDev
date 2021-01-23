package com.gdx.lebmorda.launcher;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gdx.lebmorda.main.BasicGame;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new BasicGame(), config);

		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		// Gdx.app.setLogLevel(Application.LOG_INFO);
		// Gdx.app.setLogLevel(Application.LOG_DEBUG);
		// Gdx.app.setLogLevel(Application.LOG_ERROR);
		// Gdx.app.setLogLevel(Application.LOG_NONE);

	}
}
