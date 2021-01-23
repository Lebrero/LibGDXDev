package com.gdx.lebmorda.scene2D.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FondoActor extends Actor {
	private Texture img;

	public FondoActor() {
		img = new Texture("img/badlogic.jpg");
	}

	/**
	 * Método sobreescrito de la clase Actor. Stage va a entrar por cada llamada
	 * render()
	 */
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(img, getX(), getY());
	}
}
