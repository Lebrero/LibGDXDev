package com.gdx.lebmorda.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.libgdx.lebmorda.utils.Imagen;
import com.libgdx.lebmorda.utils.Recursos;
import com.libgdx.lebmorda.utils.Render;

public class MenuScreen implements Screen {

	Imagen fondo;
	SpriteBatch b;
	boolean fadeInTerminado = false, termina = false;
	float a = 0;
	float contTiempo = 0, tiempoEspera = 5;
	float contTiempoTermina = 0, tiempoTermina = 5;

	@Override
	public void show() {
		fondo = new Imagen(Recursos.SCREEN_1);
		b = Render.batch;
		fondo.setTrasparencia(0.5f);
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0, 1);
		procesarFade();
		Render.batch.begin();
		fondo.dibujar();
		Render.batch.end();
	}

	private void procesarFade() {
		if (!fadeInTerminado) {
			a += 0.01f;
			if (a > 1) {
				a = 1;
				fadeInTerminado = true;
			}
		} else {
			contTiempo += 0.05f;
			if (contTiempo > tiempoEspera) {
				a -= 0.01f;
				if (a < 0) {
					a = 0;
					termina = true;
				}
			}
		}
		fondo.setTrasparencia(a);

		if (termina) {
			contTiempoTermina += 0.04f;
			if (contTiempoTermina > tiempoTermina) {
				System.out.println("CAMBIO PANTALLA");
				Render.app.setScreen(new MenuScreen());
				
			}
		}

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

}