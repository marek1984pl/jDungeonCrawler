/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 20:16
 */

package pl.poligro.entities.actor.intefaces;

import pl.poligro.game_engine.MoveDirection;

public interface Movable {
    Boolean move(MoveDirection moveDirection);
}
