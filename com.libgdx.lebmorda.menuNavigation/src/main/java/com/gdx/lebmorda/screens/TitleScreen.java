package com.gdx.lebmorda.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.lebmorda.main.BasicGame;

public class TitleScreen extends ScreenAdapter {

	BasicGame game;
	private FitViewport viewport;
	private Stage stage;

	public TitleScreen(BasicGame game) {
		this.game = game;
	}

	@Override
	public void show() {

		viewport = new FitViewport(640, 480);
		stage = new Stage(viewport, game.batch);

		Label title = new Label("Title Screen", game.gameSkin, "big-black");
		title.setAlignment(Align.center);
		title.setY(Gdx.graphics.getHeight() * 2 / 3);
		title.setWidth(Gdx.graphics.getWidth());
		stage.addActor(title);

		TextButton playButton = new TextButton("Play!", game.gameSkin);
		//EL acho del botón:
		//-la mitad del ancho de la pantalla
		playButton.setWidth(Gdx.graphics.getWidth() / 2);
		//La posición del boton es el medio de la pantalla:
		//- la mitad de lo largo de la pantalla entre dos - el largo del boton
		//- la mitad del acho de la pantalla entre dos - el alto del boton
		playButton.setPosition(Gdx.graphics.getWidth() / 2 - playButton.getWidth() / 2,Gdx.graphics.getHeight() / 2 - playButton.getHeight() / 2);
		playButton.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new GameScreen(game));
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		stage.addActor(playButton);

		TextButton optionsButton = new TextButton("Options", game.gameSkin);
		optionsButton.setWidth(Gdx.graphics.getWidth() / 2);
		optionsButton.setPosition(Gdx.graphics.getWidth() / 2 - optionsButton.getWidth() / 2,
				Gdx.graphics.getHeight() / 4 - optionsButton.getHeight() / 2);
		optionsButton.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new OptionScreen(game));
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		stage.addActor(optionsButton);

		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Es imprescindible para que se refejen las acciones
		stage.act();

		// Es imprescindible para que se visualicen los actores
		stage.draw();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}
