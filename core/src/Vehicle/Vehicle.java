package Vehicle;

import Obstacles.Obstacle;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.List;

public abstract class Vehicle extends Sprite {
    int length;
    int maxSpeed;
    float locationX;
    float locationY;
    float previousX;
    float previousY;
    boolean horizontal;
    boolean movingForward;
    int startposition;
    Direction direction;
    float turningPointX;
    float turningPointY;

    Vehicle(int length, int maxSpeed, Direction direction) {
        this.length = length;
        this.maxSpeed = maxSpeed;
        this.previousX = locationX;
        this.previousY = locationY;
        this.direction = direction;
        turningPointX = -1;
        turningPointY = -1;
    }

    public int GetStartingPosition() {
        return startposition;
    }

    public Direction GetDirection() {
        return direction;
    }

    // Return false if the vehicle is of screen
    abstract public boolean LocationUpdateTick(List<Obstacle> obstacleList, List<Vehicle> vehicleList);
}
