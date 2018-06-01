/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 20:16
 */

package pl.poligro.Actor;

import pl.poligro.GameEngine.Position;
import pl.poligro.Utils.NameGenerator;

public class Monster extends Actor {

    public Monster() {
        setName(NameGenerator.generateName());
        setType(ActorType.MONSTER);

    }

    public Monster(Position position) {
        setName(NameGenerator.generateName());
        setType(ActorType.MONSTER);
        setPosition(position);
    }
}
