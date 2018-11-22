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

    Vehicle(int length, int maxSpeed) {
        this.length = length;
        this.maxSpeed = maxSpeed;
        this.previousX = locationX;
        this.previousY = locationY;
    }

    public int GetStartingPosition() {
        return startposition;
    }

    // Return false if the vehicle is of screen
    abstract public boolean LocationUpdateTick(List<Obstacle> obstacleList, List<Vehicle> vehicleList);
}
