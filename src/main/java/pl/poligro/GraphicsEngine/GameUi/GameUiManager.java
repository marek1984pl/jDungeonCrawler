/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 01.06.2018
 * Time: 17:28
 */

package pl.poligro.GraphicsEngine.GameUi;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.poligro.Utils.GlobalConst;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameUiManager {

    private Logger log = LoggerFactory.getLogger(getClass().getName());

    private static Image uiImage = new BufferedImage(GlobalConst.UI_WINDOW_WIDTH_PX, GlobalConst.UI_WINDOW_HEIGHT_PX, BufferedImage.TYPE_INT_RGB);
    private static Graphics g = uiImage.getGraphics();
    private static GameUiObject gameUiObject = new GameUiObject();

    public static Image getUiImage() {
        return uiImage;
    }

    public static Boolean isRefreshRequired() {
        if (gameUiObject.isRefreshRequired()) {
            drawUi();
        }
        return gameUiObject.isRefreshRequired();
    }

    public static GameUiObject getGameUiObject() {
        return gameUiObject;
    }

    public static void drawUi() {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GlobalConst.UI_WINDOW_WIDTH_PX, GlobalConst.UI_WINDOW_HEIGHT_PX);
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, GlobalConst.UI_WINDOW_WIDTH_PX - 1, GlobalConst.UI_WINDOW_HEIGHT_PX - 1);
        g.setColor(Color.GREEN);

        if (gameUiObject.getPlayerName() != null) {
            g.drawString(gameUiObject.getPlayerName(), 10, 20);
        }
        if (gameUiObject.getPlayerPosition() != null) {
            g.setColor(Color.WHITE);
            g.drawString("Player position : ", 10, 35);
            g.setColor(Color.YELLOW);
            g.drawString(gameUiObject.getPlayerPosition(), 10, 50);
        }
        if (gameUiObject.getTurnNumber() != null) {
            g.setColor(Color.WHITE);
            g.drawString("Turn number : ", 10, 65);
            g.setColor(Color.YELLOW);
            g.drawString(gameUiObject.getTurnNumber(), 10, 80);

        }

        if (gameUiObject.getLog() != null) {
            for (int i = 0; i < getGameUiObject().getLog().size(); ++i) {
                String time = getGameUiObject().getLog().get(i).getInteractionTimeString();
                g.setColor(Color.WHITE);
                g.drawString(time, 10, GlobalConst.UI_WINDOW_HEIGHT_PX - 150 + i * 15);
                String event = " " + getGameUiObject().getLog().get(i).getInteractionResultText();
                g.setColor(Color.YELLOW);
                g.drawString(event, 60, GlobalConst.UI_WINDOW_HEIGHT_PX - 150 + i * 15);
            }
        }
    }
}
