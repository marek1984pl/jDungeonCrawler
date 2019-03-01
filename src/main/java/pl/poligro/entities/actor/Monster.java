/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 20:16
 */

package pl.poligro.entities.actor;

import pl.poligro.entities.common.InteractionResult;
import pl.poligro.entities.common.interfaces.Interactable;
import pl.poligro.entities.EntityType;
import pl.poligro.game_engine.Position;
import pl.poligro.utils.NameGenerator;

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
        InteractionResult interactionResult = new InteractionResult();
        interactionResult.setInteractionResultText(getName() + " interacts with " + entity.getEntityType());
        return interactionResult;
    }
}
