package com.emreozgenc.box2dworks;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.emreozgenc.box2dworks.screens.Play1;

public class Box2DWorks extends Game {

	public OrthographicCamera cam;

	@Override
	public void create() {
		cam = new OrthographicCamera();

		// Here we are setting our camera
		cam.setToOrtho(false, Constants.V_WIDTH, Constants.V_HEIGHT);
		cam.update();

		this.setScreen(new Play1(this));
	}
}
