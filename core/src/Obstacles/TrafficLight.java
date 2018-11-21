package Obstacles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TrafficLight extends Obstacle {
    float locationX;
    float locationY;
    boolean greenLightOn;

    public TrafficLight(float locationX, float locationY, boolean greenLightOn){
        set(new Sprite(new Texture("trafficLight.png")));
        this.locationX = locationX;
        this.locationY = locationY;
        setX(locationX);
        setY(locationY);
        ControlLight(greenLightOn);
    }

    public void ControlLight(boolean greenLightOn){
        this.greenLightOn = greenLightOn;
        if(greenLightOn){
            setColor(Color.GREEN);
        }
        else {
            setColor(Color.RED);
        }
    }
}
