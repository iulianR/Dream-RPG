package com.ttou.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ttou.gameobjects.Player;
import com.ttou.helpers.AssetLoader;

public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;

	private SpriteBatch batch;

	// Game Objects
	private Player player;

	// Game Assets
	private TextureRegion playerSprite;

	public GameRenderer(GameWorld world) {
		myWorld = world;

		cam = new OrthographicCamera();
		cam.setToOrtho(true, 400, 300);

		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);

		initGameObjects();
		initAssets();
	}

	public void initGameObjects() {
		player = myWorld.getPlayer();
	}

	public void initAssets() {
		playerSprite = AssetLoader.player;
	}

	private void drawPlayer() {
		batch.draw(playerSprite, player.getX(), player.getY(), 32, 32);
	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		drawPlayer();
		batch.end();
	}
}
