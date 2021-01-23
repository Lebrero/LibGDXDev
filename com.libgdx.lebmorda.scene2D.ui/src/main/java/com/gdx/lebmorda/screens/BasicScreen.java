package com.gdx.lebmorda.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gdx.lebmorda.main.BasicGame;

public class BasicScreen extends ScreenAdapter {
	
	BasicGame game;
    private FitViewport viewport;
    private Stage stage;
    private Skin skin;
    
	public BasicScreen(BasicGame game) {
		this.game= game;
	}
	
    @Override
    public void show() {
        viewport = new FitViewport(640, 480);
        stage = new Stage(viewport, game.batch);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        

        final TextButton button = new TextButton("Haz click!", skin, "default");
        
        button.setWidth(200f);
        button.setHeight(20f);
        button.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - 10f);
        
        button.addListener(new ClickListener(){
            @Override 
            public void clicked(InputEvent event, float x, float y){
                button.setText("Has hecho click!");
            }
        });
        
        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);


    }

	@Override
	public void render(float delta) {		
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();	
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}
