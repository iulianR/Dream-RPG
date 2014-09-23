package com.ttou.dreamrpg.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ttou.dreamrpg.DRGame;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Zombie Bird";
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new DRGame(), config);
	}
}
