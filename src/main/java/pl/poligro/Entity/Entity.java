package pl.poligro.Entity;

import pl.poligro.GameEngine.Position;
import pl.poligro.Utils.IdGenerator;

public abstract class Entity {

    private final String id = IdGenerator.generateNewId();
    private Position position;

    public String getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
