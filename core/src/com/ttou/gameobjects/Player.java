package com.ttou.gameobjects;

public class Player {

	private float positionX;
	private float positionY;
	final int SPEED = 50;

	boolean leftMove;
	boolean rightMove;
	boolean upMove;
	boolean downMove;

	static final int size = 32;

	public Player(int x, int y) {
		this.positionX = x;
		this.positionY = y;
	}

	public void update(float delta) {
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
			positionY -= SPEED * delta;
		}
		if (downMove) {
			positionY += SPEED * delta;
		}
	}

	public float getX() {
		return positionX;
	}

	public float getY() {
		return positionY;
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
