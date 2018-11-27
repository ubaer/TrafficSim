package Vehicle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bus extends Vehicle {
    public Bus(int startPosition) {
        super(2, 1.5, Direction.straight, VehicleType.Bus);
        set(new Sprite(new Texture("bus.png")));
        SetStartPosition(startPosition);
        setX(locationX);
        setY(locationY);
        setColor(Color.BROWN);
    }

    @Override
    void SetStartPosition(int startPosition) {
        this.startPosition = startPosition;
        switch (startPosition) {
            case 1:
                locationX = 467;
                locationY = 0;
                movingForward = true;
                horizontal = false;
                break;
            case 2:
                locationX = 410;
                locationY = 900;
                movingForward = false;
                horizontal = false;
                this.rotate(180);
                break;
        }
    }
}
