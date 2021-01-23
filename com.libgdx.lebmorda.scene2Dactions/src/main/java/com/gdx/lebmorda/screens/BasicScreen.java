package com.gdx.lebmorda.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gdx.lebmorda.main.BasicGame;
import com.gdx.lebmorda.scene2D.actors.FondoActor;

public class BasicScreen extends ScreenAdapter {

	BasicGame game;
	private FitViewport viewport;
	private Stage stage;
	FondoActor fondo;

	public BasicScreen(BasicGame game) {
		this.game = game;
	}

	@Override
	public void show() {

		viewport = new FitViewport(640, 480);
		stage = new Stage(viewport, game.batch);
		fondo = new FondoActor();

		// Creamos una accion
		MoveToAction moveAction = new MoveToAction();
		moveAction.setPosition(300f, 0f);
		moveAction.setDuration(1f);

		// Añadimos la accion al actor
		fondo.addAction(moveAction);

		// Añadimos el actor
		stage.addActor(fondo);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1.0f);
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
