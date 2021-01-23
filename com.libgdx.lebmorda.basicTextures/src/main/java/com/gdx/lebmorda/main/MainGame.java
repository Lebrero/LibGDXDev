package com.gdx.lebmorda.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainGame extends ApplicationAdapter {
	 private Stage stage;
	@Override
	public void create () {
		 stage = new Stage(new ScreenViewport());
		 
		 	//Creamos una textura a partir de una imagen
	        Texture texture = new Texture(Gdx.files.internal("image.jpg"));
	 
	        //Imagen 1: Creamos una imagen a partir de la textura y la posicionamos
		        Image image1 = new Image(texture);
		        image1.setPosition(Gdx.graphics.getWidth()/3-image1.getWidth()/2,Gdx.graphics.getHeight()*2/3-image1.getHeight()/2);
		        stage.addActor(image1);
	 
	        //Imagen 2: Creamos otra imagen a partir de la textura la posicionamos establecemos el origen y la rotamos 45 grados
		        Image image2 = new Image(texture);
		        image2.setPosition(Gdx.graphics.getWidth()*2/3-image2.getWidth()/2,Gdx.graphics.getHeight()*2/3-image2.getHeight()/2);
		        image2.setOrigin(image2.getWidth()/2,image2.getHeight()/2);
		        image2.rotateBy(45);
		        stage.addActor(image2);
	 
	        //Imagen 3: Creamos otra imagen la posicionamos y la escalamos
		        Image image3 = new Image(texture);
		        image3.setSize(texture.getWidth()/2,texture.getHeight()/2);
		        image3.setPosition(Gdx.graphics.getWidth()/3-image3.getWidth()/2,Gdx.graphics.getHeight()/3-image3.getHeight());
		        stage.addActor(image3);
	 
	        //Imagen 4: Repetimos la textura en ambas coordenadas (por lo visto esta repeticion se extenderá indefinidamente, es como
	        //un patrón infinito)
		        texture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat); 
		        //Creamos una "TextureRegion" a partir de esa textura
		        TextureRegion textureRegion = new TextureRegion(texture);
		        //Especificamos el alcance de la region (8 veces mas ancha * 4 veces mas alta)
		        textureRegion.setRegion(0,0,texture.getWidth()*16,texture.getHeight()*8);
		        //Creamos una imagen y le pasamos la region (esto se hace porque Image extiende de Actor)
		        Image image4 = new Image(textureRegion);
		        //Definimos el tamaño
		        image4.setSize(200,100);
		        //Definimos la posición
		        image4.setPosition(Gdx.graphics.getWidth()*2/3-image4.getWidth()/2,Gdx.graphics.getHeight()/3-image4.getHeight());
		        //añadimos el actor al stage
		        stage.addActor(image4);
	}

	@Override
	public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
	}
	
	@Override
	public void dispose () {
		stage.dispose();
	}
}
