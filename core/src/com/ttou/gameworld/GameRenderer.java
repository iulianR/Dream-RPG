package com.ttou.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ttou.gameobjects.Player;
import com.ttou.gameobjects.Player.State;
import com.ttou.helpers.AssetLoader;

public class GameRenderer {

	private static final float CAMERA_WIDTH = 240f;
	private static final float CAMERA_HEIGHT = 240f;

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private Viewport viewport;
	private TiledMapRenderer tiledMapRenderer;

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
	
	private TiledMap testMap;
	
	private float width;
	private float height;
	
	private int mapPixelWidth;
	private int mapPixelHeight;

	public GameRenderer(GameWorld world) {
		myWorld = world;

		width = Gdx.graphics.getWidth() / 3f;
		height = Gdx.graphics.getHeight() / 3f;

		cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		cam.position.set(width, height, 0);
		cam.update();
		
		viewport = new FitViewport(width, height, cam);

		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);

		initGameObjects();
		initAssets();
		initAnimations();
		
		tiledMapRenderer = new OrthogonalTiledMapRenderer(testMap);
		
		// Map dimensions - rewrite this
		MapProperties prop = testMap.getProperties();

		int mapWidth = prop.get("width", Integer.class);
		int mapHeight = prop.get("height", Integer.class);
		int tilePixelWidth = prop.get("tilewidth", Integer.class);
		int tilePixelHeight = prop.get("tileheight", Integer.class);

		mapPixelWidth = mapWidth * tilePixelWidth;
		mapPixelHeight = mapHeight * tilePixelHeight;
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
		testMap = AssetLoader.testMap;
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
	
	public void moveCamera(float x, float y) {
		//System.out.println(x + " " + y);
		//System.out.println(y + " " + player.getPosition().y);
		// I have no idea why this numbers work. Keep them here
		// TODO: Change this
		if (x < width / 2) x = width / 2;
		if (y < height / 2) y = height / 2;
		if (x > mapPixelWidth - width / 2) x = mapPixelWidth - width / 2;
		if (y > mapPixelHeight - height / 2) y = mapPixelHeight - height / 2;
		cam.position.set(x, y, 0);
		cam.update();
	}

	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		moveCamera(player.getPosition().x, player.getPosition().y);
		batch.setProjectionMatrix(cam.combined);
		tiledMapRenderer.setView(cam);
		tiledMapRenderer.render();
		
		batch.begin();
			drawPlayer();
		batch.end();
	}
	
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
	
	public OrthographicCamera getCamera() {
		return cam;
	}
}
