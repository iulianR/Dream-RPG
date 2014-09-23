package com.ttou.gameobjects;

public class Player {

	public enum State {
		IDLE, WALKING, RUNNING
	}

	public enum Facing {
		LEFT, RIGHT, UP, DOWN
	}

	private float positionX;
	private float positionY;
	State state = State.IDLE;
	float stateTime = 0;
	final int SPEED = 50;

	boolean leftMove;
	boolean rightMove;
	boolean upMove;
	boolean downMove;

	Facing facing = Facing.DOWN;

	public static final int SIZE = 32;

	public Player(int x, int y) {
		this.positionX = x;
		this.positionY = y;
	}

	public void update(float delta) {
		stateTime += delta;
		updateMotion(delta);
	}

	public void updateMotion(float delta) {
		if (leftMove) {
			positionX -= SPEED * delta;
		}
		if (rightMove) {
			positionX += SPEED * delta;
		}
		if (upMove) {
			positionY += SPEED * delta;
		}
		if (downMove) {
			positionY -= SPEED * delta;
		}
	}

	public float getX() {
		return positionX;
	}

	public float getY() {
		return positionY;
	}

	public Facing getFacing() {
		return facing;
	}

	public float getStateTime() {
		return stateTime;
	}

	public State getState() {
		return state;
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

	public void setState(State newState) {
		this.state = newState;
	}

	public void setFacing(Facing newFacing) {
		this.facing = newFacing;
	}
}
