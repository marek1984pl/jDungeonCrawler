/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 20:34
 */

package pl.poligro.GameEngine;

import pl.poligro.Utils.GlobalConst;

import java.util.Random;

public class Position {

    private static Random rand = new Random();

    private Integer x, y;

    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Position getNewPosition(MoveDirection moveDirection) {
        switch (moveDirection) {
            case UP:
                return new Position(this.getX(), this.getY() - 1);
            case DOWN:
                return new Position(this.getX(), this.getY() + 1);
            case LEFT:
                return new Position(this.getX() - 1, this.getY());
            case RIGHT:
                return new Position(this.getX() + 1, this.getY());
        }
        return this;
    }

    public static MoveDirection getRandomDirection() {
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

    public static Position getNewRandomPosition() {
        return new Position(rand.nextInt(GlobalConst.GAME_WINDOW_WIDTH_TILES), rand.nextInt(GlobalConst.GAME_WINDOW_HEIGHT_TILES));
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public String toString() {
        return "x = " + x + ", y = " + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return (x != null ? x.equals(position.x) : position.x == null) && (y != null ? y.equals(position.y) : position.y == null);
    }
}
