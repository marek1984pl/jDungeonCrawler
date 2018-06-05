/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 01.06.2018
 * Time: 17:28
 */

package pl.poligro.GameEngine;


import org.slf4j.LoggerFactory;
import pl.poligro.App;
import pl.poligro.GameEngine.Interface.Observable;
import pl.poligro.GameEngine.Interface.Observer;
import pl.poligro.Utils.GlobalConst;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameUi implements Observer {

    org.slf4j.Logger log = LoggerFactory.getLogger(App.class.getName());

    private static Image uiImage = new BufferedImage(GlobalConst.UI_WINDOW_WIDTH_PX, GlobalConst.UI_WINDOW_HEIGHT_PX, BufferedImage.TYPE_INT_RGB);
    Graphics g = uiImage.getGraphics();

    public static Image getUiImage() {
        return uiImage;
    }

    @Override
    public void update(Observable observable) {
        log.debug(getClass().getSimpleName() + " observer updated!");
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GlobalConst.UI_WINDOW_WIDTH_PX, GlobalConst.UI_WINDOW_HEIGHT_PX);
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, GlobalConst.UI_WINDOW_WIDTH_PX - 1, GlobalConst.UI_WINDOW_HEIGHT_PX - 1);
        g.setColor(Color.GREEN);
        g.drawString(((GameState) observable).getPlayer().getName(), 10, 20);
        g.drawString(((GameState) observable).getPlayer().getPosition().toString() , 10, 35);
    }
}
