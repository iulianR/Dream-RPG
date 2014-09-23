package com.ttou.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ttou.gameobjects.Player;
import com.ttou.gameobjects.Player.State;
import com.ttou.helpers.AssetLoader;

public class GameRenderer {

	private static final float CAMERA_WIDTH = 400f;
	private static final float CAMERA_HEIGHT = 300f;

	private GameWorld myWorld;
	private OrthographicCamera cam;

	private SpriteBatch batch;

	// Game Objects
	private Player player;

	// Animations
	private Animation playerWalkLeftAnimation;
	private Animation playerWalkRightAnimation;
	private Animation playerWalkUpAnimation;
	private Animation playerWalkDownAnimation;

	// Game Assets
	TextureAtlas playerAtlas;
	private TextureRegion playerIdleLeft;
	private TextureRegion playerIdleRight;
	private TextureRegion playerIdleUp;
	private TextureRegion playerIdleDown;
	private TextureRegion playerFrame;

	public GameRenderer(GameWorld world) {
		myWorld = world;

		cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		cam.position.set(0, 0, 0);
		cam.update();

		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);

		initGameObjects();
		initAssets();
		initAnimations();
	}

	public void initGameObjects() {
		player = myWorld.getPlayer();
	}

	public void initAssets() {
		playerAtlas = AssetLoader.playerAtlas;
		playerIdleLeft = AssetLoader.playerIdleLeft;
		playerIdleRight = AssetLoader.playerIdleRight;
		playerIdleUp = AssetLoader.playerIdleUp;
		playerIdleDown = AssetLoader.playerIdleDown;
	}
	
	public void initAnimations() {
		playerWalkLeftAnimation = AssetLoader.playerWalkLeftAnimation;
		playerWalkRightAnimation = AssetLoader.playerWalkRightAnimation;
		playerWalkUpAnimation = AssetLoader.playerWalkUpAnimation;
		playerWalkDownAnimation = AssetLoader.playerWalkDownAnimation;
	}

	private void drawPlayer() {
		switch (player.getFacing()) {
		case LEFT:
			playerFrame = playerIdleLeft;
			if (player.getState().equals(State.WALKING))
				playerFrame = playerWalkLeftAnimation.getKeyFrame(player.getStateTime(), true);
			break;
		case RIGHT:
			playerFrame = playerIdleRight;
			if (player.getState().equals(State.WALKING))
				playerFrame = playerWalkRightAnimation.getKeyFrame(player.getStateTime(), true);
			break;
		case UP:
			playerFrame = playerIdleUp;
			if (player.getState().equals(State.WALKING))
				playerFrame = playerWalkUpAnimation.getKeyFrame(player.getStateTime(), true);
			break;
		case DOWN:
			playerFrame = playerIdleDown;
			if (player.getState().equals(State.WALKING))
				playerFrame = playerWalkDownAnimation.getKeyFrame(player.getStateTime(), true);
			break;
		}
		batch.draw(playerFrame, player.getPosition().x, player.getPosition().y, Player.SIZE,
				Player.SIZE);
	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		drawPlayer();
		batch.end();
	}
}
