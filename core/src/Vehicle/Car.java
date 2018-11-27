package Vehicle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Car extends Vehicle {

    public Car(int startPosition, Direction direction) {
        super(40, 2, direction, VehicleType.Car);
        set(new Sprite(new Texture("car.png")));
        SetStartPosition(startPosition);
        setX(locationX);
        setY(locationY);
        setColor(Color.BLUE);
    }

    @Override
    void SetStartPosition(int startPosition) {
        this.startPosition = startPosition;
        switch (startPosition) {
            case 1:
                locationX = 0 - length;
                locationY = 410;
                movingForward = true;
                horizontal = true;
                this.rotate(270);
                // Set when the car has to turn
                if (direction == Direction.right) {
                    locationY -= 38;
                    turningPointX = 331;
                }
                if (direction == Direction.left) {
                    turningPointX = 512;
                }
                break;
            case 2:
                locationX = 507;
                locationY = 0 - length;
                movingForward = true;
                horizontal = false;
                if (direction == Direction.right) {
                    locationX += 37;
                    turningPointY = 373;
                }
                if (direction == Direction.left) {
                    turningPointY = 449;
                }
                break;
            case 3:
                locationX = 900 + length;
                locationY = 447;
                movingForward = false;
                horizontal = true;
                this.rotate(90);
                if (direction == Direction.right) {
                    locationY += 38;
                    turningPointX = 544;
                }
                if (direction == Direction.left) {
                    turningPointX = 365;
                }
                break;
            case 4:
                locationX = 368;
                locationY = 900 + length;
                movingForward = false;
                horizontal = false;
                this.rotate(180);
                if (direction == Direction.right) {
                    locationX -= 42;
                    turningPointY = 483;
                }
                if (direction == Direction.left) {
                    turningPointY = 408;
                }
                break;
        }
    }
}
