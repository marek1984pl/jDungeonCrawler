package pl.poligro.Actor;

import pl.poligro.Actor.enums.ActorType;
import pl.poligro.Utils.NameGenerator;

public class Monster extends Actor {

    public Monster() {
        setName(NameGenerator.generateName());
        setType(ActorType.MONSTER);
    }
}
