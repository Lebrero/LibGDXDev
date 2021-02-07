package com.gdx.lebmorda.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.lebmorda.screens.BasicScreen;

public class BasicGame extends Game {

	public SpriteBatch batch;
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		this.setScreen(new BasicScreen(this));
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
