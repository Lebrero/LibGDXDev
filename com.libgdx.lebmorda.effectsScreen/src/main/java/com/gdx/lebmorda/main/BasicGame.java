package com.gdx.lebmorda.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.gdx.lebmorda.screens.TitleScreen;

public class BasicGame extends Game {

	public SpriteBatch batch;
	public Skin gameSkin;
	
	@Override
	public void create () {
	    gameSkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
		batch = new SpriteBatch();
		this.setScreen(new TitleScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		screen.dispose();
	}

}
