package com.gdx.lebmorda.box2D.Bodies;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class B2dContactListener implements ContactListener {
	@Override
	public void beginContact(Contact contact) {
		String classA = contact.getFixtureA().getBody().getUserData().getClass().getName();
		String classB = contact.getFixtureB().getBody().getUserData().getClass().getName();

		// Gdx.app.debug("begin Contact","between: "+classA+" and "+ classB);
		if (classA.equalsIgnoreCase("com.gdx.lebmorda.box2D.Bodies.WindowsFrame")
				&& classB.equalsIgnoreCase("com.gdx.lebmorda.box2D.Bodies.Ball")) {
			Ball ball = (Ball) (contact.getFixtureB().getBody().getUserData());
			BallGenerator.getInstance().explode(ball);

		} else if (classB.equalsIgnoreCase("com.gdx.lebmorda.box2D.Bodies.WindowsFrame")
				&& classA.equalsIgnoreCase("com.gdx.lebmorda.box2D.Bodies.Ball")) {
			Ball ball = (Ball) (contact.getFixtureA().getBody().getUserData());
			BallGenerator.getInstance().explode(ball);
		}

	}

	@Override
	public void endContact(Contact contact) {

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}
}
