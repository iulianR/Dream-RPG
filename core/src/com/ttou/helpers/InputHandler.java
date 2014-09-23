package com.ttou.helpers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.ttou.gameobjects.Player;
import com.ttou.gameworld.GameWorld;

public class InputHandler implements InputProcessor {
	private Player myPlayer;
	private GameWorld myWorld;

	public InputHandler(GameWorld myWorld) {
		this.myWorld = myWorld;
		this.myPlayer = myWorld.getPlayer();
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.LEFT:
			myPlayer.setLeftMove(true);
			break;
		case Keys.RIGHT:
			myPlayer.setRightMove(true);
			break;
		case Keys.UP:
			myPlayer.setUpMove(true);
			break;
		case Keys.DOWN:
			myPlayer.setDownMove(true);
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.LEFT:
			myPlayer.setLeftMove(false);
			break;
		case Keys.RIGHT:
			myPlayer.setRightMove(false);
			break;
		case Keys.UP:
			myPlayer.setUpMove(false);
			break;
		case Keys.DOWN:
			myPlayer.setDownMove(false);
			break;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
