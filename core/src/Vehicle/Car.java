package Vehicle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static com.ai.trafficsim.TrafficSimMain.screenHeight;
import static com.ai.trafficsim.TrafficSimMain.screenWidth;

public class Car extends Vehicle {

    public Car(float startX, float startY, boolean horizontal) {
        super(2, 2 , startX, startY, horizontal);
        set(new Sprite(new Texture("car.png")));
        setX(startX);
        setY(startY);
        setColor(Color.BLUE);
    }

    @Override
    public boolean LocationUpdateTick() {
        if (horizontal) {
            locationX = locationX + maxSpeed;
            setX(locationX);
        } else {
            locationY = locationY + maxSpeed;
            setY(locationY);
        }

        return locationY > screenHeight || locationY < 0 || locationX > screenWidth || locationX < 0;
    }
}
