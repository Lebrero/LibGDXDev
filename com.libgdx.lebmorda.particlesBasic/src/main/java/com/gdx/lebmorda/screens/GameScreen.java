package com.gdx.lebmorda.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.lebmorda.Scene2D.actors.MusicalNote;
import com.gdx.lebmorda.Scene2D.actors.Musician;

/**
 * Created by julienvillegas on 17/01/2017.
 */
public class GameScreen extends ScreenAdapter {

    private Stage stage;


    public GameScreen() {

        stage = new Stage(new ScreenViewport());

        //Creamos el actor músico lo posicionamos y lo añadimos al Stage
        Musician musician = new Musician();
        musician.setX(Gdx.graphics.getWidth()/12);
        stage.addActor(musician);

        //Creamos el actor nota lo posicionamos y lo añadimos al Stage (le añadimos un movimiento de arriba, abajo infinito)
        MusicalNote musicalNote = new MusicalNote();
        musicalNote.setPosition(Gdx.graphics.getWidth()*3/5,Gdx.graphics.getHeight()*1/5);
		musicalNote.addAction(Actions.repeat(-1,
				Actions.sequence(
						Actions.moveTo(Gdx.graphics.getWidth() * 3 / 5, Gdx.graphics.getHeight() * 3 / 5, 2,
								Interpolation.sine),
						Actions.moveTo(Gdx.graphics.getWidth() * 3 / 5, Gdx.graphics.getHeight() * 1 / 5, 2,
								Interpolation.sine))));
		
        stage.addActor(musicalNote);


    }

    @Override
    public void show() {
        Gdx.app.log("MainScreen","show");

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }



    @Override
    public void dispose() {
        stage.dispose();
    }


}