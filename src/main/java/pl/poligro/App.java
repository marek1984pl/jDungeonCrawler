/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 20:16
 */

package pl.poligro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.poligro.game_engine.GameEngine;
import pl.poligro.game_engine.GameState;
import pl.poligro.graphics_engine.game_ui.GameUiManager;
import pl.poligro.graphics_engine.assets.AssetManager;
import pl.poligro.graphics_engine.GraphicEngine;
import pl.poligro.graphics_engine.InputHandler;

public class App {

    public static void main( String[] args ) {

        Logger log = LoggerFactory.getLogger(App.class.getName());
        log.info("Application started...");

        // 1) init resources - libs, subsystems, assets
        GraphicEngine graphicEngine = new GraphicEngine();
        AssetManager assetManager = new AssetManager();
        GameEngine gameEngine = new GameEngine();
        GameState gameState = new GameState();

        // 2) init gameEngine - load gameEngine assets, init gameEngine world, init physics, init ui
        assetManager.loadImageAssets();
        assetManager.loadStringResources();
        gameEngine.initGameWorld(gameState);

        log.info("Initialize game UI....");
        gameState.addObserver(GameUiManager.getGameUiObject());
        log.info("Game UI initialized!");

        // 3) main loop - user input, update, render, sync
        // 3A) user input - keyboard / mouse
        new InputHandler(graphicEngine, gameEngine);
//        gameEngine.updateGameState();
        graphicEngine.run(gameState);

        // 4) clean up gameEngine - destroy obj, unload assets

        // 5) release resources - unload libs, subsystem, files

        // 6) exit
        log.info("Application ended!");
        System.exit(0);
    }
}
