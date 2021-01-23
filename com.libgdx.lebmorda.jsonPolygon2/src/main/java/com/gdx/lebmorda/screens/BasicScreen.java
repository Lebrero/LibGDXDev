package com.gdx.lebmorda.screens;

import java.io.FileNotFoundException;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdx.lebmorda.main.BasicGame;

public class BasicScreen extends ScreenAdapter {
	private final Texture ENEMY_TEXTURE = new Texture("texture_0.png");
	private OrthographicCamera gamecam;
	private Viewport gameport;
	private BasicGame game;
	private static JsonValue value;
	private static HashMap<String, Polygon> polygons;
	private Box2DDebugRenderer rendered;
	private World world;
	private Polygon bounds;
	private Body bodyPolygon;
	private Fixture fixturePolygon;
	private PolygonShape shapePoly;
	private float accumulator = 0;

	static final float STEP_TIME = 1f / 60f;
	static final int VELOCITY_ITERATIONS = 6;
	static final int POSITION_ITERATIONS = 2;
	static final float SCALE = 0.64f;
	static final int COUNT = 10;

	public BasicScreen(BasicGame game) {
		this.game = game;
	}

	@Override
	public void show() {
		try {
			loadJson();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void loadJson() throws FileNotFoundException {

		init();
		bounds = getPolygon("Enemy", ENEMY_TEXTURE.getWidth(), ENEMY_TEXTURE.getHeight());
	
	}

	private void init() throws FileNotFoundException {
		
		//Crear PolygonShape a partir de bounds; 
		shapePoly= new PolygonShape();
		shapePoly.set(bounds.getVertices());	

		BodyDef defPolygon = createBodyDef();
		bodyPolygon= world.createBody(defPolygon);
		bodyPolygon.createFixture(shapePoly, 1);
		shapePoly.dispose();
		
		polygons = new HashMap<String, Polygon>();
		JsonReader reader = new JsonReader();
		
		value = reader.parse(Gdx.files.internal("models.json"));

	}

	private BodyDef createBodyDef() {
		BodyDef def = new BodyDef();
		def.position.set(0, 0);
		def.type=BodyDef.BodyType.DynamicBody;
		return def;
	}

	/**
	 * Retrieves a polygon from the polygon cache, with a specified width and height
	 *
	 * @param name   the polygon to retrieve
	 * @param width  the width of the polygon
	 * @param height the height of the polygon
	 *
	 * @return the loaded polygon
	 */
	public static Polygon getPolygon(String name, float width, float height) {
		if (!polygons.containsKey(name))
			loadPolygon(name);
		Polygon temp = polygons.get(name);
		float[] tempVertices = temp.getVertices();
		float[] returnVertices = new float[tempVertices.length];
		for (int i = 0; i < tempVertices.length; i++) {
			if (i % 2 == 0)
				returnVertices[i] = tempVertices[i] * width;
			else
				returnVertices[i] = tempVertices[i] * height;
		}
		Polygon ret = new Polygon(returnVertices);
		ret.setOrigin(width / 2, height / 2);
		return ret;
	}

	/**
	 * Método stepWorld
	 * 
	 * @param delta
	 */
	private void stepWorld(float delta) {
		accumulator += Math.min(delta, 0.25f);
		if (accumulator >= STEP_TIME) {
			accumulator -= STEP_TIME;
			world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
		}
	}

	/**
	 * Loads a new polygon into the polygon cache
	 *
	 * @param name the name of the polygon to load
	 */
	public static void loadPolygon(String name) {
		int len = 0, i = 0;
		Polygon p;
		JsonValue.JsonIterator iter = value.iterator();
		JsonValue temp, required = null;

		// Get the polygon of the required name from json
		while (iter.hasNext()) {
			temp = iter.next();
			if (temp.getString("name").equals(name))
				required = temp;
		}

		if (required == null) {
			polygons.put(name, PolygonHelper.getPolygon(0, 0, 50, 50));
			return;
		}

		iter = required.get("vertices").iterator();

		for (JsonValue vertice : iter) {
			len += 2;
		}

		iter = required.get("vertices").iterator();

		float[] vertices = new float[len];
		for (JsonValue val : iter) {
			vertices[i] = (float) val.getDouble("x");
			vertices[i + 1] = (float) val.getDouble("y");
			i += 2;
		}
		p = new Polygon(vertices);
		p.setOrigin(required.get("origin").getFloat("x"), required.get("origin").getFloat("y"));
		polygons.put(name, p);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.step(delta, 6, 2);
		gamecam.update();
		rendered.render(world, gamecam.combined);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void dispose() {
		
		world.destroyBody(bodyPolygon);
		world.dispose();

	}

}
