package com.ai.trafficsim;

import Obstacles.Obstacle;
import Obstacles.TrafficLight;
import Vehicle.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TrafficSimMain extends ApplicationAdapter {
    public static int screenWidth = 900;
    public static int screenHeight = 900;

    private SpriteBatch batch;
    private Texture backgroundImage;

    private List<Vehicle> vehicleList;
    private List<Obstacle> obstacleList;

    @Override
    public void create() {
        backgroundImage = new Texture("intersection.png");
        vehicleList = new ArrayList<Vehicle>();
        obstacleList = new ArrayList<Obstacle>();

        batch = new SpriteBatch();

        // To have movement controlled by time instead of refresh rate of the application
        Thread movementThread = new Thread(MovementThreadControl());
        movementThread.start();
        CreateVehicle();
        CreateObstacles();
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        // Draw the background image
        batch.draw(backgroundImage, 0, 0);
        // Draw the vehicles
        for (Vehicle v : vehicleList) {
            v.draw(batch);
        }

        for (Obstacle o : obstacleList) {
            o.draw(batch);
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    private Runnable MovementThreadControl() {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(10);
                        Iterator<Vehicle> iter = vehicleList.iterator();

                        while (iter.hasNext()) {
                            Vehicle v = iter.next();
                            if (v.LocationUpdateTick()) {
                                iter.remove();
                                Gdx.app.postRunnable(new Runnable() {
                                    @Override
                                    public void run() {
                                        CreateVehicle();
                                    }
                                });
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    // todo Add a way to determine which lane
    private void CreateVehicle() {
        Car car = new Car(475, 0, false);
        vehicleList.add(car);
    }

    private void CreateObstacles() {
        TrafficLight trafficLight1 = new TrafficLight(465, 372, false);
        obstacleList.add(trafficLight1);
    }
}

