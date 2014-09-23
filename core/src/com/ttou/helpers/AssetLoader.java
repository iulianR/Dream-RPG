package com.ttou.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public static Texture texture;
	public static TextureRegion player;
	
	public static void load () {
		texture = new Texture(Gdx.files.internal("data/player.png"));
		player = new TextureRegion(texture, 0, 0, 32, 32);
		player.flip(false, true);
	}
	
	public static void dispose() {
		texture.dispose();
	}
}
