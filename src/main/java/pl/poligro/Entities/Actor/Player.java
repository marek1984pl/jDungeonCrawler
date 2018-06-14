/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 20:16
 */

package pl.poligro.Entities.Actor;

import pl.poligro.Entities.Entity;
import pl.poligro.Entities.EntityType;
import pl.poligro.GameEngine.Position;

public class Player extends Actor {

    public Player(String name) {
        setName(name);
        setType(ActorType.PLAYER);
        setEntityType(EntityType.PLAYER);
        setPosition(new Position(5, 5));
        setGraphicsName("TROLL");
    }

    @Override
    public void interactWith(Entity entity) {
//        entity.interactWith(this);
    }
}
