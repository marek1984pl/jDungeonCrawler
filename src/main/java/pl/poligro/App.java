/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 20:16
 */

package pl.poligro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.poligro.GameEngine.Exceptions.EntityNotFoundException;
import pl.poligro.GameEngine.GameEngine;
import pl.poligro.GameEngine.GameUi;
import pl.poligro.GraphicsEngine.Assets.AssetManager;
import pl.poligro.GraphicsEngine.GraphicEngine;

public class App {

    public static void main( String[] args ) {

        Logger log = LoggerFactory.getLogger(App.class.getName());
        log.info("Application started...");

        // 1) init resources - libs, subsystems, assets
        GraphicEngine graphicEngine = new GraphicEngine();
        AssetManager assetManager = new AssetManager();
        GameEngine gameEngine = new GameEngine();
        GameUi gameUi = new GameUi();

        // 2) init gameEngine - load gameEngine assets, init gameEngine world, init physics, init ui
        assetManager.loadAssetsData();
        gameEngine.initGameWorld();

        log.info("Initialize game UI....");
        gameEngine.addObserver(gameUi);
        log.info("Game UI initialized!");

        // 3) main loop - user input, update, render, sync
        graphicEngine.run(gameEngine);

        // 4) clean up gameEngine - destroy obj, unload assets

        // 5) release resources - unload libs, subsystem, files

        // 6) exit
        log.info("Application ended!");
        System.exit(0);
    }
}
