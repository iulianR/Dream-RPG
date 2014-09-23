package com.ttou.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ttou.gameobjects.Player;
import com.ttou.gameobjects.Player.Facing;
import com.ttou.gameobjects.Player.State;

public class GameRenderer {

	private static final float CAMERA_WIDTH = 800f;
	private static final float CAMERA_HEIGHT = 600f;

	private GameWorld myWorld;
	private OrthographicCamera cam;

	private SpriteBatch batch;

	// Game Objects
	private Player player;

	private Animation walkLeftAnimation;
	private Animation walkRightAnimation;

	// Game Assets
	private TextureRegion playerIdleLeft;
	private TextureRegion playerIdleRight;
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
	}

	public void initGameObjects() {
		player = myWorld.getPlayer();
	}

	public void initAssets() {
		TextureAtlas atlas = new TextureAtlas(
				Gdx.files.internal("data/player.txt"));
		playerIdleLeft = atlas.findRegion("player-01");
		playerIdleRight = new TextureRegion(playerIdleLeft);
		playerIdleRight.flip(true, false);
		TextureRegion[] walkLeftFrames = new TextureRegion[3];
		for (int i = 0; i < 3; i++) {
			walkLeftFrames[i] = atlas.findRegion("player-0" + (i + 1));
		}
		walkLeftAnimation = new Animation(0.2f, walkLeftFrames);

		TextureRegion[] walkRightFrames = new TextureRegion[3];
		for (int i = 0; i < 3; i++) {
			walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
			walkRightFrames[i].flip(true, false);
		}
		walkRightAnimation = new Animation(0.2f, walkRightFrames);
	}

	private void drawPlayer() {
		// batch.draw(playerSprite, player.getX(), player.getY(), 32, 32);
		playerFrame = player.getFacing().equals(Facing.LEFT) ?
				playerIdleLeft : 
				playerIdleRight;
		if (player.getState().equals(State.WALKING)) {
			playerFrame = player.getFacing().equals(Facing.LEFT) ?
					walkLeftAnimation.getKeyFrame(player.getStateTime(), true) :
					walkRightAnimation.getKeyFrame(player.getStateTime(), true);
		}
		batch.draw(playerFrame, player.getX(), player.getY(), Player.SIZE,
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
