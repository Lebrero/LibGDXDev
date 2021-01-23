package com.gdx.lebmorda.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.lebmorda.screens.StartScreen;
import com.libgdx.lebmorda.utils.Render;

public class BasicGame extends Game {

	@Override
	public void create() {
		Render.app = this;
		Render.batch = new SpriteBatch();
		this.setScreen(new StartScreen());
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {

	}

}
