package Vehicle;

import Obstacles.Obstacle;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.List;

import static com.ai.trafficsim.TrafficSimMain.screenHeight;
import static com.ai.trafficsim.TrafficSimMain.screenWidth;

public abstract class Vehicle extends Sprite {
    int length;
    double maxSpeed;
    float locationX;
    float locationY;
    float previousX;
    float previousY;
    boolean horizontal;
    boolean movingForward;
    int startPosition;
    Direction direction;
    float turningPointX;
    float turningPointY;
    VehicleType vehicleType;

    Vehicle(int length, double maxSpeed, Direction direction, VehicleType vehicleType) {
        this.length = length;
        this.maxSpeed = maxSpeed;
        this.previousX = locationX;
        this.previousY = locationY;
        this.direction = direction;
        this.vehicleType = vehicleType;
        turningPointX = -1;
        turningPointY = -1;
    }

    abstract void SetStartPosition(int startPosition);

    public int GetStartingPosition() {
        return startPosition;
    }

    public Direction GetDirection() {
        return direction;
    }

    public boolean LocationUpdateTick(List<Obstacle> obstacleList, List<Vehicle> vehicleList) {
        boolean isOverlapping = false;
        previousX = locationX;
        previousY = locationY;

        if (turningPointY > 0 || turningPointX > 0) {
            if ((turningPointY > (locationY - 5) && turningPointY < (locationY + 5)) || (turningPointX > (locationX - 5) && turningPointX < (locationX + 5))) {
                MakeTurn();
            }
        }
        if (horizontal) {
            if (movingForward) {
                locationX = locationX + (float) maxSpeed;
                setX(locationX);
            } else {
                locationX = locationX - (float) maxSpeed;
                setX(locationX);
            }
        } else {
            if (movingForward) {
                locationY = locationY + (float) maxSpeed;
                setY(locationY);
            } else {
                locationY = locationY - (float) maxSpeed;
                setY(locationY);
            }
        }

        for (Obstacle o : obstacleList) {
            if (getBoundingRectangle().overlaps(o.getBoundingRectangle())) {
                if (!o.IsPassable()) {
                    isOverlapping = true;
                }
            }
        }

        for (Vehicle v : vehicleList) {
            if (v != this) {
                if (getBoundingRectangle().overlaps(v.getBoundingRectangle())) {
                    isOverlapping = true;
                }
            }
        }

        if (isOverlapping) {
            locationX = previousX;
            setX(locationX);
            locationY = previousY;
            setY(locationY);
        }
        return locationY > screenHeight + length || locationY < 0 - length || locationX > screenWidth + length|| locationX < 0 - length;
    }

    public VehicleType GetVehicleType(){
        return vehicleType;
    }
    private void MakeTurn() {
        switch (startPosition) {
            case 1:
                if (direction == Direction.left) {
                    horizontal = false;
                    rotate(90);
                } else if (direction == Direction.right) {
                    horizontal = false;
                    movingForward = false;
                    rotate(270);
                }
                break;
            case 2:
                if (direction == Direction.left) {
                    horizontal = true;
                    movingForward = false;
                    rotate(270);
                } else if (direction == Direction.right) {
                    horizontal = true;
                    rotate(90);
                }
                break;
            case 3:
                if (direction == Direction.left) {
                    horizontal = false;
                    rotate(90);
                } else if (direction == Direction.right) {
                    horizontal = false;
                    movingForward = true;
                    rotate(270);
                }
                break;
            case 4:
                if (direction == Direction.left) {
                    horizontal = true;
                    movingForward = true;
                    rotate(90);
                } else if (direction == Direction.right) {
                    horizontal = true;
                    rotate(270);
                }
                break;
        }
        turningPointY = -1;
        turningPointX = -1;
    }
}
