package com.gdx.lebmorda.screens;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdx.lebmorda.main.BasicGame;
import com.libgdx.lebmorda.Shape.PolygonFactory;

public class BasicScreen extends ScreenAdapter {
	

	final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();

	private BasicGame game;
	private TextureAtlas textureAtlas;

	private OrthographicCamera gamecam;
	private Viewport gameport;

	private World world;

	//Varaibles estáticas propias del World
	static final float STEP_TIME = 1f / 60f;
	static final int VELOCITY_ITERATIONS = 6;
	static final int POSITION_ITERATIONS = 2;
	static final float SCALE = 0.64f;
	static final int COUNT = 20;

	//Contiene un array de nombre de bodies
	private Body[] fruitBodies = new Body[COUNT];
	
	//Contiene un array de nombres de texturas
	private String[] names = new String[COUNT];

	private Body ground;
	
	//Body genérico
	private Body body;

	private Box2DDebugRenderer debugRenderer;
	float accumulator = 0;

	private PolygonFactory polyFactory;

	public BasicScreen(BasicGame game) {
		this.game = game;

	}

	@Override
	public void show() {

		// Inicializamos Box2D
		Box2D.init();
		// Iniciamos la factoria
		polyFactory = new PolygonFactory();
		// Instanciamos el debug
		debugRenderer = new Box2DDebugRenderer();
		// Insanciamos el World
		world = new World(new Vector2(0, -98f), true);
		// Instanciamos la camara
		gamecam = new OrthographicCamera();
		// Instanciamos el viewPort
		gameport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gamecam);
		// Instanciamos el texture Atllas
		textureAtlas = new TextureAtlas("sprites.txt");

		// Añadimos los sprites
		addSprites();

		// Generamos las frutas
		generateFruit();
	}

	private BodyDef createBodyDef() {
		BodyDef def = new BodyDef();
		def.position.set(0, 0);
		def.type = BodyDef.BodyType.DynamicBody;
		return def;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.57f, 0.77f, 0.85f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Llamamos stepWorld() para hacer funcionar nuestro World en Box 2D
		stepWorld(delta);

		// Dibujamos los sprites
		game.batch.begin();

		for (int i = 0; i < fruitBodies.length; i++) {

			Body body = fruitBodies[i];
			String name = names[i];

			Vector2 position = body.getPosition();
			float degrees = (float) Math.toDegrees(body.getAngle());

			// Dibujar frutas
			drawSprite(name, position.x, position.y, degrees);
		}

		game.batch.end();

		// Elemento de debug Box2D
		debugRenderer.render(world, gamecam.combined);

	}

	/**
	 * Método encargado de generar frutas de forma aleatoria
	 */
	private void generateFruit() {
		
		String[] fruitNames = new String[] { "banana", "orange", "cherries", "crate" };

		for (int i = 0; i < fruitNames.length; i++) {
			
			// Creamos la forma
			PolygonFactory.getPolygonShape(fruitNames[i]);
		}

		Random random = new Random();
		for (int i = 0; i < fruitBodies.length; i++) {
			String name = fruitNames[random.nextInt(fruitNames.length)];

			float x = random.nextFloat() * 640;
			float y = random.nextFloat() * 640 + 640;

			names[i] = name;
			fruitBodies[i] = createBody(name, x, y, 0);
		}
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
	 * Método que sirve para crear Bodies
	 * 
	 * @param name     Nombre del body
	 * @param x        posición del body
	 * @param y        posición del body
	 * @param rotation rotación del body
	 * @return objeto de tipo Body
	 */
	private Body createBody(String name, float x, float y, float rotation) {
		Body body = createBodyFactory(name);
		body.setTransform(x, y, rotation);
		return body;
	}

	private Body createBodyFactory(String name) {
		// Creamos el bodyDef
		BodyDef bodyDef = createBodyDef();
		body = world.createBody(bodyDef);

		HashMap<String, PolygonShape> shapeList = PolygonFactory.polygonShape;

		for (int i = 0; i < shapeList.size(); i++) {
			PolygonShape polyTmp = shapeList.get(name + i);
			if (polyTmp != null) {
				// Creamos el fixtureDef
				FixtureDef fixtureDef = new FixtureDef();
				fixtureDef.shape = polyTmp;
				fixtureDef.density = 1f;
				// Creamos el fixture
				body.createFixture(fixtureDef);
			}

		}
		return body;
	}

	/**
	 * Añade Sprites a nuestra lista. Lo toma de un texture Atlas y lo mete en una
	 * lista de tipo HashMap<String, Sprite>
	 */

	private void addSprites() {
		Array<AtlasRegion> regions = textureAtlas.getRegions();
		for (AtlasRegion region : regions) {

			Sprite sprite = textureAtlas.createSprite(region.name);
			float width = sprite.getWidth() * SCALE;
			float height = sprite.getHeight() * SCALE;

			sprite.setSize(width, height);
			sprite.setOrigin(0, 0);

			sprites.put(region.name, sprite);

		}
	}

	/**
	 * Método que crea un suelo basándose en la pantalla
	 */
	private void createGround() {
		if (ground != null)
			world.destroyBody(ground);

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.friction = 1;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(gamecam.viewportWidth, 1);

		fixtureDef.shape = shape;

		ground = world.createBody(bodyDef);
		ground.createFixture(fixtureDef);
		ground.setTransform(0, 0, 0);

		shape.dispose();
	}

	/**
	 * Método que dibuja un sprite le pasamos el nombre y la posicion
	 * 
	 * @param name nombre del Sprite
	 * @param x    posición X
	 * @param y    posición Y
	 */
	private void drawSprite(String name, float x, float y, float degrees) {
		Sprite sprite = sprites.get(name);
		sprite.setPosition(x, y);
		sprite.setRotation(degrees);
		sprite.draw(game.batch);
	}

	@Override
	public void dispose() {
		textureAtlas.dispose();
		sprites.clear();
		world.dispose();
		debugRenderer.dispose();

	}

	@Override
	public void resize(int width, int height) {
		gameport.update(width, height, true);
		game.batch.setProjectionMatrix(gamecam.combined);
		createGround();

	}

}
