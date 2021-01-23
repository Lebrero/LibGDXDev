package com.gdx.lebmorda.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Json;
import com.gdx.lebmorda.main.BasicGame;
import com.libgdx.lebmorda.serialized.Config;
import com.libgdx.lebmorda.serialized.Position;

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
		
		//Leemos el fichero json
		FileHandle handle = Gdx.files.internal("enemy.json");	
		
		//Lee el contenido 
		String fileContent = handle.readString();
			
		Json json = new Json();
		
		json.setElementType(Config.class, "enemies", Position.class);
		
		Config data = new Config();
		
		data = json.fromJson(Config.class, fileContent);

		Gdx.app.log("LOG", "Data name = " + data.name);
		
		for (Object e : data.enemies) {
			Position p = (Position) e;
			Gdx.app.log("LOG", "type = " + p.type + "-->x = " + p.x + "-->y =" + p.y);
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
