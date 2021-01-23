package com.gdx.lebmorda.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.lebmorda.main.BasicGame;
import com.libgdx.lebmorda.effects.ScreenEffects;

public class GameScreen extends AbstractScreen {
	private Stage stage;
    private BasicGame game;
    
	public GameScreen(BasicGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());	}

	@Override
	public void show() {
	        Label title = new Label("Playing Screen", game.gameSkin,"big-black");

	        title.setAlignment(Align.center);
	        title.setY(Gdx.graphics.getHeight()*2/3);
	        title.setWidth(Gdx.graphics.getWidth());
	        stage.addActor(title);
	        
	        TextButton backButton = new TextButton("Go Back",game.gameSkin);
	        backButton.setWidth(Gdx.graphics.getWidth()/2);
	        backButton.setPosition(Gdx.graphics.getWidth()/2-backButton.getWidth()/2,Gdx.graphics.getHeight()/4-backButton.getHeight()/2);
	        backButton.addListener(new InputListener(){
	            @Override
	            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	          
					ScreenEffects.setSlideScreen(stage, game,new TitleScreen(game),true,0.3f);

	            }
	            @Override
	            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	                return true;
	            }
	        });
	        stage.addActor(backButton);
	        
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
	public void dispose() {
        stage.dispose();
	}

}
