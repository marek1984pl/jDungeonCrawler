/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 20:16
 */

package pl.poligro;

import pl.poligro.GameEngine.GameState;
import pl.poligro.GraphicsEngine.Assets.AssetManager;
import pl.poligro.GraphicsEngine.GraphicEngine;

import java.util.logging.Logger;

public class App {

    public static void main( String[] args ) {

        Logger log = Logger.getLogger(App.class.getName());
        log.info("Application started...");

        // 1) init resources - libs, subsystems, assets
        GraphicEngine graphicEngine = new GraphicEngine();
        AssetManager assetManager = new AssetManager();
        GameState gameState = new GameState();

        // 2) init gameState - load gameState assets, init gameState world, init physics, init ui
        assetManager.loadAssetsData();
        gameState.initGameWorld();
        gameState.initUi();

        // 3) main loop - user input, update, render, sync
        graphicEngine.run(gameState);

        // 4) clean up gameState - destroy obj, unload assets

        // 5) release resources - unload libs, subsystem, files

        // 6) exit
        log.info("Application ended!");
    }
}
