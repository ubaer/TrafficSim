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
        backgroundImage = new Texture("intersection2.png");

        vehicleList = new ArrayList<>();
        obstacleList = new ArrayList<>();

        batch = new SpriteBatch();

        // To have movement controlled by time instead of refresh rate of the application
        Thread movementThread = new Thread(MovementThreadControl());
        movementThread.start();
        CreateVehicle(VehicleType.Car,4, Direction.left);
        CreateVehicle(VehicleType.Bus,2, null);
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

        /* todo This sometimes crashed the program and has to do something with the vehiclelist being modified while drawing.
           todo Could maybe be fixed by copying the list before drawing the vehicles but then you might draw vehicles that don't exist anymore
        */
        try {
            for (Vehicle v : vehicleList) {
                v.draw(batch);
            }
        } catch (Exception e) {
            System.out.println("Yikes");
            // Pokemon exception to avoid program crash
        }
        // Draw obstacles
        for (Obstacle o : obstacleList) {
            o.draw(batch);
        }

        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.F1)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                CreateVehicle(VehicleType.Car, 1, Direction.left);
            } else if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                CreateVehicle(VehicleType.Car, 1, Direction.right);
            } else {
                CreateVehicle(VehicleType.Car, 1, Direction.straight);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F2)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                CreateVehicle(VehicleType.Car, 2, Direction.left);
            } else if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                CreateVehicle(VehicleType.Car, 2, Direction.right);
            } else {
                CreateVehicle(VehicleType.Car, 2, Direction.straight);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F3)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                CreateVehicle(VehicleType.Car, 3, Direction.left);
            } else if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                CreateVehicle(VehicleType.Car, 3, Direction.right);
            } else {
                CreateVehicle(VehicleType.Car, 3, Direction.straight);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F4)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                CreateVehicle(VehicleType.Car, 4, Direction.left);
            } else if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                CreateVehicle(VehicleType.Car, 4, Direction.right);
            } else {
                CreateVehicle(VehicleType.Car, 4, Direction.straight);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F5)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                TrafficLight trafficLight = (TrafficLight) obstacleList.stream().filter(f -> f.GetPosition() == 2).findFirst().get();
                trafficLight.ControlLight(!trafficLight.IsPassable());
            } else {
                TrafficLight trafficLight = (TrafficLight) obstacleList.stream().filter(f -> f.GetPosition() == 1).findFirst().get();
                trafficLight.ControlLight(!trafficLight.IsPassable());
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F6)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                TrafficLight trafficLight = (TrafficLight) obstacleList.stream().filter(f -> f.GetPosition() == 4).findFirst().get();
                trafficLight.ControlLight(!trafficLight.IsPassable());
            } else {
                TrafficLight trafficLight = (TrafficLight) obstacleList.stream().filter(f -> f.GetPosition() == 3).findFirst().get();
                trafficLight.ControlLight(!trafficLight.IsPassable());
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F7)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                TrafficLight trafficLight = (TrafficLight) obstacleList.stream().filter(f -> f.GetPosition() == 6).findFirst().get();
                trafficLight.ControlLight(!trafficLight.IsPassable());
            } else {
                TrafficLight trafficLight = (TrafficLight) obstacleList.stream().filter(f -> f.GetPosition() == 5).findFirst().get();
                trafficLight.ControlLight(!trafficLight.IsPassable());
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F8)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                TrafficLight trafficLight = (TrafficLight) obstacleList.stream().filter(f -> f.GetPosition() == 8).findFirst().get();
                trafficLight.ControlLight(!trafficLight.IsPassable());
            } else {
                TrafficLight trafficLight = (TrafficLight) obstacleList.stream().filter(f -> f.GetPosition() == 7).findFirst().get();
                trafficLight.ControlLight(!trafficLight.IsPassable());
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F9)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                TrafficLight trafficLight = (TrafficLight) obstacleList.stream().filter(f -> f.GetPosition() == 10).findFirst().get();
                trafficLight.ControlLight(!trafficLight.IsPassable());
            } else {
                TrafficLight trafficLight = (TrafficLight) obstacleList.stream().filter(f -> f.GetPosition() == 9).findFirst().get();
                trafficLight.ControlLight(!trafficLight.IsPassable());
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F11)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                CreateVehicle(VehicleType.Bus, 2, null);
            } else {
                CreateVehicle(VehicleType.Bus, 1, null);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            vehicleList = new ArrayList<>();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    private Runnable MovementThreadControl() {
        return () -> {
            try {
                while (true) {
                    Thread.sleep(10);
                    Iterator<Vehicle> iter = vehicleList.iterator();

                    while (iter.hasNext()) {
                        final Vehicle v = iter.next();

                        List<Vehicle> copyVehicleList = new ArrayList<>(vehicleList);
                        if (v.LocationUpdateTick(obstacleList, copyVehicleList)) {
                            iter.remove();
                            Gdx.app.postRunnable(() -> CreateVehicle(v.GetVehicleType(), v.GetStartingPosition(), v.GetDirection()));
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    private void CreateVehicle(VehicleType vehicleType, int startingPosition, Direction direction) {
        Vehicle vehicle = null;

        switch (vehicleType) {
            case Bus:
                vehicle = new Bus(startingPosition);
                break;
            case Car:
                vehicle = new Car(startingPosition, direction);
                break;
        }

        boolean isOverlapping = false;
        for (Vehicle v : vehicleList) {
            if (vehicle.getBoundingRectangle().overlaps(v.getBoundingRectangle())) {
                isOverlapping = true;
            }
        }

        if (!isOverlapping) {
            vehicleList.add(vehicle);
        }
    }

    private void CreateObstacles() {
        TrafficLight trafficLight;

        for (int i = 1; i < 11; i++) {
            trafficLight = new TrafficLight(i, false);
            obstacleList.add(trafficLight);
        }
    }
}

