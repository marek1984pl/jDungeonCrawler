/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 07.06.2018
 * Time: 18:40
 */

package pl.poligro.entities.obstacle;

import pl.poligro.entities.common.InteractionResult;
import pl.poligro.entities.common.interfaces.Interactable;
import pl.poligro.entities.EntityType;
import pl.poligro.game_engine.Position;

public class Wall extends Obstacle {

    public Wall(Position position) {
        setGraphicsName("WALL");
        setEntityType(EntityType.WALL);
        setPosition(position);
    }

    @Override
    public InteractionResult interactWith(Interactable entity) {
        return null;
    }
}
