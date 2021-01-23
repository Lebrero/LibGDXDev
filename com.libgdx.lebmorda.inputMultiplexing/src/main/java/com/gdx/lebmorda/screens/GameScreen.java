package com.gdx.lebmorda.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.lebmorda.main.BasicGame;

public class GameScreen extends ScreenAdapter {

	// Stages
	private Stage gameStage;
	private Stage UIStage;

	private BasicGame game;
	private OrthographicCamera camera;

	// Objeto detipo slider
	private Slider slider;

	//
	private InputMultiplexer multiplexer;

	public GameScreen(BasicGame aGame) {
		this.game = aGame;

		// Instanciamos las dos Stages y le creamos un ScreenViewport independiente
		gameStage = new Stage(new ScreenViewport());
		UIStage = new Stage(new ScreenViewport());

		// InputMultiplexer:
		// Gestiona varios InputProcessor, esto podemos necesitarlo
		// cuando tenemos varias Stages y necesitamos recoger los eventos de cada una de
		// forfa individual. En nuestro caso en particular, neceistamos recoger los
		// eventos de UIStage y de gameStage

		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(UIStage);
		multiplexer.addProcessor((gameStage));

		// Instanciamos la imagen
		Image map = new Image(new Texture("map.jpg"));
		
		//Añadimos un Listener ActorGestureListener de tipo pan (desplazamiento de ratón)
		map.addListener(new ActorGestureListener() {
			@Override
			public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
				super.pan(event, x, y, deltaX, deltaY);
				camera.position.x -= (deltaX * Gdx.graphics.getDensity());
				camera.position.y -= (deltaY * Gdx.graphics.getDensity());

			}
		});
		
		gameStage.addActor(map);
		
		//Creamos y posicionamos la imagen del anillo y la añadimos al gameStage
		Image ring = new Image((new Texture("ring.png")));
		ring.setPosition(1100, 1225);
		gameStage.addActor(ring);
		
		//Creamos y posicionamos la lupa y lo añadimos al UIStage
		Image magnifier = new Image(new Texture("magnifier.png"));
		magnifier.setPosition(Gdx.graphics.getWidth() / 2 - magnifier.getWidth() / 4,Gdx.graphics.getHeight() / 2 - magnifier.getHeight() / 2);
		
		UIStage.addActor(magnifier);

		//Creamos el slider le posicionamos, le damos valor..etc y configuramos su inputListener, en función de su posición, hacermos mas o menos zoom a la camara
		slider = new Slider(1, 2, 0.01f, true, game.skin);
		slider.setAnimateInterpolation(Interpolation.smooth);
		slider.setAnimateDuration(0.1f);
		slider.setHeight(Gdx.graphics.getHeight() * 0.8f);
		slider.setPosition(Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() / 10);
		slider.setValue(1.55f);
		slider.addListener(new InputListener() {
			@Override
			public void touchDragged(InputEvent event, float x, float y, int pointer) {
				
				//Es aqui donde en función del valor del Slider establecemos el zoom
				camera.zoom = slider.getValue();
				camera.update();
				// Gdx.app.log("touchDragged","slider Value:"+slider.getValue());
				super.touchDragged(event, x, y, pointer);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				camera.zoom = slider.getValue();
				camera.update();
				Gdx.app.log("up", "slider Value:" + slider.getValue());

			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				camera.zoom = slider.getValue();
				camera.update();
				Gdx.app.log("down", "slider Value:" + slider.getValue());

				return true;
			}
		});
		UIStage.addActor(slider);
		
		//Asignamos la camara que hemos creado al gameStage y la configuramos 
		camera = (OrthographicCamera) gameStage.getViewport().getCamera();
		camera.translate(200, 250);
		camera.zoom = 1.55f;
	}

	@Override
	public void show() {
		Gdx.app.log("MainScreen", "show");
		Gdx.input.setInputProcessor(multiplexer);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		UIStage.act();
		gameStage.act();
		gameStage.draw();
		UIStage.draw();

	}

	@Override
	public void dispose() {
		UIStage.dispose();
		gameStage.dispose();
	}
}
