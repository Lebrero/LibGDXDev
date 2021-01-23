package com.gdx.lebmorda.screen;

import com.badlogic.gdx.Screen;
import com.gdx.lebmorda.loading.MainGame;

/**
 * @author Mats Svensson
 */
public abstract class AbstractScreen implements Screen {

    protected MainGame game;

    public AbstractScreen(MainGame game) {
        this.game = game;
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
