package com.libgdx.lebmorda.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.gdx.lebmorda.main.BasicGame;

public class ScreenEffects {

	
/**
 * Switches to a new screen while handling fading buffer Fade transition

 * @param stage
 * @param game
 * @param screen
 * @param fadeOut
 */
	public static void setFadeScreen(final Stage stage, final BasicGame game, final Screen screen, float fadeOut) {
		// fade out animation
		stage.addAction(Actions.sequence(Actions.fadeOut(fadeOut), Actions.run(new Runnable() {
			@Override
			public void run() {
				game.setScreen(screen);
			}
		})));
	}

	/**
	 * Switches to a new screen while handling fading buffer Slide transition either
	 * to the left or right
	 * 
	 * @param stage
	 * @param game
	 * @param screen
	 * @param right
	 * @param speed
	 */
	public static void setSlideScreen(Stage stage, BasicGame game, Screen screen, boolean right, float speed) {

		// slide animation
		stage.addAction(
				Actions.sequence(Actions.moveBy(right ? -Gdx.graphics.getWidth() : Gdx.graphics.getWidth(), 0, speed),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								game.setScreen(screen);
							}
						})));
	}

}
