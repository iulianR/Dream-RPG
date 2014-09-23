package com.ttou.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.ttou.gameobjects.Player;
import com.ttou.gameobjects.Player.Facing;
import com.ttou.gameobjects.Player.State;

public class GameWorld {

	private Player player;

	private GameRenderer renderer;

	public GameWorld() {
		// New player at X, Y coordinates
		player = new Player(30, 30);
	}

	public void update(float delta) {
		player.setState(State.IDLE);
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			player.setFacing(Facing.UP);
			player.setState(State.WALKING);
			player.setUpMove(true);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			player.setFacing(Facing.DOWN);
			player.setState(State.WALKING);
			player.setDownMove(true);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			player.setFacing(Facing.LEFT);
			player.setState(State.WALKING);
			player.setLeftMove(true);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			player.setFacing(Facing.RIGHT);
			player.setState(State.WALKING);
			player.setRightMove(true);
		}
		player.update(delta);
		player.setUpMove(false);
		player.setDownMove(false);
		player.setLeftMove(false);
		player.setRightMove(false);

	}

	public Player getPlayer() {
		return player;
	}

	public void setRenderer(GameRenderer renderer) {
		this.renderer = renderer;
	}
}
