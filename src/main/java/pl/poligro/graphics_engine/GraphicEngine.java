/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 18:27
 */

package pl.poligro.graphics_engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.poligro.entities.Entity;
import pl.poligro.game_engine.*;
import pl.poligro.graphics_engine.assets.AssetManager;
import pl.poligro.graphics_engine.game_ui.GameUiManager;
import pl.poligro.utils.GlobalConst;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collection;

public class GraphicEngine extends JFrame {

    private Logger log = LoggerFactory.getLogger(GraphicEngine.class.getName());

    private GameState gameState;

    private Graphics g;
    private Graphics bbg;
    private BufferedImage backBuffer;
    private Insets insets;

    public void run(GameState gameState) {

        log.info("Initializing graphics engine...");
        initialize();

        this.gameState = gameState;

        log.info("Graphics engine initialized!");

        while (gameState.isRunning()) {
            long time = System.currentTimeMillis();

            draw();

            time = (1000 / GlobalConst.FPS) - (System.currentTimeMillis() - time);

            if (time > 0) {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        }
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

        g = getGraphics();
        bbg = backBuffer.getGraphics();
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
        if (GameUiManager.isRefreshRequired()) {
            bbg.drawImage(GameUiManager.getUiImage(), GlobalConst.UI_WINDOW_START_POS_X, GlobalConst.UI_WINDOW_START_POS_Y, GlobalConst.UI_WINDOW_WIDTH_PX, GlobalConst.UI_WINDOW_HEIGHT_PX, this);
        }
    }

    private void drawInBottomLayer() {
        drawBackground();
        drawObstacles();
    }

    private void drawBackground() {
        for (int i = 0; i < GlobalConst.GAME_WINDOW_WIDTH_TILES; ++i) {
            for (int j = 0; j < GlobalConst.GAME_WINDOW_HEIGHT_TILES; ++j) {
                drawImage(AssetManager.getGraphicsAsset("SAND_1"), new Position(i, j), GlobalConst.TILE_SIZE_PX, GlobalConst.TILE_SIZE_PX);
            }
        }
    }

    private void drawObstacles() {
        drawEntities(gameState.getObstacles());
    }

    private void drawInMiddleLayer() {

    }

    private void drawInTopLayer() {
        drawMonsters();
        drawPlayer();
        drawFPSCounter();
    }

    private void drawFPSCounter() {
        bbg.setColor(Color.YELLOW);
        bbg.setFont(new Font(Font.DIALOG, Font.BOLD, 32));
        bbg.drawString("FPS: " + "60", GlobalConst.GAME_WINDOW_START_POS_X, 24);
    }

    private void drawMonsters() {
        drawEntities(gameState.getMonsterList());
    }

    private void drawPlayer() {
        drawEntity(gameState.getPlayer());
    }

    private void drawEntities(Collection<Entity> entities) {
        for (Entity entity : entities) {
            drawEntity(entity);
        }
    }

    private void drawEntity(Entity entity) {
        bbg.drawImage(AssetManager.getGraphicsAsset(entity.getGraphicsName()), entity.getPosition().getX() * GlobalConst.TILE_SIZE_PX, entity.getPosition().getY() * GlobalConst.TILE_SIZE_PX, GlobalConst.TILE_SIZE_PX, GlobalConst.TILE_SIZE_PX, this);
    }

    private void drawImage(Image img, Integer posX, Integer posY, Integer sizeX, Integer sizeY) {
        bbg.drawImage(img, posX * GlobalConst.TILE_SIZE_PX, posY * GlobalConst.TILE_SIZE_PX, sizeX, sizeY, this);
    }

    private void drawImage(Image img, Position position, Integer sizeX, Integer sizeY) {
        drawImage(img, position.getX(), position.getY(), sizeX, sizeY);
    }
}
