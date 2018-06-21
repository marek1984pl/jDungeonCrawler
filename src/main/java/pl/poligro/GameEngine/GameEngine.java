/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 20:06
 */

package pl.poligro.GameEngine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.poligro.App;
import pl.poligro.Entities.Actor.Monster;
import pl.poligro.Entities.Entity;
import pl.poligro.Entities.Obstacle.Wall;
import pl.poligro.Utils.GlobalConst;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//todo change this to gameState
public class GameEngine {

    private Logger log = LoggerFactory.getLogger(App.class.getName());

    private GameState gameState;

    public void initGameWorld(GameState gameState) {

        log.info("Initialize game world....");

        this.gameState = gameState;

        gameState.setTurnNumber(0);

        addEntities(Stream
                // todo change new position constructor
                .generate(() -> new Monster(Position.getNewRandomPosition()))
                .limit(20)
                .collect(Collectors.toList()));

        addEntity(gameState.getPlayer());
        addEntities(gameState.getMonsterList());

        addEntity(new Wall(new Position(0, 0)));
        addEntity(new Wall(new Position(0, 1)));
        addEntity(new Wall(new Position(0, 2)));
        addEntity(new Wall(new Position(0, 3)));
        addEntity(new Wall(new Position(1, 0)));
        addEntity(new Wall(new Position(2, 0)));
        addEntity(new Wall(new Position(3, 0)));
        addEntity(new Wall(new Position(4, 0)));

        addEntities(Stream.
                generate(() -> new Wall(Position.getNewRandomPosition()))
                .limit(50)
                .collect(Collectors.toList()));

        gameState.setChanged();
        log.info("Game world initialized!");
    }

    public Boolean checkIfEntityExists(Entity entity) {
        return gameState.getGameEntities().contains(entity);
    }

    public Boolean addEntity(final Entity entity) {
        if (!checkIfEntityExists(entity)) {
            gameState.getGameEntities().add(entity);
            return true;
        }
        return false;
    }

    public void addEntities(final Collection<Entity> entities) {
        for (Entity entity : entities) {
            addEntity(entity);
        }
    }

    public void updateGameState() {
        // todo think about changed state, compare this to GameState.class and GraphicsEngine
        gameState.setChanged();
        moveMonsters();
    }

    private void moveMonsters() {
        for (Entity monster : gameState.getMonsterList()) {
            MoveDirection newDirection = Position.getRandomDirection();
            if (checkIfNewPositionIsInGameWindow(monster.getPosition(), newDirection)) {
                Entity entityToCheck = gameState.getEntityByPosition(monster.getPosition().getNewPosition(newDirection));
                if (entityToCheck == null) {
                    ((Monster) monster).move(newDirection);
                } else {
                    monster.interactWith(entityToCheck);
                }
            }
        }
    }

    public void nextTurn() {
        gameState.setTurnNumber(gameState.getTurnNumber() + 1);
        updateGameState();
    }

    public void movePlayer(MoveDirection direction) {
        if (checkIfNewPositionIsInGameWindow(gameState.getPlayer().getPosition(), direction)) {
            Entity entityToCheck = gameState.getEntityByPosition(gameState.getPlayer().getPosition().getNewPosition(direction));
            // todo remove null check
            if (entityToCheck == null) {
                gameState.getPlayer().move(direction);
            } else {
                // todo use strategy pattern for interaction
                gameState.getPlayer().interactWith(entityToCheck);
            }
        }
    }

    private boolean checkIfNewPositionIsInGameWindow(Position currentPosition, MoveDirection direction) {
        Position newPosition = currentPosition.getNewPosition(direction);
        return newPosition.getX() >= 0 && newPosition.getX() <= GlobalConst.GAME_WINDOW_WIDTH_TILES - 1 && newPosition.getY() >= 0 && newPosition.getY() <= GlobalConst.GAME_WINDOW_HEIGHT_TILES - 1;
    }

    public void reset() {
        gameState.getGameEntities().clear();
        initGameWorld(gameState);
    }

    public void stopGame() {
        gameState.setRunning(false);
    }
}
