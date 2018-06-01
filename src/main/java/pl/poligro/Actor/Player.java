package pl.poligro.Actor;

import pl.poligro.Enums.ActorType;

public class Player extends Actor {

    private Player() {
    }

    public Player(String name) {
        setName(name);
        setType(ActorType.PLAYER);
    }
}
