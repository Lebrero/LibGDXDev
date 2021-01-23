package com.gdx.lebmorda.loading;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.gdx.lebmorda.screen.LoadingScreen;

/**
 * @author Mats Svensson
 */
public class MainGame extends Game {

    /**
     * Holds all our assets
     */
    public AssetManager manager = new AssetManager();

    @Override
    public void create() {
        setScreen(new LoadingScreen(this));
    }
}
