/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 31.08.2018
 * Time: 19:29
 */

package pl.poligro.graphics_engine.game_ui;

import pl.poligro.entities.common.InteractionResult;
import pl.poligro.game_engine.interfaces.Observable;

import java.util.ArrayDeque;
import java.util.Deque;

public class EventLog implements Observable {

    private Deque<InteractionResult> log = new ArrayDeque<>();

    public void addToEventLog(InteractionResult interactionResult) {
        if (log.size() >= 10) {
            log.removeFirst();
        }

        log.addLast(interactionResult);
        setChanged();
    }

    public Deque<InteractionResult> getLog() {
        return log;
    }

    public void clearLog() {
        log.clear();
        setChanged();
    }
}
