package Obstacles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TrafficLight extends Obstacle {
    private float locationX;
    private float locationY;
    private boolean greenLightOn;
    private int position;

    public TrafficLight(int position, boolean greenLightOn) {
        set(new Sprite(new Texture("trafficLight.png")));
        SetPosition(position);

        ControlLight(greenLightOn);
    }

    @Override
    public int GetPosition() {
        return position;
    }

    private void SetPosition(int position) {
        this.position = position;

        switch (position) {
            // Car Pos 1
            case 1:
                this.locationX = 304;
                this.locationY = 428;
                this.rotate(90);
                break;
            case 2:
                this.locationX = 304;
                this.locationY = 391;
                this.rotate(90);
                break;
            // Car Pos 2
            case 3:
                this.locationX = 505;
                this.locationY = 332;
                break;
            case 4:
                this.locationX = 541;
                this.locationY = 332;
                break;
            // Car Pos 3
            case 5:
                this.locationX = 561;
                this.locationY = 467;
                this.rotate(90);
                break;
            case 6:
                this.locationX = 561;
                this.locationY = 504;
                this.rotate(90);
                break;
            // Car Pos 4
            case 7:
                this.locationX = 361;
                this.locationY = 568;
                break;
            case 8:
                this.locationX = 323;
                this.locationY = 568;
                break;
        }
        setX(locationX);
        setY(locationY);
    }

    public void ControlLight(boolean greenLightOn) {
        this.greenLightOn = greenLightOn;
        if (greenLightOn) {
            setColor(Color.GREEN);
        } else {
            setColor(Color.RED);
        }
    }

    @Override
    public boolean IsPassable() {
        return greenLightOn;
    }
}
