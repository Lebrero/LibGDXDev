package com.gdx.lebmorda.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.lebmorda.main.BasicGame;

public class TitleScreen extends AbstractScreen {
	protected Stage stage;
	protected BasicGame game;

	public TitleScreen(BasicGame game) {
		this.game= game;
	}

	@Override
	public void show() {
	        stage = new Stage(new ScreenViewport());

	        Label title = new Label("There and Back Again", game.skin,"big-black");
	        title.setAlignment(Align.center);
	        title.setY(Gdx.graphics.getHeight()*2/3);
	        title.setWidth(Gdx.graphics.getWidth());
	        stage.addActor(title);

	        TextButton playButton = new TextButton("Play!",game.skin);
	        playButton.setWidth(Gdx.graphics.getWidth()/2);
	        playButton.setPosition(Gdx.graphics.getWidth()/2-playButton.getWidth()/2,Gdx.graphics.getHeight()/2-playButton.getHeight()/2);
	        playButton.addListener(new InputListener(){
	            @Override
	            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	                game.setScreen(new GameScreen(game));
	            }
	            @Override
	            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	                return true;
	            }
	        });
	        stage.addActor(playButton);
	        Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void dispose() {
		stage.dispose();		
	}

}
