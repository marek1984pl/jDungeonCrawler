/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 18:27
 */

package pl.poligro.GraphicsEngine;

import org.slf4j.LoggerFactory;
import pl.poligro.Entities.Actor.Monster;
import pl.poligro.App;
import pl.poligro.Entities.Entity;
import pl.poligro.GameEngine.GameEngine;
import pl.poligro.GameEngine.GameUi;
import pl.poligro.GameEngine.MoveDirection;
import pl.poligro.GameEngine.Position;
import pl.poligro.GraphicsEngine.Assets.AssetManager;
import pl.poligro.Utils.GlobalConst;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GraphicEngine extends JFrame {

    private org.slf4j.Logger log = LoggerFactory.getLogger(App.class.getName());

    private boolean isRunning = true;

    private GameEngine gameEngine;

    private Graphics g;
    private Graphics bbg;
    private BufferedImage backBuffer;
    private Insets insets;
    private InputHandler inputHandler;

    public void run(GameEngine gameEngine) {

        log.info("Initializing graphics engine...");
        initialize();
        this.gameEngine = gameEngine;
        log.info("Graphics engine initialized!");

        while (isRunning) {
            long time = System.currentTimeMillis();

            update();
            draw();

            time = (1000 / GlobalConst.FPS) - (System.currentTimeMillis() - time);

            if (time > 0) {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        setVisible(false);
    }

    private void initialize() {
        setTitle("jDungeonCrawler");
        setSize(GlobalConst.MAIN_WINDOW_WIDTH_PX, GlobalConst.MAIN_WINDOW_HEIGHT_PX);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        insets = getInsets();
        setSize(insets.left + getWidth() + insets.right, insets.top + getHeight() + insets.bottom);

        backBuffer = new BufferedImage(GlobalConst.MAIN_WINDOW_WIDTH_PX, GlobalConst.MAIN_WINDOW_HEIGHT_PX, BufferedImage.TYPE_INT_RGB);
        inputHandler = new InputHandler(this);

        g = getGraphics();
        bbg = backBuffer.getGraphics();
    }

    private void update() {

        // 3A) user input - keyboard / mouse
        handleKeyboardInput();

        if (gameEngine.hasChanged()) {
            gameEngine.notifyObservers();
            // 3B) update - process user input, physics / ai / world / network / ui
            gameEngine.updateGameState();
        }
    }

    private void handleKeyboardInput() {
        if (inputHandler.isKeyDown(KeyEvent.VK_ESCAPE)) {
            isRunning = false;
        }
        if (inputHandler.isKeyDown(KeyEvent.VK_D)) {
            gameEngine.movePlayer(MoveDirection.RIGHT);
            gameEngine.nextTurn();
        }
        if (inputHandler.isKeyDown(KeyEvent.VK_A)) {
            gameEngine.movePlayer(MoveDirection.LEFT);
            gameEngine.nextTurn();
        }
        if (inputHandler.isKeyDown(KeyEvent.VK_W)) {
            gameEngine.movePlayer(MoveDirection.UP);
            gameEngine.nextTurn();
        }
        if (inputHandler.isKeyDown(KeyEvent.VK_S)) {
            gameEngine.movePlayer(MoveDirection.DOWN);
            gameEngine.nextTurn();
        }
    }

    private void draw() {
        // 3C) render - game and ui render
        clearGameWindow();

        drawInBottomLayer();
        drawInMiddleLayer();
        drawInTopLayer();

        drawUI();

        g.drawImage(backBuffer, insets.left, insets.top, this);

    }

    private void clearGameWindow() {
        bbg.setColor(Color.BLACK);
        bbg.fillRect(GlobalConst.GAME_WINDOW_START_POS_X, GlobalConst.GAME_WINDOW_START_POS_Y, GlobalConst.GAME_WINDOW_WIDTH_PX, GlobalConst.GAME_WINDOW_HEIGHT_PX);
    }

    private void drawUI() {
//        drawImage(GameUi.getUiImage()., GlobalConst.UI_WINDOW_START_POS_X, GlobalConst.UI_WINDOW_START_POS_Y);
        bbg.drawImage(GameUi.getUiImage(), GlobalConst.UI_WINDOW_START_POS_X, GlobalConst.UI_WINDOW_START_POS_Y, GlobalConst.UI_WINDOW_WIDTH_PX, GlobalConst.UI_WINDOW_HEIGHT_PX, this);
    }

    private void drawInBottomLayer() {
        drawBackground();
        drawObstacles();
    }

    private void drawBackground() {
    }

    private void drawObstacles() {
        drawImage("WALL", 0, 0);
        drawImage("WALL", 0, 1);
        drawImage("WALL", 0, 2);
        drawImage("WALL", 0, 3);
        drawImage("WALL", 1, 0);
        drawImage("WALL", 2, 0);
        drawImage("WALL", 3, 0);
        drawImage("WALL", 4, 0);
    }

    private void drawInMiddleLayer() {

    }

    private void drawInTopLayer() {
        for (Entity entity : gameEngine.getGameState().getGameState()) {
            drawEntity(entity);
        }
//        drawMonsters();
//        drawPlayer();
    }

    private void drawMonsters() {
        for (Monster monster : gameEngine.getMonsterList()) {
            drawEntity(monster);
            drawImage("PRIEST", monster.getPosition());
        }
    }

    private void drawPlayer() {
        drawEntity(gameEngine.getPlayer());
    }

    private void drawEntity(Entity entity) {
        drawImage(entity.getGraphicsName(), entity.getPosition());
    }

    private void drawImage(String assetName, Integer posX, Integer posY) {
        bbg.drawImage(AssetManager.getGraphicsAsset(assetName), posX * GlobalConst.TILE_SIZE_PX, posY * GlobalConst.TILE_SIZE_PX, GlobalConst.TILE_SIZE_PX, GlobalConst.TILE_SIZE_PX, this);
    }

    private void drawImage(String assetName, Position pos) {
        drawImage(assetName, pos.getX(), pos.getY());
    }
}
