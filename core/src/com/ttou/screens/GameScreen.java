package com.ttou.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.ttou.gameworld.GameRenderer;
import com.ttou.gameworld.GameWorld;
import com.ttou.helpers.InputHandler;

public class GameScreen implements Screen {

	private GameWorld world;
	private GameRenderer renderer;

	public GameScreen() {
		Gdx.app.log("GameScreen", "Attached");
		world = new GameWorld();
		Gdx.input.setInputProcessor(new InputHandler(world));
		renderer = new GameRenderer(world);
	}

	@Override
	public void render(float delta) {
		world.update(delta);
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("GameScreen", "resizing");
		renderer.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void show() {
		Gdx.app.log("GameScreen", "show called");
	}

	@Override
	public void hide() {
		Gdx.app.log("GameScreen", "hide called");
	}

	@Override
	public void pause() {
		Gdx.app.log("GameScreen", "pause called");
	}

	@Override
	public void resume() {
		Gdx.app.log("GameScreen", "resume called");
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}
