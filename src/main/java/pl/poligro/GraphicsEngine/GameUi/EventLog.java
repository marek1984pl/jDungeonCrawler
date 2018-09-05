/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 31.08.2018
 * Time: 19:29
 */

package pl.poligro.GraphicsEngine.GameUi;

import pl.poligro.Entities.Common.InteractionResult;
import pl.poligro.GameEngine.Interface.Observable;

import java.util.ArrayDeque;
import java.util.Deque;

public class EventLog implements Observable {

    private Deque<InteractionResult> eventLog = new ArrayDeque<>();

    public void addToEventLog(InteractionResult interactionResult) {
        if (eventLog.size() >= 10) {
            eventLog.removeFirst();
        }

        eventLog.addLast(interactionResult);
        setChanged();
    }

    public Deque<InteractionResult> getEventLog() {
        return eventLog;
    }
}
