/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 20:16
 */

package pl.poligro.Entities.Actor;

import pl.poligro.Entities.Common.InteractionResult;
import pl.poligro.Entities.Common.Interfaces.Interactable;
import pl.poligro.Entities.EntityType;
import pl.poligro.GameEngine.Position;

public class Player extends Actor {

    public Player(String name) {
        setName(name);
        setType(ActorType.PLAYER);
        setEntityType(EntityType.PLAYER);
        setPosition(new Position(5, 5));
        setGraphicsName("TROLL");
        createStats(6, 4, 4, 7, ActorType.PLAYER);
    }

    @Override
    public InteractionResult interactWith(Interactable entity) {
        InteractionResult result = new InteractionResult();

        switch (entity.getEntityType()) {
            case MONSTER:
                result.setInteractionResultText("Interaction with monster!");
                break;
            case WALL:
                result.setInteractionResultText("Interaction with wall!");
                break;
            default:
                result.setInteractionResultText("Unknown interaction...");
                break;
        }

        return result;
    }
}
