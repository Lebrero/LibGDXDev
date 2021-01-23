package com.gdx.lebmorda.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Json;
import com.gdx.lebmorda.main.BasicGame;
import com.libgdx.lebmorda.serialized.Bounds;
import com.libgdx.lebmorda.serialized.BoundsSerializer;
import com.libgdx.lebmorda.serialized.DisplayConfig;


public class BasicScreen extends ScreenAdapter {

	BasicGame game;
	Json json;

	public BasicScreen(BasicGame game) {
		this.game = game;
	}

	@Override
	public void show() {
		loadJson();

	}

	public void loadJson() {

		FileHandle handle = Gdx.files.internal("display.json");

		// Lee el contenido
		String fileContent = handle.readString();
		
		Json json = new Json();
		
		json.setSerializer(Bounds.class, new BoundsSerializer());
		    
		DisplayConfig data = json.fromJson(DisplayConfig.class, fileContent);

		System.out.println("Title: " + data.title);
		System.out.println("Vsync: " + data.vsync);
		System.out.println("Fullscreen: " + data.fullscreen);
		System.out.println("Foreground Fps: " + data.foregroundFps);
		System.out.println("Background Fps: " + data.backgroundFps);
		System.out.println("Width: " + data.windowBounds.width);
		System.out.println("Height: " +  data.windowBounds.height);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void dispose() {

	}

}
