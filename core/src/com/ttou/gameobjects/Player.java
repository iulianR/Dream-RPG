package com.ttou.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Player {

	public enum State {
		IDLE, WALKING, RUNNING
	}

	public enum Facing {
		LEFT, RIGHT, UP, DOWN
	}

	Vector2 position;
	State state;
	Facing facing;
	float stateTime = 0;
	final int SPEED = 50;

	boolean leftMove;
	boolean rightMove;
	boolean upMove;
	boolean downMove;

	public static final int SIZE = 32;

	public Player(float x, float y) {
		this.position = new Vector2(x, y);
		this.state = State.IDLE;
		this.facing = Facing.DOWN;
	}

	public void update(float delta) {
		stateTime += delta;
		updateMotion(delta);
	}

	public void updateMotion(float delta) {
		if (leftMove) {
			position.x -= SPEED * delta;
		}
		if (rightMove) {
			position.x += SPEED * delta;
		}
		if (upMove) {
			position.y += SPEED * delta;
		}
		if (downMove) {
			position.y -= SPEED * delta;
		}
	}

	public Vector2 getPosition() {
		return position;
	}

	public Facing getFacing() {
		return facing;
	}

	public State getState() {
		return state;
	}

	public float getStateTime() {
		return stateTime;
	}

	public boolean isLeftMove() {
		return leftMove;
	}

	public boolean isUpMove() {
		return upMove;
	}

	public boolean isDownMove() {
		return downMove;
	}

	public boolean isRightMove() {
		return rightMove;
	}

	public void setFacing(Facing newFacing) {
		this.facing = newFacing;
	}

	public void setState(State newState) {
		this.state = newState;
	}

	public void setLeftMove(boolean t) {
		if (rightMove && t)
			rightMove = false;
		leftMove = t;
	}

	public void setRightMove(boolean t) {
		if (leftMove && t)
			leftMove = false;
		rightMove = t;
	}

	public void setUpMove(boolean t) {
		if (downMove && t)
			downMove = false;
		upMove = t;
	}

	public void setDownMove(boolean t) {
		if (upMove && t)
			upMove = false;
		downMove = t;
	}
}
