/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 31.08.2018
 * Time: 17:49
 */

package pl.poligro.Entities.Common.Interfaces;

import pl.poligro.Entities.Common.InteractionResult;
import pl.poligro.Entities.Entity;
import pl.poligro.GraphicsEngine.GameUi.EventLog;

public interface Interactable {
    InteractionResult interactWith(Interactable entity);
}
