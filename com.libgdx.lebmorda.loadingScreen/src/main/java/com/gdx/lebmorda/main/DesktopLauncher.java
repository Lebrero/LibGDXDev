package com.gdx.lebmorda.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gdx.lebmorda.loading.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "libgdx-loading-screen";
		cfg.width = 800;
		cfg.height = 480;

		new LwjglApplication(new MainGame(), cfg);

	}
}


