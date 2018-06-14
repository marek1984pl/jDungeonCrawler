/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 20:16
 */

package pl.poligro.Entities.Actor.Intefaces;

import pl.poligro.GameEngine.MoveDirection;

public interface Movable {
    Boolean move(MoveDirection moveDirection);
}
