package com.gdx.lebmorda.util;

import com.gdx.lebmorda.screens.AbstractScreen;
import com.gdx.lebmorda.screens.GameScreen;
import com.gdx.lebmorda.screens.LevelSelectScreen;
import com.gdx.lebmorda.screens.MainMenuScreen;

public enum ScreenEnum {
	
	MAIN_MENU {
		public AbstractScreen getScreen(Object... params) {
			return new MainMenuScreen();
		}
	},
	
	LEVEL_SELECT {
		public AbstractScreen getScreen(Object... params) {
			return new LevelSelectScreen();
		}
	},
	
	GAME {
		public AbstractScreen getScreen(Object... params) {
			return new GameScreen((Integer) params[0]);
		}
	};
	
	public abstract AbstractScreen getScreen(Object... params);
}
