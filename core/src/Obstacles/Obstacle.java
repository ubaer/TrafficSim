package Obstacles;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Obstacle extends Sprite {
    public abstract boolean IsPassable();
    public abstract int GetPosition();
}
