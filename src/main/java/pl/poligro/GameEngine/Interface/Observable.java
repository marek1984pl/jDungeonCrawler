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
    List<Boolean> changed = new ArrayList<>();

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

    default void notifyObserver(Observer observer) {
        clearChanged();
        observer.update(this);
    }

    default void deleteObservers() {
        observers.clear();
    }

    default void setChanged() {
        changed.add(true);
        notifyObservers();
    }

    default void clearChanged() {
        changed.clear();
    }

    default Boolean hasChanged() {
        return !changed.isEmpty();
    }

    default Integer countObservers() {
        return observers.size();
    }
}
