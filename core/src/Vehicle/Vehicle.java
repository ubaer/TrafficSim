package Vehicle;

import Obstacles.Obstacle;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.List;

public abstract class Vehicle extends Sprite{
    int length;
    int maxSpeed;
    float locationX;
    float locationY;
    float previousX;
    float previousY;
    boolean horizontal;

    Vehicle(int length, int maxSpeed, float locationX, float locationY, boolean horizontal) {
        this.length = length;
        this.maxSpeed = maxSpeed;
        this.locationX = locationX;
        this.locationY = locationY;
        this.previousX = locationX;
        this.previousY = locationY;
        this.horizontal = horizontal;
    }

    // Return false if the vehicle is of screen
    abstract public boolean LocationUpdateTick(List<Obstacle> obstacleList, List<Vehicle> vehicleList);
}
