/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 07.06.2018
 * Time: 18:52
 */

package pl.poligro.GameEngine;

import pl.poligro.Entities.Entity;
import pl.poligro.GameEngine.Exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class GameState {

    private List<Entity> gameState = new ArrayList<>();

    public Entity getEntityByPosition(final Position position) throws EntityNotFoundException {
        final Optional<Entity> first = gameState
                .stream()
                .filter(entity -> entity.getPosition().equals(position))
                .findFirst();

        return first.orElseThrow(EntityNotFoundException::new);
    }

    public Entity getEntityById(final String  entityId) throws EntityNotFoundException {
        final Optional<Entity> first = gameState
                .stream()
                .filter(entity -> entity.getId().equals(entityId))
                .findFirst();

        return first.orElseThrow(EntityNotFoundException::new);
    }

//    public Boolean setEntity(final Entity entity) throws EntityNotFoundException {
//        Entity entityToReplace = getEntityById(entity.getId());
//
//        if (entityToReplace.getPosition().equals(entity.getPosition()) && gameState.remove(getEntityById(entity.getId()))) {
//            gameState.add(entity);
//            return true;
//        }
//        return false;
//    }

    public Boolean checkIfEntityExists(Entity entity) {
        return gameState.contains(entity);
    }

    public Boolean addEntity(final Entity entity) {
        if (!checkIfEntityExists(entity)) {
            gameState.add(entity);
            return true;
        }
        return false;
    }

    // todo remove casting
    public void addEntities(final Collection entities) {
        for (Object entity : entities) {
            addEntity((Entity) entity);
        }
    }

    public List<Entity> getGameState() {
        return gameState;
    }

    public void setGameState(List<Entity> gameState) {
        this.gameState = gameState;
    }
}
