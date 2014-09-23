package com.ttou.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetLoader {
	
	public static Texture playerWalkSheet;
	
	public static void load () {
		playerWalkSheet = new Texture(Gdx.files.internal("data/player.png"));
	}
	
	public static void dispose() {
		playerWalkSheet.dispose();
	}
}
