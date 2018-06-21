/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 07.06.2018
 * Time: 18:52
 */

package pl.poligro.GameEngine;

import pl.poligro.Entities.Actor.Player;
import pl.poligro.Entities.Entity;
import pl.poligro.Entities.EntityType;
import pl.poligro.GameEngine.Exceptions.EntityNotFoundException;
import pl.poligro.GameEngine.Interface.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameState implements Observable {

    //todo move methods to GameEngine

    private List<Entity> gameEntities = new ArrayList<>();
    private Integer turnNumber;
    private boolean isRunning = true;

    private Player player = new Player("Zodgar");

    public List<Entity> getGameEntities() {
        return gameEntities;
    }

    public Entity getEntityByPosition(final Position position) {
        final Optional<Entity> first = gameEntities
                .stream()
                .filter(entity -> entity.getPosition().equals(position))
                .findFirst();

        return first.orElse(null);
    }

    public Entity getEntityById(final String entityId) {
        final Optional<Entity> first = gameEntities
                .stream()
                .filter(entity -> entity.getId().equals(entityId))
                .findFirst();

        try {
            return first.orElseThrow(() -> new EntityNotFoundException(entityId));
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Entity> getEntitiesByType(EntityType entityType) {
        return gameEntities
                .stream()
                .filter(entity -> entity.getEntityType().equals(entityType))
                .collect(Collectors.toList());
    }

    public Integer getTurnNumber() {
        return turnNumber;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Entity> getMonsterList() {
        return getEntitiesByType(EntityType.MONSTER);
    }

    public void setTurnNumber(Integer turnNumber) {
        this.turnNumber = turnNumber;
    }

    public List<Entity> getObstacles() {
        return getEntitiesByType(EntityType.WALL);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
