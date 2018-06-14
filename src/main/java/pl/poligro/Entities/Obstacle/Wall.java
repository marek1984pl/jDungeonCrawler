/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 07.06.2018
 * Time: 18:40
 */

package pl.poligro.Entities.Obstacle;

import pl.poligro.Entities.Entity;
import pl.poligro.Entities.EntityType;
import pl.poligro.GameEngine.Position;

public class Wall extends Obstacle {

    public Wall(Position position) {
        setGraphicsName("WALL");
        setEntityType(EntityType.WALL);
        setPosition(position);
    }

    @Override
    public void interactWith(Entity entity) {

    }
}
