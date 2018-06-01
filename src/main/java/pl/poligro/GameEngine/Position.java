/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 20:34
 */

package pl.poligro.GameEngine;

import java.util.Random;

public class Position {

    private static Random rand = new Random();

    private Integer x, y;

    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public static MoveDirection randomDirection() {
        int newPosition = rand.nextInt(4);

        switch (newPosition) {
            case 0:
                return MoveDirection.UP;
            case 1:
                return MoveDirection.DOWN;
            case 2:
                return MoveDirection.LEFT;
            case 3:
                return MoveDirection.RIGHT;
            default:
                return null;
        }
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
