package com.ttou.helpers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.ttou.gameobjects.Player;
import com.ttou.gameobjects.Player.Facing;
import com.ttou.gameobjects.Player.State;
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
			myPlayer.setFacing(Facing.LEFT);;
			myPlayer.setState(State.WALKING);
			myPlayer.setLeftMove(true);
			break;
		case Keys.RIGHT:
			myPlayer.setFacing(Facing.RIGHT);
			myPlayer.setState(State.WALKING);
			myPlayer.setRightMove(true);
			break;
		case Keys.UP:
			myPlayer.setState(State.WALKING);
			myPlayer.setUpMove(true);
			break;
		case Keys.DOWN:
			myPlayer.setState(State.WALKING);
			myPlayer.setDownMove(true);
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// checking for facing for smooth control when a button is pressed
		// while there is another one pressed
		switch (keycode) {
		case Keys.LEFT:
			if (myPlayer.getFacing().equals(Facing.LEFT)) {
				myPlayer.setState(State.IDLE);
			}
			myPlayer.setLeftMove(false);
			break;
		case Keys.RIGHT:
			if (myPlayer.getFacing().equals(Facing.RIGHT)) {
				myPlayer.setState(State.IDLE);
			}
			myPlayer.setRightMove(false);
			break;
		case Keys.UP:
			if (myPlayer.getFacing().equals(Facing.UP)) {
				myPlayer.setState(State.IDLE);
			}
			myPlayer.setUpMove(false);
			break;
		case Keys.DOWN:
			if (myPlayer.getFacing().equals(Facing.DOWN)) {
				myPlayer.setState(State.IDLE);
			}
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
