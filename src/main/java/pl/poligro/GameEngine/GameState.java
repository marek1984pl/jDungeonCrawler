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

    public void initGameWorld() {

        log.info("Initialize game world....");

        monsterList = Stream
                .generate(() -> new Monster(new Position(rand.nextInt(GlobalConsts.WIDTH_TILES), rand.nextInt(GlobalConsts.HEIGHT_TILES))))
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
            monster.move(Position.randomDirection());
        }
    }

    public void movePlayer(MoveDirection direction) {
        player.move(direction);
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
}
