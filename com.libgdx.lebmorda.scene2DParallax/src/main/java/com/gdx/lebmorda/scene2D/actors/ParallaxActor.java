package com.gdx.lebmorda.scene2D.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class ParallaxActor extends Actor {
	private int scroll;
	private Array<Texture> layers;
	private final int LAYER_SPEED_DIFFERENCE = 2;

	float x, y, width, heigth, scaleX, scaleY;
	int originX, originY, rotation, srcX, srcY;
	boolean flipX, flipY;

	private int speed;

	public ParallaxActor(Array<Texture> textures) {

		layers = textures;
		
		// Recorremos el Array de texturas
		for (int i = 0; i < textures.size; i++) {
			layers.get(i).setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
		}
		
		//Desplazamiente y velocidad
		scroll = 0;
		speed = 0;

		//Reseteamos todos los parametros a 0
		x = y = originX = originY = rotation = srcY = 0;

		//Tamaño de la pantalla
		width = Gdx.graphics.getWidth();
		heigth = Gdx.graphics.getHeight();

		//Seteamos la escala
		scaleX = scaleY = 1;
		
		//Seteamos el volcado
		flipX = flipY = false;
	}

	public void setSpeed(int newSpeed) {
		this.speed = newSpeed;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);

		scroll += speed;

		for (int i = 0; i < layers.size; i++) {
			srcX = scroll + i * this.LAYER_SPEED_DIFFERENCE * scroll;
			
			//Se pintan las imágenes con el desfase que hemos indicado LAYER_SPEED_DIFFERENCE
			//layers.get(i)--> Textura actual
			//srcX, srcY-->Posición de la Textura (desplazamiento de la textura, la camara no se mueve)
			
			
			//srcX, srcY Desplaza la propia textura, esto quiere decir que no se mueve a traves del espacio si no que se mueve dentro de si misma
			//Cuando repetimos la texura con "TextureWrap.MirroredRepeat" lo que estamos haciendo es repetirla de forma infinita pero dentro de donde está contenida
			
			batch.draw(layers.get(i), x, y, originX, originY, width, heigth, scaleX, scaleY, rotation, srcX, srcY,
					layers.get(i).getWidth(), layers.get(i).getHeight(), flipX, flipY);
		}
	}
}
