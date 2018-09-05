/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 20:16
 */

package pl.poligro.Entities.Actor;

import pl.poligro.Entities.Common.InteractionResult;
import pl.poligro.Entities.Common.Interfaces.Interactable;
import pl.poligro.Entities.Entity;
import pl.poligro.Entities.EntityType;
import pl.poligro.Entities.Obstacle.Obstacle;
import pl.poligro.GameEngine.Position;
import pl.poligro.Utils.NameGenerator;

public class Monster extends Actor {

    public Monster() {
        setName(NameGenerator.generateName());
        setType(ActorType.MONSTER);
        setEntityType(EntityType.MONSTER);
        setGraphicsName("PRIEST");
    }

    public Monster(Position position) {
        this();
        setPosition(position);
    }

    @Override
    public InteractionResult interactWith(Interactable entity) {

//        if (entity instanceof Player) {
//            result.setInteractionResultText("Interaction with player!");
//        } else if (entity instanceof Obstacle) {
//            result.setInteractionResultText("Monster interacts with wall!");
//        } else if (entity instanceof Monster) {
//            result.setInteractionResultText("Monster meets monster!");
//        }
        return new InteractionResult();
    }
}
