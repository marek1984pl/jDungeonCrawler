package pl.poligro.GameObject;

import pl.poligro.Utils.IdGenerator;

public interface GameObject {

    default String getId() {
        return IdGenerator.generateNewId();
    }
}
