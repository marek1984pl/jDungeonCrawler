package pl.poligro;

import pl.poligro.GameEngine.Game;
import pl.poligro.GameEngine.AssetManager;
import pl.poligro.GameEngine.GameWindow;

import java.util.logging.Logger;

public class App {

    public static void main( String[] args ) {

        Logger log = Logger.getLogger(App.class.getName());
        log.info("App start");

        // 1) init resources - libs, subsystems, assets
        AssetManager assetManager = new AssetManager();
        GameWindow gameWindow = new GameWindow();
        Game game = new Game();

        // 2) init game - load game assets, init game world, init physics, init ui
        assetManager.loadAssetsData();
        game.initGameWorld();
        game.initUi();

        // 3) main loop - user input, update, render, sync
        gameWindow.run();

        // 4) clean up game - destroy obj, unload assets

        // 5) release resources - unload libs, subsystem, files

        // 6) exit
        log.info("App end");
    }
}
