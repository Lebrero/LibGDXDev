package com.libgdx.lebmorda.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.lebmorda.main.BasicGame;

public class Render {

	public static SpriteBatch batch;
	public static BasicGame app;

	public static void limpiarPantalla(float r, float g, float b, float a) {
		Gdx.gl.glClearColor(r, g, b, a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	};

}
