package com.emreozgenc.parallax;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ParallaxEffect {

    private static final float PARALLAX_SPEED = 50;
    private static final float PARALLAX_WIDTH = Gdx.graphics.getWidth();
    private static final float PARALLAX_HEIGHT = Gdx.graphics.getHeight();
    private static final int TEXTURE_COUNT = 4;
    private static final int DRAW_COUNT = 2;

    private SpriteBatch batch;
    private Texture[][] textures;
    private Vector2[][] positions;

    public ParallaxEffect() {
    	//Creamos un nuevo SpriteBatch
        batch = new SpriteBatch();
        
        /* Aspecto del array 4x2 (Columnas x Filas) de Textura
        [0][0][0][0]
        [0][0][0][0]
        */
        /* Aspecto del array 4x2 (Columnas x Filas) de Vector2
        [0;0][0;0][0;0][0;0]
        [0;0][0;0][0;0][0;0]
        */
        
        
        //Creamos un array bidimensional del tamaño 4x2 de tipo 'Texture' con las variables 'TEXTURE_COUNT'=4 y 'DRAW_COUNT'=2
        textures = new Texture[TEXTURE_COUNT][DRAW_COUNT];
        //Creamos un array bidimensional del tamaño 4x2 de tipo 'Vector2' con las variables 'TEXTURE_COUNT'=4 y 'DRAW_COUNT'=2
        positions = new Vector2[TEXTURE_COUNT][DRAW_COUNT];
        


        //Paso 1: 
	        //Se inicializan las texturas
	        /* 
        	[parallax_00.png]  [parallax_01.png]  [parallax_02.png]  [parallax_03.png]
	        		[0]  			[0]  				[0]  				[0] */
        
		        textures[0][0] = new Texture(Gdx.files.internal("parallax_00.png"));
		        textures[1][0] = new Texture(Gdx.files.internal("parallax_01.png"));
		        textures[2][0] = new Texture(Gdx.files.internal("parallax_02.png"));
		        textures[3][0] = new Texture(Gdx.files.internal("parallax_03.png"));
	        
        //Paso 2: 
		    //Se recorre el array bidimensional de texturas y se recorre 
	        //para informar la otra fila que falta (fila 1) blucle j y por eso 
		    //el segundo bucle empieza 'j' a partir de 1. El resultado es una array bidimensional
		    // en el que las texturas de las diferentes capas se repiten en las filas.
		        
		    //Para hacer el scroll horizontal utiliza dos texturas iguales (por eso se repiten). cada columna posee
		    //una capa del paralax.    
		        
	        /*
	         * Resultado:
	        [parallax_00.png]  [parallax_01.png]  [parallax_02.png]  [parallax_03.png]
	        [parallax_00.png]  [parallax_01.png]  [parallax_02.png]  [parallax_03.png] */
		        
				        for (int i = 0; i < TEXTURE_COUNT; i++) {
				            for (int j = 1; j < DRAW_COUNT; j++) {
				                textures[i][j] = textures[i][0];
				            }
				        }
     
		//Paso 3: 
		     //Se recorren las columnas 'TEXTURE_COUNT' y se mete en todas las columnas el valor siguiente		        
		        //Columna 0		Columna 1 		Columna 2		Columna 3       
		        //[0,0]			[0,0]			[0,0]			[0,0]
		        //[1280,0]		[1280,0]		[1280,0]		[1280,0]		 
      
				        for (int i = 0; i < TEXTURE_COUNT; i++) {
				            positions[i][0] = new Vector2(0, 0);
				            positions[i][1] = new Vector2(PARALLAX_WIDTH, 0);
				        }
				    }

    public void render() {
    	
    	//Por cada ciclo de render pinta todas las capas. Cuando termina se ejecuta el método update() que recoloca
    	// la posición de las texturas además de repetirlas (recolocandolas)
        batch.begin();
        for (int i = 0; i < TEXTURE_COUNT; i++) {
            for (int j = 0; j < DRAW_COUNT; j++) {
            	
            	float posicionTexturaX= positions[i][j].x;
            	float posicionTexturaY= positions[i][j].y;
            	Texture currentTexture =textures[i][j];

            	//Se recorren los arrays bidimensionales de texturas y posiciones 
                batch.draw(
                		//Tomamos la textura
                		currentTexture, 
                		
                		//Tomamos la .x (Vector2.x)
                		posicionTexturaX,
                		
                		//Tomamos la .y (Vector2.y)
                		posicionTexturaY, 
                		
                		PARALLAX_WIDTH + 5, 
                		PARALLAX_HEIGHT);
            }
        }
        batch.end();
    }
    
    public void update() {

        // control if texture out of bounds
		for (int i = 0; i < TEXTURE_COUNT; i++) {
			for (int j = 0; j < DRAW_COUNT; j++) {
				if (positions[i][j].x <= -PARALLAX_WIDTH) {
					positions[i][j].x = PARALLAX_WIDTH;
				}
			}
		}

        // update position of layers
        for (int i = 0; i < TEXTURE_COUNT; i++) {
            for (int j = 0; j < DRAW_COUNT; j++) {
                positions[i][j].x -= PARALLAX_SPEED * (i+1) * Gdx.graphics.getDeltaTime();

            }
        }
    }


    public void dispose() {
        for(int i=0 ; i < TEXTURE_COUNT; i++) {
            textures[i][0].dispose();
        }
        batch.dispose();
    }

}
