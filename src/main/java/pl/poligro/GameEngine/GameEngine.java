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
import pl.poligro.Entities.Common.InteractionResult;
import pl.poligro.Entities.Entity;
import pl.poligro.Entities.Obstacle.Wall;
import pl.poligro.GraphicsEngine.GameUi.EventLog;
import pl.poligro.Utils.GlobalConst;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameEngine {

    private Logger log = LoggerFactory.getLogger(App.class.getName());

    private GameState gameState;
    private EventLog eventLog = new EventLog();

    public void initGameWorld(GameState gameState) {

        log.info("Initialize game world....");

        this.gameState = gameState;

        gameState.setTurnNumber(0);

        addEntities(Stream
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
        return gameState.getEntityByPosition(entity.getPosition()) != null;
    }

    public Boolean addEntity(final Entity entity) {
        if (!checkIfEntityExists(entity)) {
            gameState.getAllGameEntities().add(entity);
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
        gameState.nextTurn();
        moveMonsters();
        gameState.setChanged();
    }

    private void moveMonsters() {
        for (Entity monster : gameState.getMonsterList()) {
            MoveDirection newDirection = Position.getRandomDirection();
            if (checkIfNewPositionIsInGameWindow(monster.getPosition(), newDirection)) {
                Entity entityToCheck = gameState.getEntityByPosition(monster.getPosition().getNewPosition(newDirection));
                if (entityToCheck == null) {
                    ((Monster) monster).move(newDirection);
                } else {
                    InteractionResult interactionResult = monster.interactWith(entityToCheck);

                    if (interactionResult.getInteractionResultText() != null) {
                        eventLog.addToEventLog(interactionResult);
                    }
                }
            }
        }
    }

    public void nextTurn() {
        updateGameState();
    }

    public void movePlayer(MoveDirection direction) {
        if (checkIfNewPositionIsInGameWindow(gameState.getPlayer().getPosition(), direction)) {
            Entity entityToCheck = gameState.getEntityByPosition(gameState.getPlayer().getPosition().getNewPosition(direction));
            if (entityToCheck == null) {
                gameState.getPlayer().move(direction);
            } else {
                // todo use strategy pattern for interaction
                InteractionResult interactionResult = gameState.getPlayer().interactWith(entityToCheck);
                eventLog.addToEventLog(interactionResult);
            }
        }
    }

    private boolean checkIfNewPositionIsInGameWindow(Position currentPosition, MoveDirection direction) {
        Position newPosition = currentPosition.getNewPosition(direction);
        return newPosition.getX() >= 0 && newPosition.getX() <= GlobalConst.GAME_WINDOW_WIDTH_TILES - 1 && newPosition.getY() >= 0 && newPosition.getY() <= GlobalConst.GAME_WINDOW_HEIGHT_TILES - 1;
    }

    public void reset() {
        gameState.getAllGameEntities().clear();
        eventLog.clearLog();
        initGameWorld(gameState);
    }

    public void stopGame() {
        gameState.setRunning(false);
    }
}
