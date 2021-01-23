package com.gdx.lebmorda.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.gdx.lebmorda.screens.TitleScreen;

public class BasicGame extends Game {

	public SpriteBatch batch;
	public Skin skin;
	static public TextureAtlas textureAtlas;


	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
		textureAtlas = new TextureAtlas();
		textureAtlas.addRegion("note",new TextureRegion(new Texture("note.png")));
		this.setScreen(new TitleScreen(this));
		

	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		screen.dispose();
	}

}
