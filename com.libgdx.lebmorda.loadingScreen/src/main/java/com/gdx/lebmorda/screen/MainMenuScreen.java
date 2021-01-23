package com.gdx.lebmorda.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.lebmorda.loading.MainGame;

/**
 */
public class MainMenuScreen extends AbstractScreen {

	SpriteBatch batch;
	Texture img;

	public MainMenuScreen(MainGame game) {
		super(game);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();

		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			game.setScreen(new LoadingScreen(game));
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		img = new Texture("img/badlogic.jpg");
	}

	@Override
	public void hide() {
	}
}
