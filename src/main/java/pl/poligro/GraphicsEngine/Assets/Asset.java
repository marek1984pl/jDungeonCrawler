/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 31.05.2018
 * Time: 10:21
 */

package pl.poligro.GraphicsEngine.Assets;

import java.util.HashMap;
import java.util.Map;

public class Asset<T> {

    private Map<String, T> assets = new HashMap<>();

    public T get(String nameOfObject) {
        return assets.get(nameOfObject);
    }

    public void createNewAsset(String nameOfObject, T asset) {
        assets.put(nameOfObject, asset);
    }
}
