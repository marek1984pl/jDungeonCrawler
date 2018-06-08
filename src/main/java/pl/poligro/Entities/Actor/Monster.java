/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 20:16
 */

package pl.poligro.Entities.Actor;

import pl.poligro.Entities.EntityType;
import pl.poligro.GameEngine.Position;
import pl.poligro.Utils.NameGenerator;

public class Monster extends Actor {

    public Monster() {
        setName(NameGenerator.generateName());
        setType(ActorType.MONSTER);
        setEntityType(EntityType.MONSTER);
        setGraphicsName("TOMB");
    }

    public Monster(Position position) {
        this();
        setPosition(position);
    }

    @Override
    public void interactWith() {

    }
}
