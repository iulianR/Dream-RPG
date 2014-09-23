package com.ttou.gameworld;

import com.ttou.gameobjects.Player;

public class GameWorld {

	private Player player;

	private GameRenderer renderer;

	public GameWorld() {
		// New player at X, Y coordinates
		player = new Player(30, 30);
	}

	public void update(float delta) {
		player.update(delta);
	}

	public Player getPlayer() {
		return player;
	}

	public void setRenderer(GameRenderer renderer) {
		this.renderer = renderer;
	}
}
