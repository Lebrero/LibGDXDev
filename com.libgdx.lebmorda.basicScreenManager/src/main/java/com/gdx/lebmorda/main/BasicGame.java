package com.gdx.lebmorda.main;

import com.badlogic.gdx.Game;
import com.gdx.lebmorda.util.ScreenEnum;
import com.gdx.lebmorda.util.ScreenManager;

public class BasicGame extends Game {

	@Override
	public void create() {
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
	}
}
