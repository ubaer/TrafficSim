package Vehicle;

import Obstacles.Obstacle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.List;

import static com.ai.trafficsim.TrafficSimMain.screenHeight;
import static com.ai.trafficsim.TrafficSimMain.screenWidth;

public class Car extends Vehicle {

    public Car(int startPosition, Direction direction) {
        super(2, 2, direction);
        set(new Sprite(new Texture("car.png")));
        SetStartPosition(startPosition);
        setX(locationX);
        setY(locationY);
        setColor(Color.BLUE);
    }

    private void SetStartPosition(int startPosition) {
        this.startposition = startPosition;
        switch (startPosition) {
            case 1:
                locationX = 0;
                locationY = 407;
                movingForward = true;
                horizontal = true;
                this.rotate(270);
                // Set when the car has to turn
                if (direction == Direction.right) {
                    locationY -= 34;
                    turningPointX = 397;
                }
                if (direction == Direction.left) {
                    turningPointX = 473;
                }
                break;
            case 2:
                locationX = 471;
                locationY = 0;
                movingForward = true;
                horizontal = false;
                if (direction == Direction.right) {
                    locationX += 32;
                    turningPointY = 373;
                }
                if (direction == Direction.left) {
                    turningPointY = 455;
                }
                break;
            case 3:
                locationX = 900;
                locationY = 450;
                movingForward = false;
                horizontal = true;
                this.rotate(90);
                if (direction == Direction.right) {
                    locationY += 35;
                    turningPointX = 500;
                }
                if (direction == Direction.left) {
                    turningPointX = 425;
                }
                break;
            case 4:
                locationX = 428;
                locationY = 900;
                movingForward = false;
                horizontal = false;
                this.rotate(180);
                if (direction == Direction.right) {
                    locationX -= 38;
                    turningPointY = 483;
                }
                if (direction == Direction.left) {
                    turningPointY = 405;
                }
                break;
        }
    }

    @Override
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
                locationX = locationX + maxSpeed;
                setX(locationX);
            } else {
                locationX = locationX - maxSpeed;
                setX(locationX);
            }
        } else {
            if (movingForward) {
                locationY = locationY + maxSpeed;
                setY(locationY);
            } else {
                locationY = locationY - maxSpeed;
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
        return locationY > screenHeight || locationY < 0 || locationX > screenWidth || locationX < 0;
    }

    private void MakeTurn() {
        switch (startposition) {
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
