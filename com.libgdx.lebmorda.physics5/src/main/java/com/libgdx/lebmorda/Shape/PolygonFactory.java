package com.libgdx.lebmorda.Shape;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.libgdx.lebmorda.constantes.Constants;

public class PolygonFactory {
	private PolygonShape boundShape;
	public static HashMap<String, PolygonShape> polygonShape = new HashMap<String, PolygonShape>();
	public static JsonValue value;

	public PolygonFactory() {
		loadJson();
	}

	private void loadJson() {
		init();
	}

	public static PolygonShape cargarPolygon(String name) {
		PolygonShape polyShape = polygonShape.get(name);
		return polyShape;
	}

	public static void getPolygonShape(String name) {
		String nameMod = name + 1;
		if (!polygonShape.containsKey(nameMod))
			loadPolygonShape(name);
	}

	public static void loadPolygonShape(String name) {
		int j = 0, i = 0;

		JsonValue.JsonIterator iter = value.iterator();
		JsonValue temp, required = null;

		// Recorremos todo el Json y cuando el nodo "name" sea igual al que le pasamos
		// por parámetro lo recogemos
		while (iter.hasNext()) {
			temp = iter.next();
			if (temp.getString("name").equals(name)) {
				required = temp;
			}
		}
		if (required != null) {
			// Tomamos el nodo "vertices" del elemento en el que nos hemos parado
			iter = required.get("polygons").iterator();

			for (JsonValue val : iter) {
				iter = val.iterator();
				// Creamos un Vector2 del tamaño antes calculado
				Vector2[] verticesShape = new Vector2[val.size];
				i = 0;
				for (JsonValue vale : iter) {

					float x = vale.getFloat("x") * Constants.SCALE;
					float y = vale.getFloat("y") * Constants.SCALE;

					verticesShape[i] = new Vector2(x, y);
					i++;
				}
				// Creamos un PolygonShape a partir de el Vector2 Construido
				PolygonShape pShape = new PolygonShape();
				pShape.set(verticesShape);

				// Informamos el HashMap con el nombre y la forma
				polygonShape.put(name + j, pShape);
				j++;
			}
		}
	}

	private void init() {
		polygonShape = new HashMap<String, PolygonShape>();
		JsonReader reader = new JsonReader();
		value = reader.parse(Gdx.files.internal("models.json"));
	}

	public PolygonShape getBoundShape() {
		return boundShape;
	}

	public void setBoundShape(PolygonShape boundShape) {
		this.boundShape = boundShape;
	}

}
