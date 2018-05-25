package pl.poligro;


import pl.poligro.Actor.Monster;
import pl.poligro.Actor.Player;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main( String[] args ) {

        Player player = new Player("Zodgar");
        System.out.println(player);

        List<Monster> monsterList = Stream.generate(Monster::new).limit(20).collect(Collectors.toList());
        monsterList.forEach(System.out::println);
    }
}
