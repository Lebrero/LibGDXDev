package com.gdx.lebmorda.launcher;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width  = 320;
		config.height = 240;
		
		new LwjglApplication(new GdxAnimationExample(), config);
	}
}
