package pl.poligro.Actor;

import pl.poligro.Actor.enums.ActorType;

public class Player extends Actor {

    private Player() {
    }

    public Player(String name) {
        setName(name);
        setType(ActorType.PLAYER);
    }
}
