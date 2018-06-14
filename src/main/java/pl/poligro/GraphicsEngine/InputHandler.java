/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 31.05.2018
 * Time: 19:29
 */

package pl.poligro.GraphicsEngine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.poligro.App;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    private Logger log = LoggerFactory.getLogger(App.class.getName());

    private boolean[] keys = new boolean[256];

    public InputHandler(Component c) {
        c.addKeyListener(this);
    }

    public boolean isKeyDown(int keyCode) {
        if (keyCode > 0 && keyCode < 256) {
            return keys[keyCode];
        }
        return false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() > 0 && e.getKeyCode() < 256) {
            keys[e.getKeyCode()] = true;
            log.debug("Key pressed: " + e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() > 0 && e.getKeyCode() < 256) {
            keys[e.getKeyCode()] = false;
            log.debug("Key released: " + e.getKeyCode());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
