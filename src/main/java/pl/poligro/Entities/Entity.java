/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 20:16
 */

package pl.poligro.Entities;

import pl.poligro.Entities.Common.Interfaces.Interactable;
import pl.poligro.GameEngine.Position;
import pl.poligro.Utils.IdGenerator;

public abstract class Entity implements Interactable {

    private final String id = IdGenerator.generateNewId();
    private Position position;
    private EntityType entityType;

    private String graphicsName;

    public String getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public String getGraphicsName() {
        return graphicsName;
    }

    public void setGraphicsName(String graphicsName) {
        this.graphicsName = graphicsName;
    }
}
