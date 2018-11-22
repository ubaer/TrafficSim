package Vehicle;

import Obstacles.Obstacle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.List;

import static com.ai.trafficsim.TrafficSimMain.screenHeight;
import static com.ai.trafficsim.TrafficSimMain.screenWidth;

public class Car extends Vehicle {

    public Car(int startPosition) {
        super(2, 2);
        set(new Sprite(new Texture("car.png")));
        SetStartPosition(startPosition);

        setColor(Color.BLUE);
    }

    private void SetStartPosition(int startPosition) {
        this.startposition = startPosition;
        switch(startPosition){
            case 1:
                locationX = 0;
                locationY = 400;
                setX(locationX);
                setY(locationY);
                movingForward = true;
                horizontal = true;
                this.rotate(270);
                break;
            case 2:
                locationX = 475;
                locationY = 0;
                setX(locationX);
                setY(locationY);
                movingForward = true;
                horizontal = false;
                break;
            case 3:
                locationX = 900;
                locationY = 475;
                setX(locationX);
                setY(locationY);
                movingForward = false;
                horizontal = true;
                this.rotate(90);
                break;
            case 4:
                locationX = 400;
                locationY = 900;
                setX(locationX);
                setY(locationY);
                movingForward = false;
                horizontal = false;
                this.rotate(180);
                break;
        }
    }

    @Override
    public boolean LocationUpdateTick(List<Obstacle> obstacleList, List<Vehicle> vehicleList) {
        boolean isOverlapping = false;
        previousX = locationX;
        previousY = locationY;

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
}
