/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 31.08.2018
 * Time: 17:49
 */

package pl.poligro.entities.common.interfaces;

import pl.poligro.entities.common.InteractionResult;
import pl.poligro.entities.EntityType;

public interface Interactable {
    InteractionResult interactWith(Interactable entity);

    EntityType getEntityType();
}
