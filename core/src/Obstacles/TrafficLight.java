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
            case 1:
                this.locationX = 368;
                this.locationY = 426;
                this.rotate(90);
                break;
            case 2:
                this.locationX = 464;
                this.locationY = 372;
                break;
            case 3:
                this.locationX = 519;
                this.locationY = 505;
                this.rotate(90);
                break;
            case 4:
                this.locationX = 386;
                this.locationY = 523;
                break;
            case 5:
                this.locationX = 368;
                this.locationY = 390;
                this.rotate(90);
                break;
            case 6:
                this.locationX = 501;
                this.locationY = 372;
                break;
            case 7:
                this.locationX = 519;
                this.locationY = 468;
                this.rotate(90);
                break;
            case 8:
                this.locationX = 423;
                this.locationY = 523;
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
