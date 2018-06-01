/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 20:16
 */

package pl.poligro.Actor;

import pl.poligro.GameEngine.Position;

public class Player extends Actor {

    public Player(String name) {
        setName(name);
        setType(ActorType.PLAYER);
        setPosition(new Position(0, 0));
    }
}
