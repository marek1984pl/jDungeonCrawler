/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 08.11.2018
 * Time: 18:16
 */

package pl.poligro.graphics_engine.assets;

import java.awt.*;

public class GameUiString {

    private String stringKey;
    private String string;
    private Color color;
    private Integer posX;
    private Integer posY;

    public GameUiString(String stringKey, String string, Color color, Integer posX, Integer posY) {
        this.stringKey = stringKey;
        this.string = string;
        this.color = color;
        this.posX = posX;
        this.posY = posY;
    }

    public String getStringKey() {
        return stringKey;
    }

    public String getString() {
        return string;
    }

    public Color getColor() {
        return color;
    }

    public Integer getPosX() {
        return posX;
    }

    public Integer getPosY() {
        return posY;
    }
}
