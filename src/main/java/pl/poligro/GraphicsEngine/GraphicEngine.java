/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 18:27
 */

package pl.poligro.GraphicsEngine;

import pl.poligro.Actor.Monster;
import pl.poligro.GameEngine.GameState;
import pl.poligro.GameEngine.MoveDirection;
import pl.poligro.GraphicsEngine.Assets.AssetManager;
import pl.poligro.Utils.GlobalConsts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

public class GraphicEngine extends JFrame {

    private Logger log = Logger.getLogger(getClass().getName());

    private static final int FPS = 60;

    private boolean isRunning = true;

    private GameState gameState;

    private Graphics g;
    private Graphics bbg;
    private BufferedImage backBuffer;
    private Insets insets;
    private InputHandler inputHandler;

    public void run(GameState gameState) {

        log.info("Initializing graphics engine...");
        initialize();
        this.gameState = gameState;
        log.info("Graphics engine initialized!");

        while (isRunning) {
            long time = System.currentTimeMillis();

            update();
            draw();

            time = (1000 / FPS) - (System.currentTimeMillis() - time);

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
        setSize(GlobalConsts.MAIN_WINDOW_WIDTH_PX, GlobalConsts.MAIN_WINDOW_HEIGHT_PX);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        insets = getInsets();
        setSize(insets.left + getWidth() + insets.right, insets.top + getHeight() + insets.bottom);

        backBuffer = new BufferedImage(GlobalConsts.MAIN_WINDOW_WIDTH_PX, GlobalConsts.MAIN_WINDOW_HEIGHT_PX, BufferedImage.TYPE_INT_RGB);
        inputHandler = new InputHandler(this);

        g = getGraphics();
        bbg = backBuffer.getGraphics();
    }

    private void update() {

        // 3A) user input - keyboard / mouse
        handleKeyboardInput();

        if (gameState.isChangeTurn()) {
            // 3B) update - process user input, physics / ai / world / network / ui
            gameState.updateGameState();
            gameState.setChangeTurn(false);
        }
    }

    private void handleKeyboardInput() {
        if (inputHandler.isKeyDown(KeyEvent.VK_D)) {
            gameState.movePlayer(MoveDirection.RIGHT);
            gameState.nextTurn();
        }
        if (inputHandler.isKeyDown(KeyEvent.VK_A)) {
            gameState.movePlayer(MoveDirection.LEFT);
            gameState.nextTurn();
        }
        if (inputHandler.isKeyDown(KeyEvent.VK_W)) {
            gameState.movePlayer(MoveDirection.UP);
            gameState.nextTurn();
        }
        if (inputHandler.isKeyDown(KeyEvent.VK_S)) {
            gameState.movePlayer(MoveDirection.DOWN);
            gameState.nextTurn();
        }
    }

    int x = 132;
    int y = 132;

    private void draw() {
        // 3C) render - game and ui render
        bbg.setColor(Color.BLACK);
        bbg.fillRect(0, 0, GlobalConsts.MAIN_WINDOW_WIDTH_PX, GlobalConsts.MAIN_WINDOW_HEIGHT_PX);

        drawInBottomLayer();
        drawInMiddleLayer();
        drawInTopLayer();

        drawUI();

        g.drawImage(backBuffer, insets.left, insets.top, this);

    }

    private void drawUI() {
    }

    private void drawInBottomLayer() {
        bbg.drawImage(AssetManager.getGraphicsAssets().get("WALL"), 100, 300, GlobalConsts.TILE_SIZE_PX, GlobalConsts.TILE_SIZE_PX, this);
        bbg.drawImage(AssetManager.getGraphicsAssets().get("WALL"), 300+48, 300, GlobalConsts.TILE_SIZE_PX, GlobalConsts.TILE_SIZE_PX, this);
        bbg.drawImage(AssetManager.getGraphicsAssets().get("WALL"), 300+96, 300, GlobalConsts.TILE_SIZE_PX, GlobalConsts.TILE_SIZE_PX, this);
        bbg.drawImage(AssetManager.getGraphicsAssets().get("WALL"), 300, 300+48, GlobalConsts.TILE_SIZE_PX, GlobalConsts.TILE_SIZE_PX, this);
    }

    private void drawInMiddleLayer() {
        bbg.drawImage(AssetManager.getGraphicsAssets().get("VAMPIRE"), 100, 100, GlobalConsts.TILE_SIZE_PX, GlobalConsts.TILE_SIZE_PX, this);
        bbg.drawImage(AssetManager.getGraphicsAssets().get("PRIEST"), 150, 150, GlobalConsts.TILE_SIZE_PX, GlobalConsts.TILE_SIZE_PX, this);
    }

    private void drawInTopLayer() {
        drawPlayer();
        drawMonsters();
    }

    private void drawMonsters() {
        for (Monster monster : gameState.getMonsterList()) {
            bbg.drawImage(AssetManager.getGraphicsAssets().get("PRIEST"), monster.getPosition().getX() * GlobalConsts.TILE_SIZE_PX, monster.getPosition().getY() * GlobalConsts.TILE_SIZE_PX, GlobalConsts.TILE_SIZE_PX, GlobalConsts.TILE_SIZE_PX, this);
        }
    }

    private void drawPlayer() {
        bbg.drawImage(AssetManager.getGraphicsAssets().get("TROLL"), gameState.getPlayer().getPosition().getX() * GlobalConsts.TILE_SIZE_PX, gameState.getPlayer().getPosition().getY() * GlobalConsts.TILE_SIZE_PX, GlobalConsts.TILE_SIZE_PX, GlobalConsts.TILE_SIZE_PX, this);
    }
}
