package com.gdx.lebmorda.screen;

import java.text.DecimalFormat;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.gdx.lebmorda.loading.GameLabel;
import com.gdx.lebmorda.loading.LoadingBar;
import com.gdx.lebmorda.loading.MainGame;

/**
 * @author Mats Svensson
 */
public class LoadingScreen extends AbstractScreen {

	private Stage stage;

	private Image logo;
	private Image loadingFrame;
	private Image loadingBarHidden;
	private Image screenBg;
	private Image loadingBg;

	private GameLabel loading;
	private Skin skin;
	private Random rd;
	private DecimalFormat df;

	private float startX, endX;
	private float percent;

	private Actor loadingBar;

	public LoadingScreen(MainGame game) {
		super(game);
	}

	@Override
	public void show() {
		// Tell the manager to load assets for the loading screen
		game.manager.load("data/loading.pack", TextureAtlas.class);
		// Wait until they are finished loading
		game.manager.finishLoading();

		// Initialize the stage where we will place everything
		stage = new Stage();

		// Get our textureatlas from the manager
		TextureAtlas atlas = game.manager.get("data/loading.pack", TextureAtlas.class);

		// Grab the regions from the atlas and create some images
		logo = new Image(atlas.findRegion("libgdx-logo"));
		loadingFrame = new Image(atlas.findRegion("loading-frame"));
		loadingBarHidden = new Image(atlas.findRegion("loading-bar-hidden"));
		screenBg = new Image(atlas.findRegion("screen-bg"));
		loadingBg = new Image(atlas.findRegion("loading-frame-bg"));

		df = new DecimalFormat("0");
		rd = new Random();
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		loading = new GameLabel("Loading...", skin);
		loading.setPosition(320 - loading.getWidth() / 2, 180 - loading.getHeight() / 2);

		// Add the loading bar animation
		Animation anim = new Animation(0.05f, atlas.findRegions("loading-bar-anim"));
		anim.setPlayMode(PlayMode.LOOP_REVERSED);
		loadingBar = new LoadingBar(anim);

		// Or if you only need a static bar, you can do
		loadingBar = new Image(atlas.findRegion("loading-bar1"));

		// Add all the actors to the stage
		stage.addActor(screenBg);
		stage.addActor(loadingBar);
		stage.addActor(loadingBg);
		stage.addActor(loadingBarHidden);
		stage.addActor(loadingFrame);
		stage.addActor(logo);
		stage.addActor(loading);

		// Add everything to be loaded, for instance:
		// game.manager.load("data/assets1.pack", TextureAtlas.class);
		// game.manager.load("data/assets2.pack", TextureAtlas.class);
		// game.manager.load("data/assets3.pack", TextureAtlas.class);
	}

	@Override
	public void resize(int width, int height) {
		// Set our screen to always be XXX x 480 in size
		width = 480 * width / height;
		height = 480;
		// stage.setViewport(width , height, false);
		stage.getViewport().setScreenWidth(width);
		stage.getViewport().setScreenHeight(height);

		// Make the background fill the screen
		screenBg.setSize(width, height);

		// Place the logo in the middle of the screen and 100 px up
		logo.setX((width - logo.getWidth()) / 2);
		logo.setY((height - logo.getHeight()) / 2 + 100);

		// Place the loading frame in the middle of the screen
		loadingFrame.setX((stage.getWidth() - loadingFrame.getWidth()) / 2);
		loadingFrame.setY((stage.getHeight() - loadingFrame.getHeight()) / 2);

		// Place the loading bar at the same spot as the frame, adjusted a few px
		loadingBar.setX(loadingFrame.getX() + 15);
		loadingBar.setY(loadingFrame.getY() + 5);

		// Place the image that will hide the bar on top of the bar, adjusted a few px
		loadingBarHidden.setX(loadingBar.getX() + 35);
		loadingBarHidden.setY(loadingBar.getY() - 3);
		// The start position and how far to move the hidden loading bar
		startX = loadingBarHidden.getX();
		endX = 440;

		// The rest of the hidden bar
		loadingBg.setSize(450, 50);
		loadingBg.setX(loadingBarHidden.getX() + 30);
		loadingBg.setY(loadingBarHidden.getY() + 3);
	}

	@Override
	public void render(float delta) {
		// Clear the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		percent = Interpolation.bounceIn.apply(percent, game.manager.getProgress(), 0.05f);

		if (rd.nextBoolean() && rd.nextBoolean()) {
			loading.setText("Cargando... " + (df.format(percent * 100)) + "%");

			// Update positions (and size) to match the percentage
			loadingBarHidden.setX(startX + endX * percent);
			loadingBg.setX(loadingBarHidden.getX() + 30);
			loadingBg.setWidth(450 - 450 * percent);
			loadingBg.invalidate();
		}
		if (game.manager.update() && percent > 0.999f) {
			loading.setText("Haz click para continuar...");
			if (Gdx.input.isTouched()) {
				game.setScreen(new MainMenuScreen(game));
			}
		}
		// Show the loading screen
		stage.act();
		stage.draw();
	}

	@Override
	public void hide() {
		// Dispose the loading assets as we no longer need them
		game.manager.unload("data/loading.pack");
	}
}
