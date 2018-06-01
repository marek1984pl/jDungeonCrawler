/**
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 18:27
 */

package pl.poligro.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GameWindow extends JFrame {

    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;

    private static final int FPS = 60;

    private boolean isRunning = true;

    private BufferedImage backBuffer;
    private Insets insets;
    private InputHandler inputHandler;

    public void run() {

        initialize();

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
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        insets = getInsets();
        setSize(insets.left + WIDTH + insets.right, insets.top + getHeight() + insets.bottom);

        backBuffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        inputHandler = new InputHandler(this);
    }

    private void update() {
        // 3A) user input - keyboard / mouse
        if (inputHandler.isKeyDown(KeyEvent.VK_RIGHT)) {
            x += 5;
        }
        if (inputHandler.isKeyDown(KeyEvent.VK_LEFT)) {
            x -= 5;
        }
        if (inputHandler.isKeyDown(KeyEvent.VK_UP)) {
            y -= 5;
        }
        if (inputHandler.isKeyDown(KeyEvent.VK_DOWN)) {
            y += 5;
        }

        // 3B) update - process user input, physics / ai / world / network / ui
    }

    int x = 100;
    int y = 100;

    private void draw() {
        // 3C) render - game and ui render
        Graphics g = getGraphics();
        Graphics bbg = backBuffer.getGraphics();

        bbg.setColor(Color.BLACK);
        bbg.fillRect(0, 0, WIDTH, HEIGHT);
        bbg.setColor(Color.YELLOW);

        bbg.drawImage(AssetManager.getGraphicsAssets().get("VAMPIRE"), 100, 100, this);
        bbg.drawImage(AssetManager.getGraphicsAssets().get("PRIEST"), 150, 150, this);
        bbg.drawImage(AssetManager.getGraphicsAssets().get("TROLL"), 200, 150, this);

        bbg.fillOval(x, y, 20, 20);

        g.drawImage(backBuffer, insets.left, insets.top, this);

    }
}
