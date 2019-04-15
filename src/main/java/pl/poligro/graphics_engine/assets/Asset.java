/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 31.05.2018
 * Time: 10:21
 */

package pl.poligro.graphics_engine.assets;

import pl.poligro.graphics_engine.exceptions.AssetNotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Asset<T> {

    //todo make asset as name, type, etc...
    //todo move this methods to assetManager
    //todo remove map
    //todo make this class abstract and more universal, eg. create new StringAsset class, imageAsset class
    //todo add asset type enum
    //todo add asset path, maybe loadAsset method? loadAsst method abstract... eg. load string is different than load graphic asset (string has colors, position etc)
    //todo load on demand - getAsset (if loaded return, if not load and return)

    private String name;
    private AssetType type;
    private String path;
    private T assetData;

    public Asset() {
    }

    public Asset(String name, AssetType type, String path, T assetData) {
        this.name = name;
        this.type = type;
        this.path = path;
        this.assetData = assetData;
    }

    public String getName() {
        return name;
}

    public T getAssetData() {
        return assetData;
    }

    //    private Map<String, T> assets = new HashMap<>();
}
