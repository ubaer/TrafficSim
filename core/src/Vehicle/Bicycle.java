package Vehicle;

import Obstacles.Obstacle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.List;

import static com.ai.trafficsim.TrafficSimMain.screenHeight;
import static com.ai.trafficsim.TrafficSimMain.screenWidth;

public class Bicycle extends Vehicle {
    private boolean sideways;
    private boolean turning;

    public Bicycle(int startPosition) {
        super(18, 0.7, Direction.straight, VehicleType.Bicycle);
        set(new Sprite(new Texture("bicycle.png")));
        SetStartPosition(startPosition);
        setX(locationX);
        setY(locationY);
        setColor(Color.BLACK);
        sideways = false;
        turning = false;
    }

    @Override
    void SetStartPosition(int startPosition) {
        this.startPosition = startPosition;
        switch (startPosition) {
            case 1:
                locationX = 615;
                locationY = 0 - length;
                movingForward = true;
                horizontal = false;
                turningPointY = 356;
                direction = Direction.left;
                break;
            case 2:
                locationX = 603;
                locationY = 0 - length;
                movingForward = true;
                horizontal = false;
                turningPointY = 347;
                direction = Direction.left;
                break;
            case 3:
                locationX = 275;
                locationY = 900 + length;
                movingForward = false;
                horizontal = false;
                turningPointY = 527;
                rotate(180);
                direction = Direction.left;
                break;
            case 4:
                locationX = 287;
                locationY = 900 + length;
                movingForward = false;
                horizontal = false;
                turningPointY = 534;
                rotate(180);
                direction = Direction.left;
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
        if (movingForward) {
            if (sideways) {
                locationY = locationY + (float) maxSpeed / (float) 1.6;
                locationX = locationX - (float) 0.65;
                setY(locationY);
                setX(locationX);
            } else {
                locationY = locationY + (float) maxSpeed;
                setY(locationY);
            }
        } else {
            if (sideways) {
                locationY = locationY - (float) maxSpeed / (float) 1.6;
                locationX = locationX + (float) 0.65;
                setY(locationY);
                setX(locationX);
            } else {
                locationY = locationY - (float) maxSpeed;
                setY(locationY);
            }
        }


        for (Obstacle o : obstacleList) {
            if (getBoundingRectangle().overlaps(o.getBoundingRectangle())) {
                if (!o.IsPassable()) {
                    isOverlapping = true;

                    if (o.GetPosition() == 8 || o.GetPosition() == 4) {
                        isOverlapping = false;
                    }
                }
            }
        }

        for (
                Vehicle v : vehicleList)

        {
            if (v != this) {
                if (getBoundingRectangle().overlaps(v.getBoundingRectangle())) {
                    isOverlapping = true;

                    if (v.vehicleType.equals(VehicleType.Bicycle)) {
                        if (startPosition == 1 || startPosition == 2) {
                            if (v.startPosition == 3 || v.startPosition == 4) {
                                isOverlapping = false;
                            }
                        } else if (startPosition == 3 || startPosition == 4) {
                            if (v.startPosition == 1 || v.startPosition == 2) {
                                isOverlapping = false;
                            }
                        }
                    }
                }
            }
        }

        if (isOverlapping)

        {
            locationX = previousX;
            setX(locationX);
            locationY = previousY;
            setY(locationY);
        }
        if (turning)

        {
            if (movingForward) {
                if (direction == Direction.left) {
                    rotate(3);
                    if (getRotation() > 54) {
                        turning = false;
                        direction = Direction.right;
                    }
                } else {
                    rotate(-3);
                    if (getRotation() == 0) {
                        turning = false;
                        direction = Direction.straight;
                    }
                }
            } else {
                if (direction == Direction.left) {
                    rotate(3);
                    if (getRotation() > 234) {
                        turning = false;
                        direction = Direction.right;
                    }
                } else {
                    rotate(-3);
                    if (getRotation() == 180) {
                        turning = false;
                        direction = Direction.straight;
                    }
                }
            }
        }
        return locationY > screenHeight + length || locationY < 0 - length || locationX > screenWidth + length || locationX < 0 - length;
    }

    private void MakeTurn() {
        sideways = !sideways;
        switch (startPosition) {
            case 1:
                if (sideways) {
                    turningPointY = -1;
                    turningPointX = 309;
                } else {
                    turningPointX = -1;
                }
                turning = true;
                break;
            case 2:
                if (sideways) {
                    turningPointY = -1;
                    turningPointX = 298;
                } else {
                    turningPointX = -1;
                }
                turning = true;
                break;
            case 3:
                if (sideways) {
                    turningPointY = -1;
                    turningPointX = 581;
                } else {
                    turningPointX = -1;
                }
                turning = true;
                break;
            case 4:
                if (sideways) {
                    turningPointY = -1;
                    turningPointX = 592;
                } else {
                    turningPointX = -1;
                }
                turning = true;
                break;
        }
    }
}
