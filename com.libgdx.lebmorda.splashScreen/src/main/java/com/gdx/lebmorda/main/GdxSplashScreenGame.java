package com.gdx.lebmorda.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.gdx.lebmorda.screens.SplashScreen;

public class GdxSplashScreenGame extends Game {
 
    private static long SPLASH_MINIMUM_MILLIS = 2000L;
 
    public GdxSplashScreenGame() { super(); }
 
    @Override
    public void create () {
        setScreen(new SplashScreen());
 
        //1.- Recogemos el tiempo actual el milisegundos y lo guardamos en una variable
        final long splash_start_time = System.currentTimeMillis();
        new Thread(new Runnable() {
               @Override
               public void run() {
 
                   Gdx.app.postRunnable(new Runnable() {
                       @Override
                       public void run() {
                    	   
                           // ... carga de datos
                           // ... carga de fuentes tipograficas
                           // ... carga de sonidos
                           // ... carga de imagenes
                           // ... carga de recursos de internacionalizacion
                           // ... otros
                    	   
                    	   //2.- Cargamos todos lo necesario y volvemos a recoger el tiempo actual. Pero esta vez
                    	   // se lo restamos al tiempo anterior. Esto no dá como resultado el tiempo que ha tardado e
                    	   // el sistema en cargar todos los datos
 
                           //3.- Si ha tardado menos que la constante SPLASH_MINIMUM_MILLIS cargamos la pantalla directamente
                    	   
                           long splash_elapsed_time = System.currentTimeMillis() - splash_start_time;
                           if (splash_elapsed_time < GdxSplashScreenGame.SPLASH_MINIMUM_MILLIS) {
                               Timer.schedule(
                                       new Timer.Task() {
                                           @Override
                                           public void run() {
                                        	   GdxSplashScreenGame.this.setScreen(new MainMenuScreen());
                                           }
                                       }, (float)(GdxSplashScreenGame.SPLASH_MINIMUM_MILLIS - splash_elapsed_time) / 1000f);
                           } else {
                        	   GdxSplashScreenGame.this.setScreen(new MainMenuScreen());
                           }
                       }
                   });
               }
            }).start();
    }
 
    @Override
    public void dispose() {
        // DISPOSE ALL RESOURCES
        getScreen().dispose();
        Gdx.app.exit();
    }
}
