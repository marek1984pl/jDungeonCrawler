/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 31.05.2018
 * Time: 19:29
 */

package pl.poligro.graphics_engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.poligro.game_engine.GameEngine;
import pl.poligro.game_engine.MoveDirection;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {

    private Logger log = LoggerFactory.getLogger(InputHandler.class.getName());

    private GameEngine gameEngine;

    public InputHandler(Component c, GameEngine gameEngine) {
        c.addKeyListener(this);
        this.gameEngine = gameEngine;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 3B) update - process user input, physics / ai / world / network / ui
        log.debug("Key pressed : " + e.getKeyCode());

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            gameEngine.stopGame();
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            gameEngine.movePlayer(MoveDirection.RIGHT);
            gameEngine.nextTurn();
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            gameEngine.movePlayer(MoveDirection.LEFT);
            gameEngine.nextTurn();
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            gameEngine.movePlayer(MoveDirection.UP);
            gameEngine.nextTurn();
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            gameEngine.movePlayer(MoveDirection.DOWN);
            gameEngine.nextTurn();
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            gameEngine.reset();
        }
    }
}
