package com.gdx.lebmorda.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Json;
import com.gdx.lebmorda.main.BasicGame;
import com.libgdx.lebmorda.serialized.MapArrayJsonReader;
import com.libgdx.lebmorda.serialized.MapJsonReader;

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
		
		Json json = new Json();
		json.setElementType(MapArrayJsonReader.class, "array", MapJsonReader.class);
		MapArrayJsonReader map = json.fromJson(MapArrayJsonReader.class, Gdx.files.internal("map.json"));
		
		for(Object a : map.array){
		   MapJsonReader m = (MapJsonReader) a;
		   System.out.println("Name: "+m.name);
		   System.out.println("X: "+m.x);
		   System.out.println("Y: "+m.y);
		   System.out.println("\n");
		}
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

