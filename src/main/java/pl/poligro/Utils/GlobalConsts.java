/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 01.06.2018
 * Time: 17:44
 */

package pl.poligro.Utils;

public interface GlobalConsts {

    int TILE_SIZE = 48;

    int WIDTH_TILES = 38;
    int HEIGHT_TILES = 20;

    int UI_WIDTH = 8;
    int UI_HEIGHT = 0;

    int GAME_WIDTH_TILES = WIDTH_TILES - UI_WIDTH;
    int GAME_HEIGHT_TILES = HEIGHT_TILES - UI_HEIGHT;

}
