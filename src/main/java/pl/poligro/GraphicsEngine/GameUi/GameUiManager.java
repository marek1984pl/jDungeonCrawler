/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 01.06.2018
 * Time: 17:28
 */

package pl.poligro.GraphicsEngine.GameUi;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.poligro.GameEngine.Position;
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

    public static void drawString(String str, Color color, Integer posX, Integer posY) {
        g.setColor(color);
        g.drawString(str, posX, posY);
        g.setColor(Color.WHITE);
    }

    public static void drawStringUsing2Colors(String str1, Color color1, String str2, Color color2, Integer posX, Integer posY) {
        drawString(str1, color1, posX, posY);
        drawString(str2, color2, posX + g.getFontMetrics().stringWidth(str1), posY);
    }

    public static void drawUi() {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GlobalConst.UI_WINDOW_WIDTH_PX, GlobalConst.UI_WINDOW_HEIGHT_PX);
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(0, 0, GlobalConst.UI_WINDOW_WIDTH_PX - 1, GlobalConst.UI_WINDOW_HEIGHT_PX - 1);

        //todo change this to be more universal
        if (gameUiObject.getPlayerName() != null) {
            drawString(gameUiObject.getPlayerName(), Color.GREEN, 10, 20);
            drawString("HP : " + gameUiObject.getPlayerStats().getHealth().toString(), Color.YELLOW, 10, 80);
            g.drawString("STR : " + gameUiObject.getPlayerStats().getStrength().toString(), 10, 95);
            g.drawString("DEX : " + gameUiObject.getPlayerStats().getDexterity().toString(), 10, 110);
            g.drawString("INT : " + gameUiObject.getPlayerStats().getIntelligence().toString(), 10, 125);
            g.drawString("STA : " + gameUiObject.getPlayerStats().getStamina().toString(), 10, 140);
            g.drawString("CRT : " + gameUiObject.getPlayerStats().getCritChance().toString(), 10, 155);
            g.drawString("HIT : " + gameUiObject.getPlayerStats().getHitChance().toString(), 10, 170);
        }
        if (gameUiObject.getPlayerPosition() != null) {
            drawStringUsing2Colors("Player position : ", Color.WHITE, gameUiObject.getPlayerPosition(), Color.YELLOW, 10, 35);
        }
        if (gameUiObject.getTurnNumber() != null) {
            drawStringUsing2Colors("Turn number : ", Color.WHITE, gameUiObject.getTurnNumber(), Color.YELLOW, 190, 20);
        }

        if (gameUiObject.getLog() != null) {
            for (int i = 0; i < getGameUiObject().getLog().size(); ++i) {
                String time = getGameUiObject().getLog().get(i).getInteractionTimeString();
                String event = " " + getGameUiObject().getLog().get(i).getInteractionResultText();
                drawStringUsing2Colors(time, Color.WHITE, event, Color.YELLOW, 10,GlobalConst.UI_WINDOW_HEIGHT_PX - 150 + i * 15);
            }
        }
    }
}
