package com.emreozgenc.parallax;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Parallax extends Game {

	public SpriteBatch batch;
	ParallaxEffect screen;
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new ParallaxEffect(this));
	}

	@Override
	public void render() {
		super.render();

	}

	@Override
	public void dispose() {
		batch.dispose();
		screen.dispose();
	}

}
