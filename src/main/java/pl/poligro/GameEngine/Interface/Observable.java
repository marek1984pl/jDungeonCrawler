/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 05.06.2018
 * Time: 21:33
 */

package pl.poligro.GameEngine.Interface;

import java.util.ArrayList;
import java.util.List;

public interface Observable {

    List<Observer> observers = new ArrayList<>();

    default void addObserver(Observer observer) {
        if (observer == null) {
            throw new NullPointerException();
        }
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    default void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    default void notifyObservers() {
        clearChanged();
        for (Observer o: observers) {
            o.update(this);
        }
    }

    default void notifyObservers(Observer observer) {
        clearChanged();
        observer.update(this);
    }

    default void deleteObservers() {
        observers.clear();
    }

    void setChanged();

    void clearChanged();

    Boolean hasChanged();

    default Integer countObservers() {
        return observers.size();
    }
}
