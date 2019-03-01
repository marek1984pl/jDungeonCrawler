/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 20:16
 */

package pl.poligro.entities.actor;

import pl.poligro.entities.actor.intefaces.Movable;
import pl.poligro.entities.Entity;
import pl.poligro.game_engine.MoveDirection;

public abstract class Actor extends Entity implements Movable {

    private ActorStatus status;
    private ActorType type;
    private String name;
    private Integer level;
    private Statistics stats = new Statistics();

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

    public Statistics getStats() {
        return stats;
    }

    public void createStats(Integer strength, Integer dexterity, Integer intelligence, Integer stamina, ActorType actorType) {
        if (actorType.equals(ActorType.PLAYER)) {
            stats.setStrength(strength);
            stats.setDexterity(dexterity);
            stats.setIntelligence(intelligence);
            stats.setStamina(stamina);
            stats.setAttackPower(strength * 2);
            stats.setHitChance(strength.floatValue() + dexterity.floatValue() + 60);
            stats.setCritChance((strength.floatValue() * dexterity.floatValue()) / 10 + 10);
            stats.setHealth(stamina * 10);
        }
    }

    @Override
    public Boolean move(MoveDirection moveDirection) {
        setPosition(getPosition().getNewPosition(moveDirection));
        return null;
    }

    @Override
    public String toString() {
        return "actor{" +
                "id=" + getId() +
                ", status=" + status +
                ", type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}
