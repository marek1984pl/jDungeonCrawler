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
import pl.poligro.GraphicsEngine.GameUi.EventLog;

public class Player extends Actor {

    public Player(String name) {
        setName(name);
        setType(ActorType.PLAYER);
        setEntityType(EntityType.PLAYER);
        setPosition(new Position(5, 5));
        setGraphicsName("TROLL");
    }

    @Override
    public InteractionResult interactWith(Interactable entity) {
        InteractionResult result = new InteractionResult();

        if (entity instanceof Monster) {
            result.setInteractionResultText("Interaction with monster!");
        }
        if (entity instanceof Obstacle) {
            result.setInteractionResultText("Interaction with wall!");
        }

        return result;
    }
}
