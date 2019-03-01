/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 31.08.2018
 * Time: 18:32
 */

package pl.poligro.graphics_engine.game_ui;

import pl.poligro.entities.actor.Statistics;
import pl.poligro.entities.common.InteractionResult;
import pl.poligro.game_engine.GameState;
import pl.poligro.game_engine.interfaces.Observable;
import pl.poligro.game_engine.interfaces.Observer;

import java.util.ArrayList;
import java.util.List;


public class GameUiObject implements Observer {

    private String playerName;
    private String playerPosition;
    private String turnNumber;
    private Statistics playerStats;
    private List<InteractionResult> log = new ArrayList<>();

    private Boolean refreshRequired = false;

    public GameUiObject() {
    }

    GameUiObject getGameUiObject() {
        return this;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerPosition() {
        return playerPosition;
    }

    public List<InteractionResult> getLog() {
        return log;
    }

    public String getTurnNumber() {
        return turnNumber;
    }

    public Boolean isRefreshRequired() {
        return refreshRequired;
    }

    public Statistics getPlayerStats() {
        return playerStats;
    }

    @Override
    public void update(Observable observable) {
        if (observable instanceof GameState) {
            playerStats = ((GameState) observable).getPlayer().getStats();
            playerName = ((GameState) observable).getPlayer().getName();
            playerPosition = ((GameState) observable).getPlayer().getPosition().toString();
            turnNumber = ((GameState) observable).getTurnNumber().toString();
            refreshRequired = true;
        } else if (observable instanceof EventLog) {
            log.clear();
            log.addAll(((EventLog) observable).getLog());
            refreshRequired = true;
        } else {
            refreshRequired = false;
        }

    }
}
