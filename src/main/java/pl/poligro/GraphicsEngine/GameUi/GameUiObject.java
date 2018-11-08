/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 31.08.2018
 * Time: 18:32
 */

package pl.poligro.GraphicsEngine.GameUi;

import pl.poligro.Entities.Actor.Statistics;
import pl.poligro.Entities.Common.InteractionResult;
import pl.poligro.GameEngine.GameState;
import pl.poligro.GameEngine.Interface.Observable;
import pl.poligro.GameEngine.Interface.Observer;

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
            log.addAll(((EventLog) observable).getEventLog());
            refreshRequired = true;
        } else {
            refreshRequired = false;
        }

    }
}
