package com.gdx.lebmorda.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BasicGame extends Game {

	public SpriteBatch batch;
	Texture img;

	
	@Override
	public void create () {
		batch= new SpriteBatch();
		img = new Texture("badlogic.jpg");
		Gdx.app.debug("DEBUG", "Mensaje de debug");
		Gdx.app.log("INFO", "Mensaje de log");
		Gdx.app.error("ERROR", "Mensaje de error");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();	
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		screen.dispose();
	}

}
