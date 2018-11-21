package Vehicle;

import Obstacles.Obstacle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.List;

import static com.ai.trafficsim.TrafficSimMain.screenHeight;
import static com.ai.trafficsim.TrafficSimMain.screenWidth;

public class Car extends Vehicle {

    public Car(float startX, float startY, boolean horizontal) {
        super(2, 2, startX, startY, horizontal);
        set(new Sprite(new Texture("car.png")));
        setX(startX);
        setY(startY);
        setColor(Color.BLUE);
    }

    @Override
    public boolean LocationUpdateTick(List<Obstacle> obstacleList, List<Vehicle> vehicleList) {
        boolean isOverlapping = false;

        if (horizontal) {
            previousX = locationX;
            locationX = locationX + maxSpeed;
            setX(locationX);
        } else {
            previousY = locationY;
            locationY = locationY + maxSpeed;
            setY(locationY);
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
