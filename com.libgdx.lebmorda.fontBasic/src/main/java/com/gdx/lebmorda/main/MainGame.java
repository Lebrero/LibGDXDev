package com.gdx.lebmorda.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainGame extends ApplicationAdapter {
	  private Stage stage;

	
	@Override
	public void create () {
	    stage = new Stage(new ScreenViewport());
	    
	    //Se generan lineas en el fondo para ver mejor el texto
	    
	    //Guias de ayuda
	    int Help_Guides = 12;
	    //Tamaño de las filas
	    int row_height = Gdx.graphics.getWidth() / 12;
	    //Tamaño de las columnas
	    int col_width = Gdx.graphics.getWidth() / 12;
	    
	    //Añadimos las guias de ayuda como fondo
	    addBackgroundGuide(Help_Guides);


		// 1) BitmapFont: una representación gráfica de una fuente . La fuente de mapa
		// de bits no se escala muy bien (ya que estira la imagen), pero si va a una resolución
		// predefinida, funciona.
	    
			//1.1) Creamos nuestra fuente
				BitmapFont myFont = new BitmapFont(Gdx.files.internal("bitmapfont/Amble-Regular-26.fnt"));
			
			//1.2) Creamos el LabelStyle
				Label.LabelStyle label1Style = new Label.LabelStyle();
				
			//1.3) Le pasamos al LabelStyle la fuente y le decimos el color
				label1Style.font = myFont;
				label1Style.fontColor = Color.RED;
	
			//1.4) Creamos una nueva Label y le pasamos LabelStyle 
				Label label1 = new Label("Title (BitmapFont)", label1Style);
			
	        //1.5) Especificamos el tamaño de nuestra Label
				label1.setSize(Gdx.graphics.getWidth(), row_height);
	        //1.6) Especificamos la posición de nuestra Label
				label1.setPosition(0, Gdx.graphics.getHeight() - row_height * 2);
	        //1.7) Especificamos el alineamiento
				label1.setAlignment(Align.center);
			
			//1.8) Añadimos nuestra Label al Stage
				stage.addActor(label1);
			
		//2) Gdx FreeType : extensión que le permite generar su BitmapFont sobre la
		// marcha. Este es el más flexible cuando programar. Le ofrece muchos
		// parámetros para manipular el tamaño de fuente, color, sombra ... Sería su
		// enfoque preferido para manejar fuentes, pero ¡ Cuidado ! No puede usar esto
		// si apunta a HTML. Es por eso que no se incluye de manera predeterminada
		// cuando crea un proyecto LibGDX, la extensión debe incluirse marcando la
		// casilla FreeType dentro de la herramienta de creación de proyectos.//
			
			//Creamos el generador de fuente especificando la fuente
	        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("truetypefont/Amble-Light.ttf"));
	        //Creamos el parametrizador
	        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
	        
	        //Configuramos el parametrizador con los parámetros deseados
	        parameter.size = 30;
	        parameter.borderWidth = 1;
	        parameter.color = Color.YELLOW;
	        parameter.shadowOffsetX = 3;
	        parameter.shadowOffsetY = 3;
	        parameter.shadowColor = new Color(0, 0.5f, 0, 0.75f);
	        
	        //Generanmos nuestra BitmapFont
	        BitmapFont font24 = generator.generateFont(parameter); // font size 24 pixels
	        //Borramos de memoria el generador que no vamos a utilizar
	        generator.dispose();
	        //Creamos un LabelStyle con la fuente generada
	        Label.LabelStyle labelStyle = new Label.LabelStyle();
	        labelStyle.font = font24;
	     
	        //Creamos una equiqueta nueva con el estilo generado anteriormente
	        Label label2 = new Label("True Type Font (.ttf) - Gdx FreeType",labelStyle);
	        //Especificamos el tamaño de nuestra etiqueta
	        label2.setSize(Gdx.graphics.getWidth()/Help_Guides*5,row_height);
	        //Especificamos la posición de nuestra etiqueta
	        label2.setPosition(col_width*2,Gdx.graphics.getHeight()-row_height*4);
	        
			//Añadimos nuestra Label al Stage
	        stage.addActor(label2);
	     
		//3) Skin: Equivalente en LibGDX a un CSS en HTML. Crea tu Label usando el
		// estilo definido dentro de tu Skin. Descargue una máscara existente de sitios
		// como el repositorio gdx-skins de czyzby o cree la suya propia utilizando una
		// de las herramientas disponibles en ese sitio.
	        //Tomamos la skin de nuestro sistema
	        
	        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
	        //Creamos una etiqueta con esta skin
	        Label label3 = new Label("This is a Label (skin) on  5 columns ",mySkin,"black");
	        //Especificamos el tamaño de nuestra etiqueta
	        label3.setSize(Gdx.graphics.getWidth()/Help_Guides,row_height);
	        //Especificamos la posición de nuestra etiqueta
	        label3.setPosition(col_width*2,Gdx.graphics.getHeight()-row_height*6);
			//Añadimos nuestra Label al Stage
	        stage.addActor(label3);
	     
	        
		//3.1) Warp activado.Si es 'false', el texto solo se ajustará donde contenga nuevas líneas (\ n). El
		// tamaño preferido de la etiqueta será los límites del texto. Si es 'true', el
		// texto se ajustará al ancho de la etiqueta. El ancho preferido de la etiqueta
		// será 0, se espera que algo externo establezca el ancho de la etiqueta. El
		// ajuste no se producirá cuando los puntos suspensivos estén habilitados. El
		// valor predeterminado es falso. Cuando el ajuste está habilitado, la altura
		// preferida de la etiqueta depende del ancho de la etiqueta. En algunos casos,
		// el padre de la etiqueta necesitará un diseño dos veces: una para establecer
		// el ancho de la etiqueta y una segunda vez para ajustar a la nueva altura
		// preferida de la etiqueta.
	        Label label4 = new Label("This is a Label (skin) with a 5 columns width but WITH wrap",mySkin,"black");
	        label4.setSize(Gdx.graphics.getWidth()/Help_Guides*5,row_height);
	        label4.setPosition(col_width*2,Gdx.graphics.getHeight()-row_height*7);
	        label4.setWrap(true);
	        stage.addActor(label4);
	        
	   
	    }
	    public void addBackgroundGuide(int columns){
	        Texture texture = new Texture(Gdx.files.internal("background.jpg"));
	        texture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
	     
	        TextureRegion textureRegion = new TextureRegion(texture);
	        textureRegion.setRegion(0,0,texture.getWidth()*columns,texture.getWidth()*columns);
	        Image background = new Image(textureRegion);
	        background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getWidth());
	        background.setPosition(0,Gdx.graphics.getHeight()-background.getHeight());
	        stage.addActor(background);
	    }
	

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}
	
	@Override
	public void dispose () {
		stage.dispose();
	}
}
