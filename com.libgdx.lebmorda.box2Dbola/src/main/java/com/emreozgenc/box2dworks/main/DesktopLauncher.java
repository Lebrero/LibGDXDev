package com.emreozgenc.box2dworks.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.emreozgenc.box2dworks.Box2DWorks;
import com.emreozgenc.box2dworks.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constants.WIDTH;
		config.height = Constants.HEIGHT;
		config.resizable = false;
		new LwjglApplication(new Box2DWorks(), config);
	}
}
