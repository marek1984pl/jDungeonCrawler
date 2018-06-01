package pl.poligro.Actor;

import pl.poligro.Actor.Intefaces.Movable;
import pl.poligro.Enums.ActorStatus;
import pl.poligro.Enums.ActorType;
import pl.poligro.Entity.Entity;
import pl.poligro.Enums.MoveDirection;

public abstract class Actor extends Entity implements Movable {

    private ActorStatus status;
    private ActorType type;
    private String name;
    private Integer level;

    public Actor() {
        this.name = "default name";
        this.status = ActorStatus.ALIVE;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ActorStatus getStatus() {
        return status;
    }

    public void setStatus(ActorStatus status) {
        this.status = status;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public ActorType getType() {
        return type;
    }

    public void setType(ActorType type) {
        this.type = type;
    }

    @Override
    public Boolean move(MoveDirection moveDirection) {
        return null;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + getId() +
                ", status=" + status +
                ", type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}
