/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 20:06
 */

package pl.poligro.GameEngine;

import pl.poligro.Actor.Monster;
import pl.poligro.Actor.Player;
import pl.poligro.Utils.GlobalConsts;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameState {

    private Logger log = Logger.getLogger(getClass().getName());

    private Random rand = new Random();

    private Player player = new Player("Zodgar");
    private List<Monster> monsterList;

    private Integer turnNumber = 0;
    private boolean changeTurn = false;

    public void initGameWorld() {

        log.info("Initialize game world....");

        monsterList = Stream
                // todo change new position constructor
                .generate(() -> new Monster(new Position(rand.nextInt(GlobalConsts.GAME_WIDTH_TILES), rand.nextInt(GlobalConsts.GAME_HEIGHT_TILES))))
                .limit(20)
                .collect(Collectors.toList());

//        monsterList.forEach(System.out::println);

        log.info("Game world initialized!");
    }

    public void initUi() {
        log.info("Initialize game UI....");
        log.info("Game UI initialized!");

    }

    public void updateGameState() {
        moveMonsters();
    }

    private void moveMonsters() {
        for (Monster monster : monsterList) {
            MoveDirection newDirection = Position.randomDirection();
            if (checkIfNewPositionIsInGameWindow(monster.getPosition(), newDirection)) {
                monster.move(newDirection);
            }
        }
    }

    public Integer nextTurn() {
        changeTurn = true;
        return ++turnNumber;
    }

    public void movePlayer(MoveDirection direction) {
        if (checkIfNewPositionIsInGameWindow(player.getPosition(), direction)) {
            player.move(direction);
        }
    }

    private boolean checkIfNewPositionIsInGameWindow(Position currentPosition, MoveDirection direction) {
        Position newPosition = currentPosition.newPosition(direction);
        return newPosition.getX() >= 0 && newPosition.getX() <= GlobalConsts.GAME_WIDTH_TILES && newPosition.getY() >= 0 && newPosition.getY() <= GlobalConsts.GAME_HEIGHT_TILES;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Monster> getMonsterList() {
        return monsterList;
    }

    public void setMonsterList(List<Monster> monsterList) {
        this.monsterList = monsterList;
    }

    public Integer getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(Integer turnNumber) {
        this.turnNumber = turnNumber;
    }

    public boolean isChangeTurn() {
        return changeTurn;
    }

    public void setChangeTurn(boolean changeTurn) {
        this.changeTurn = changeTurn;
    }
}
