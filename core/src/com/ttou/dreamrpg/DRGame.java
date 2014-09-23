package com.ttou.dreamrpg;

import com.badlogic.gdx.Game;
import com.ttou.helpers.AssetLoader;
import com.ttou.screens.GameScreen;

public class DRGame extends Game {

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
