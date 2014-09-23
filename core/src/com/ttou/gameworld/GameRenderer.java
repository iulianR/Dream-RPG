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

	private static final float CAMERA_WIDTH = 400f;
	private static final float CAMERA_HEIGHT = 300f;

	private GameWorld myWorld;
	private OrthographicCamera cam;

	private SpriteBatch batch;

	// Game Objects
	private Player player;

	private Animation walkLeftAnimation;
	private Animation walkRightAnimation;
	private Animation walkUpAnimation;
	private Animation walkDownAnimation;

	// Game Assets
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
	}

	public void initGameObjects() {
		player = myWorld.getPlayer();
	}

	public void initAssets() {
		TextureAtlas atlas = new TextureAtlas(
				Gdx.files.internal("data/player.txt"));
		playerIdleRight = atlas.findRegion("player_side_01");
		playerIdleLeft = new TextureRegion(playerIdleRight);
		playerIdleLeft.flip(true, false);
		playerIdleUp = atlas.findRegion("player_up_01");
		playerIdleDown = atlas.findRegion("player_down_01");

		// Right animation
		TextureRegion[] walkRightFrames = new TextureRegion[11];
		for (int i = 0; i < 11; i++) {
			if (i < 9)
				walkRightFrames[i] = atlas
						.findRegion("player_side_0" + (i + 1));
			else
				walkRightFrames[i] = atlas.findRegion("player_side_" + (i + 1));
		}
		walkRightAnimation = new Animation(0.066f, walkRightFrames);

		// Left animation
		TextureRegion[] walkLeftFrames = new TextureRegion[11];
		for (int i = 0; i < 11; i++) {
			walkLeftFrames[i] = new TextureRegion(walkRightFrames[i]);
			walkLeftFrames[i].flip(true, false);
		}
		walkLeftAnimation = new Animation(0.066f, walkLeftFrames);

		// Up animation
		TextureRegion[] walkUpFrames = new TextureRegion[11];
		for (int i = 0; i < 11; i++) {
			if (i < 9)
				walkUpFrames[i] = atlas.findRegion("player_up_0" + (i + 1));
			else
				walkUpFrames[i] = atlas.findRegion("player_up_" + (i + 1));
		}
		walkUpAnimation = new Animation(0.066f, walkUpFrames);

		// Down animation
		TextureRegion[] walkDownFrames = new TextureRegion[11];
		for (int i = 0; i < 11; i++) {
			if (i < 9)
				walkDownFrames[i] = atlas.findRegion("player_down_0" + (i + 1));
			else
				walkDownFrames[i] = atlas.findRegion("player_down_" + (i + 1));
		}
		walkDownAnimation = new Animation(0.066f, walkDownFrames);
	}

	private void drawPlayer() {
		switch (player.getFacing()) {
		case LEFT:
			playerFrame = playerIdleLeft;
			if (player.getState().equals(State.WALKING))
				playerFrame = walkLeftAnimation.getKeyFrame(player.getStateTime(), true);
			break;
		case RIGHT:
			playerFrame = playerIdleRight;
			if (player.getState().equals(State.WALKING))
				playerFrame = walkRightAnimation.getKeyFrame(player.getStateTime(), true);
			break;
		case UP:
			playerFrame = playerIdleUp;
			if (player.getState().equals(State.WALKING))
				playerFrame = walkUpAnimation.getKeyFrame(player.getStateTime(), true);
			break;
		case DOWN:
			playerFrame = playerIdleDown;
			if (player.getState().equals(State.WALKING))
				playerFrame = walkDownAnimation.getKeyFrame(player.getStateTime(), true);
			break;
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
