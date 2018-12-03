package com.ai.trafficsim.desktop;

import com.ai.trafficsim.TrafficSimMain;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages= {"API"})
@SpringBootApplication
public class DesktopLauncher {
	public static void main (String[] arg) {
		SpringApplication.run(DesktopLauncher.class, arg);
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 900;
		config.height = 900;
		config.resizable = false;
		new LwjglApplication(TrafficSimMain.getInstance(), config);
	}
}
