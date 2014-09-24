package com.ttou.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class AssetLoader {

	public static TextureAtlas playerAtlas;

	public static Animation playerWalkLeftAnimation;
	public static Animation playerWalkRightAnimation;
	public static Animation playerWalkUpAnimation;
	public static Animation playerWalkDownAnimation;
	
	public static TextureRegion playerIdleLeft;
	public static TextureRegion playerIdleRight;
	public static TextureRegion playerIdleUp;
	public static TextureRegion playerIdleDown;
	public static TextureRegion playerFrame;
	
	public static TiledMapRenderer tiledMapRenderer;
	
	public static TiledMap testMap;
	
	public static void load() {
		playerAtlas = new TextureAtlas(Gdx.files.internal("data/player.txt"));
		playerIdleRight = playerAtlas.findRegion("player_side_01");
		playerIdleLeft = new TextureRegion(playerIdleRight);
		playerIdleLeft.flip(true, false);
		playerIdleUp = playerAtlas.findRegion("player_up_01");
		playerIdleDown = playerAtlas.findRegion("player_down_01");

		// Right animation
		TextureRegion[] walkRightFrames = new TextureRegion[11];
		for (int i = 0; i < 11; i++) {
			if (i < 9)
				walkRightFrames[i] = playerAtlas
						.findRegion("player_side_0" + (i + 1));
			else
				walkRightFrames[i] = playerAtlas.findRegion("player_side_" + (i + 1));
		}
		playerWalkRightAnimation = new Animation(0.066f, walkRightFrames);

		// Left animation
		TextureRegion[] walkLeftFrames = new TextureRegion[11];
		for (int i = 0; i < 11; i++) {
			walkLeftFrames[i] = new TextureRegion(walkRightFrames[i]);
			walkLeftFrames[i].flip(true, false);
		}
		playerWalkLeftAnimation = new Animation(0.066f, walkLeftFrames);

		// Up animation
		TextureRegion[] walkUpFrames = new TextureRegion[11];
		for (int i = 0; i < 11; i++) {
			if (i < 9)
				walkUpFrames[i] = playerAtlas.findRegion("player_up_0" + (i + 1));
			else
				walkUpFrames[i] = playerAtlas.findRegion("player_up_" + (i + 1));
		}
		playerWalkUpAnimation = new Animation(0.066f, walkUpFrames);

		// Down animation
		TextureRegion[] walkDownFrames = new TextureRegion[11];
		for (int i = 0; i < 11; i++) {
			if (i < 9)
				walkDownFrames[i] = playerAtlas.findRegion("player_down_0" + (i + 1));
			else
				walkDownFrames[i] = playerAtlas.findRegion("player_down_" + (i + 1));
		}
		playerWalkDownAnimation = new Animation(0.066f, walkDownFrames);
		
		// Maps
		testMap = new TmxMapLoader().load("data/testMap.tmx");
	}

	public static void dispose() {
		playerAtlas.dispose();
		testMap.dispose();
	}
}
