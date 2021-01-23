package com.gdx.lebmorda.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gdx.lebmorda.main.BasicGame;
import com.gdx.lebmorda.scene2D.actors.FondoActor;

public class BasicScreen extends ScreenAdapter {

	protected FitViewport viewport;
	protected FondoActor fondo;

	// Constantes con los valores de las filas y columnas
	private static final int FRAME_COLS = 5;
	private static final int FRAME_ROWS = 5;

	// Animation de tipo TextureRegion
	Animation<TextureRegion> animacion;

	// Textura que contiene la explosión
	Texture explosion;

	// A variable for tracking elapsed time for the animation
	float stateTime;



	@Override
	public void show() {
		viewport = new FitViewport(640, 480);
		fondo = new FondoActor();

		// Cargamos la textura
		explosion = new Texture(Gdx.files.internal("animation_explosion2.png"));

		// Cargamos en un array bidimensional de TextureRegion los frames
		TextureRegion[][] textureFrames = TextureRegion.split(
				explosion, 
				explosion.getWidth() / FRAME_COLS,
				explosion.getHeight() / FRAME_ROWS);

		// Una vez divididas las vamos a meter en orden de reproduccion en un array de
		// TextureRegion. Este array deberá ser del tamaño resultante de multiplicar las
		// columnas y las filas

		TextureRegion[] textureRegionId = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				textureRegionId[index++] = textureFrames[i][j];
			}
		}

		// Inicializamos la animación especificando el tiempo entre frames
		animacion = new Animation<TextureRegion>(0.025f, textureRegionId);
	//	walkAnimation.setPlayMode(PlayMode.LOOP_PINGPONG);

		// Variable animación que nos da paso al siguiente frame
		stateTime = 0f;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stateTime += Gdx.graphics.getDeltaTime();

		// Hacemos la llamada a la animación y guardamos el frame que nos devuelva
		// getKeyFrame()
		TextureRegion currentFrame = animacion.getKeyFrame(stateTime, true);

		BasicGame.batch.begin();
		// dibujamos el frame
		BasicGame.batch.draw(currentFrame, 50, 50);
		BasicGame.batch.end();
	}

	@Override
	public void dispose() {
		explosion.dispose();

	}
}
