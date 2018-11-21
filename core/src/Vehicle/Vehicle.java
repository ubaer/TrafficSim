package Vehicle;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Vehicle extends Sprite{
    int length;
    int maxSpeed;
    float locationX;
    float locationY;
    boolean horizontal;

    Vehicle(int length, int maxSpeed, float locationX, float locationY, boolean horizontal) {
        this.length = length;
        this.maxSpeed = maxSpeed;
        this.locationX = locationX;
        this.locationY = locationY;
        this.horizontal = horizontal;
    }

    // Return false if the vehicle is of screen
    abstract public boolean LocationUpdateTick();
}
