package com.gdx.lebmorda.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainGame extends ApplicationAdapter {

	private Stage stage;
	private static int lapsCount;
	private Label lapsLabel;
	private Group lapsLabelContainer;

	@Override
	public void create() {
		lapsCount = 3;
		stage = new Stage(new ScreenViewport());
		Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
	    Texture texture = new Texture("image.jpg");

		int X_left = Gdx.graphics.getWidth() / 3 - texture.getWidth() / 2;
		int X_right = Gdx.graphics.getWidth() * 2 / 3 - texture.getWidth() / 2;
		int Y_top = Gdx.graphics.getHeight() * 2 / 3 - texture.getHeight() / 2;
		int Y_bottom = Gdx.graphics.getHeight() / 3 - texture.getHeight() / 2;

		/*Cargamos la imagen, le damos posicion y configuramos su origen*/
		Image image1 = new Image(texture);
		image1.setPosition(X_left, Y_top);
		image1.setOrigin(image1.getWidth() / 2, image1.getHeight() / 2);
		stage.addActor(image1);
		
	    //1) Creamos acción paralela (Se añaden acciones y se ejecutan al mismo tiempo)
		ParallelAction topLeftRightParallelAction = new ParallelAction();
		topLeftRightParallelAction.addAction(Actions.moveTo(X_right, Y_top, 1, Interpolation.exp5Out));
		topLeftRightParallelAction.addAction(Actions.scaleTo(2, 2, 1, Interpolation.exp5Out));
		
	    //2) Creamos acción unica (Única acción)
		MoveToAction moveBottomRightAction = new MoveToAction();
		moveBottomRightAction.setPosition(X_right, Y_bottom);
		moveBottomRightAction.setDuration(1);
		moveBottomRightAction.setInterpolation(Interpolation.smooth);

		//3) Accion Runnable (Accion que se ejecuta al mismo tiempo y de forma independiente al resto de las acciones)
		RunnableAction updateLapCountAction = new RunnableAction();
		updateLapCountAction.setRunnable(new Runnable() {
			@Override
			public void run() {
				updateLapsCount();
			}
		});
		
		//4)Creamos acción paralela  (Se añaden acciones y se ejecutan al mismo tiempo)
		ParallelAction bottomLeftRightParallelAction = new ParallelAction();
		bottomLeftRightParallelAction.addAction(Actions.moveTo(X_left, Y_bottom, 1, Interpolation.sineOut));
		bottomLeftRightParallelAction.addAction(Actions.scaleTo(1, 1, 1));
		
		//5)Creamos acción paralela  (Se añaden acciones y se ejecutan al mismo tiempo)
		ParallelAction leftBottomTopParallelAction = new ParallelAction();
		leftBottomTopParallelAction.addAction(Actions.moveTo(X_left, Y_top, 1, Interpolation.swingOut));
		leftBottomTopParallelAction.addAction(Actions.rotateBy(90, 1));
		
		//6) Añadimos todas las acciones y las ejecutamos de forma secuencial.
		SequenceAction overallSequence = new SequenceAction();
		overallSequence.addAction(updateLapCountAction);
		overallSequence.addAction(topLeftRightParallelAction);
		overallSequence.addAction(moveBottomRightAction);
		overallSequence.addAction(bottomLeftRightParallelAction);
		overallSequence.addAction(leftBottomTopParallelAction);

		//7) Accion repetitiva
		RepeatAction loopNbrAction = new RepeatAction();
		loopNbrAction.setCount(lapsCount);
		loopNbrAction.setAction(overallSequence);

		lapsLabel = new Label(" Loop :", skin, "big-black");
		lapsLabel.setPosition(0, 0);
		lapsLabel.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		lapsLabel.setAlignment(Align.center);

		lapsLabelContainer = new Group();
		lapsLabelContainer.addActor(lapsLabel);
		lapsLabelContainer.setOrigin(lapsLabel.getWidth() / 2, lapsLabel.getHeight() / 2);

		stage.addActor(lapsLabelContainer);

		RunnableAction completedAction = new RunnableAction();
		completedAction.setRunnable(new Runnable() {
			@Override
			public void run() {
				finished();
			}
		});

		image1.addAction(Actions.sequence(loopNbrAction, completedAction));

	}

	private void updateLapsCount() {
		lapsCount--;
		lapsLabelContainer.setScale(0);
		SequenceAction FadingSequenceAction = new SequenceAction();
		FadingSequenceAction.addAction(Actions.fadeIn(1));

		ParallelAction parallelAction = new ParallelAction();

		lapsLabel.setText(" Laps : " + (lapsCount + 1));
		FadingSequenceAction.addAction(Actions.fadeOut(2));
		parallelAction.addAction(Actions.scaleBy(5, 5, 4));

		parallelAction.addAction(FadingSequenceAction);
		lapsLabelContainer.addAction(parallelAction);
	}

	private void finished() {
		lapsLabelContainer.setScale(0);
		SequenceAction FadingSequenceAction = new SequenceAction();
		FadingSequenceAction.addAction(Actions.fadeIn(1));

		ParallelAction parallelAction = new ParallelAction();

		lapsLabel.setText(" Finished!");
		// FadingSequenceAction.addAction(Actions.fadeOut(10));
		parallelAction.addAction(Actions.rotateBy(360, 2));
		parallelAction.addAction(Actions.scaleBy((float) 1.2, (float) 1.2, 3, Interpolation.bounce));

		parallelAction.addAction(FadingSequenceAction);
		lapsLabelContainer.addAction(parallelAction);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}
