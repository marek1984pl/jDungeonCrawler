package pl.poligro.Actor;

import pl.poligro.Actor.enums.ActorStatus;
import pl.poligro.Actor.enums.ActorType;
import pl.poligro.GameObject.GameObject;

public abstract class Actor implements GameObject {

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
    public String toString() {
        return "Actor{" +
                "id=" + getId() +
                ", status=" + status +
                ", type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}
