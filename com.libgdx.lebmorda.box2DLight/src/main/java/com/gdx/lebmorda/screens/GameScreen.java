package com.gdx.lebmorda.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.lebmorda.box2D.Bodies.B2dContactListener;
import com.gdx.lebmorda.box2D.Bodies.BallGenerator;
import com.gdx.lebmorda.box2D.Bodies.FireEmitter;
import com.gdx.lebmorda.box2D.Bodies.GearActor;
import com.gdx.lebmorda.box2D.Bodies.WindowsFrame;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class GameScreen extends Game {
	SpriteBatch batch;
	private World world;
	private Stage stage;
	private Box2DDebugRenderer debugRenderer;

	private RayHandler rayHandler;

	@Override
	public void create () {
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
		stage.draw();

		//debugRenderer.render(world, stage.getCamera().combined);

		BallGenerator.getInstance().emit();

		rayHandler.setCombinedMatrix(stage.getCamera().combined,0,0,1,1);
		rayHandler.updateAndRender();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		rayHandler.dispose();
	}



}