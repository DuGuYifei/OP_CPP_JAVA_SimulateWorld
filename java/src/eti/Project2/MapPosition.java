package eti.Project2;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2021/5/26 22:21
 * Description: Position in the map
 */

public class MapPosition {
    private int X;
    private int Y;

    //getters and setters
    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    //Position
    public MapPosition() {
        X = -1;
        Y = -1;
    }

    public MapPosition(int xCoordinate, int yCoordinate) {
        X = xCoordinate;
        Y = yCoordinate;
    }

    //the world is a sphere,
    //so it will go to the other size in the map when it move out of side
    public MapPosition Left(World world) {
        int x;
        if (X > 0)
            x = X - 1;
        else
            x = world.getSizeX() - 1;
        return new MapPosition(x, Y);
    }

    public MapPosition Right(World world) {
        int x;
        if (X < world.getSizeX() - 1)
            x = X + 1;
        else
            x = 0;
        return new MapPosition(x, Y);
    }

    public MapPosition Up(World world) {
        int y;
        if (Y > 0)
            y = Y - 1;
        else
            y = world.getSizeY() - 1;
        return new MapPosition(X, y);
    }

    public MapPosition Down(World world) {
        int y;
        if (Y < world.getSizeY() - 1)
            y = Y + 1;
        else
            y = 0;
        return new MapPosition(X, y);
    }

    public MapPosition LeftUp(World world) {
        int x;
        if (X > 0)
            x = X - 1;
        else
            x = world.getSizeX() - 1;

        int y;
        if (Y > 0)
            y = Y - 1;
        else
            y = world.getSizeY() - 1;

        return new MapPosition(x, y);
    }

    public MapPosition LeftDown(World world){
        int x;
        if (X > 0)
            x = X - 1;
        else
            x = world.getSizeX() - 1;

        int y;
        if (Y < world.getSizeY() - 1)
            y = Y + 1;
        else
            y = 0;

        return new MapPosition(x, y);
    }

    public MapPosition RightUp(World world){
        int x;
        if (X < world.getSizeX() - 1)
            x = X + 1;
        else
            x = 0;

        int y;
        if (Y > 0)
            y = Y - 1;
        else
            y = world.getSizeY() - 1;

        return new MapPosition(x, y);
    }

    public MapPosition RightDown(World world){
        int x;
        if (X < world.getSizeX() - 1)
            x = X + 1;
        else
            x = 0;

        int y;
        if (Y < world.getSizeY() - 1)
            y = Y + 1;
        else
            y = 0;

        return new MapPosition(x, y);
    }
}
