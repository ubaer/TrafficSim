package com.ai.trafficsim;

import Obstacles.Obstacle;
import Obstacles.TrafficLight;
import Vehicle.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
        CreateVehicle(2);
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
        // Draw obstacles
        for (Obstacle o : obstacleList) {
            o.draw(batch);
        }

        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.F1)) {
            // your actions
            CreateVehicle(1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F2)) {
            // your actions
            CreateVehicle(2);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F3)) {
            // your actions
            CreateVehicle(3);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F4)) {
            // your actions
            CreateVehicle(4);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F5)) {
            // your actions
            TrafficLight trafficLight = (TrafficLight) obstacleList.stream().filter(f -> f.GetPosition() == 1).findFirst().get();
            trafficLight.ControlLight(!trafficLight.IsPassable());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F6)) {
            // your actions
            TrafficLight trafficLight = (TrafficLight) obstacleList.stream().filter(f -> f.GetPosition() == 2).findFirst().get();
            trafficLight.ControlLight(!trafficLight.IsPassable());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F7)) {
            // your actions
            TrafficLight trafficLight = (TrafficLight) obstacleList.stream().filter(f -> f.GetPosition() == 3).findFirst().get();
            trafficLight.ControlLight(!trafficLight.IsPassable());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F8)) {
            // your actions
            TrafficLight trafficLight = (TrafficLight) obstacleList.stream().filter(f -> f.GetPosition() == 4).findFirst().get();
            trafficLight.ControlLight(!trafficLight.IsPassable());
        }
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
                            final Vehicle v = iter.next();
                            //
                            if (v.LocationUpdateTick(obstacleList, vehicleList)) {
                                iter.remove();
                                Gdx.app.postRunnable(new Runnable() {
                                    @Override
                                    public void run() {
                                        CreateVehicle(v.GetStartingPosition());
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
    private void CreateVehicle(int startPosition) {
        Car car = new Car(startPosition);

        boolean isOverlapping = false;
        for (Vehicle v : vehicleList) {
            if (car.getBoundingRectangle().overlaps(v.getBoundingRectangle())) {
                isOverlapping = true;
            }
        }

        if (!isOverlapping) {
            vehicleList.add(car);
        }
    }

    private void CreateObstacles() {
        TrafficLight trafficLight;

        for(int i = 1; i < 5; i ++){
            trafficLight = new TrafficLight(i, false);
            obstacleList.add(trafficLight);
        }
    }
}

