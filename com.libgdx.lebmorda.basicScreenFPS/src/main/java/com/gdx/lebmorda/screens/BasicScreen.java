package com.gdx.lebmorda.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.gdx.lebmorda.main.BasicGame;

public class BasicScreen extends AbstractScreen {

	Texture img;
	BasicGame game;
	FPSLogger fpsLogger;

	public BasicScreen(BasicGame game) {
		this.game = game;
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void show() {
		fpsLogger = new FPSLogger();
	}

	@Override
	public void render(float delta) {
		fpsLogger.log();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		game.batch.draw(img, 0, 0);
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
