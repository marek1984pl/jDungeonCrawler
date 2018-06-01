/**
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 20:06
 */

package pl.poligro.GameEngine;

import pl.poligro.Actor.Monster;
import pl.poligro.Actor.Player;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {

    public void initGameWorld() {

        Player player = new Player("Zodgar");
        System.out.println(player);

        List<Monster> monsterList = Stream
                .generate(Monster::new)
                .limit(20)
                .collect(Collectors.toList());

        monsterList.forEach(System.out::println);

    }

    public void initUi() {
    }
}
