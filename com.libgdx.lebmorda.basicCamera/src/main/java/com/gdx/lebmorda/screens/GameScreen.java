package com.gdx.lebmorda.screens;

import java.time.Instant;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.lebmorda.main.BasicGame;

public class GameScreen extends AbstractScreen {
	
	protected BasicGame game;
	protected Stage stage;
    private OrthographicCamera camera;
    
    //Coordenadas de origen
    private final int startX = 1100;
    private final int startY = 1225;

    //Coordenadas de destino
    private final int endX = 2350;
    private final int endY = 600;

    private float minAltitude = 0.5f;
    private float maxAltitude = 2.5f;

    private float percent;
    private float counter;
    private long startTime;
    
    //Duración de la animación (en ms)
    private final float animation_duration = 15000;
    
	public GameScreen(BasicGame game) {
		this.game=game;		
	    stage = new Stage(new ScreenViewport());
        Image map = new Image(new Texture("map.jpg"));    
        stage.addActor(map);       
        
		// En esta parte del código estamos "sacando" la camara del stage para poder
		// manipularla despues
        camera = (OrthographicCamera) stage.getViewport().getCamera();
        //Trasladamos la cámara a la posición fija definida anteriormente
        camera.translate(startX,startY);
        //Resetear el contador
        counter = 0;
        //Tomamos el momento actual (en ms)
        startTime = Instant.now().toEpochMilli();
	}

	@Override
	public void show() {
		//Logs
        Gdx.app.log("MainScreen","show");
        //Seleccionamos la entrada del usuario
        Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		
        long secondFromStart = Instant.now().toEpochMilli()-startTime;
        
        percent = (secondFromStart%animation_duration)/animation_duration;
        percent = (float)Math.cos(percent*Math.PI*2)/2+0.5f;
        
        //Logs
        Gdx.app.log("render","secondFromStart:"+ secondFromStart+", %:"+percent);
        
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        moveCamera();
        stage.act();
        stage.draw();
	}
	  private void moveCamera(){
		  
	        float currentX = startX + (endX-startX)*percent;
	        float currentY = startY + (endY-startY)*percent;
	        float percentZ = Math.abs(percent - 0.5f)*2;
	        float currentZ = maxAltitude - (maxAltitude-minAltitude)*percentZ  ;

	        camera.position.x = currentX;
	        camera.position.y = currentY;
	        camera.zoom = currentZ;
	        camera.update();
	    }

	@Override
	public void dispose() {
        stage.dispose();

	}

}
