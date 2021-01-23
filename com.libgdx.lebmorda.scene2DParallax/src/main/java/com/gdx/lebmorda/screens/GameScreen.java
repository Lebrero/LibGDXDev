package com.gdx.lebmorda.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.lebmorda.main.BasicGame;
import com.gdx.lebmorda.scene2D.actors.ParallaxActor;

public class GameScreen extends AbstractScreen {
	protected Stage stage;
	protected BasicGame game;
	protected OrthographicCamera camera;

	public GameScreen(BasicGame game) {
       this.game = game;
        stage = new Stage(new ScreenViewport());
	}

	@Override
	public void show() {
        camera = (OrthographicCamera) stage.getViewport().getCamera();

        //Array de Texturas
        Array<Texture> textures = new Array<Texture>();
        
        //Iteramos 6 veces para añadir mas texturas al array
        for(int i = 1; i <=6;i++){
        	
        	//Añadimos la textura al Array
            textures.add(new Texture("parallax/img"+i+".png"));
//            
//            //Repetimos la textura en ambas coordenadas
//            textures.get(textures.size-1).setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        }

        //Instanciamos al Actor Parallax y le pasamos el array de texturas
        ParallaxActor parallaxBackground = new ParallaxActor(textures);
        parallaxBackground.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        parallaxBackground.setSpeed(1);
        
        stage.addActor(parallaxBackground);
        Gdx.input.setInputProcessor(stage);

	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
	}

	@Override
	public void dispose() {
        stage.dispose();

	}

}
