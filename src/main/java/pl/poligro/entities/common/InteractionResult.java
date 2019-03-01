/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 31.08.2018
 * Time: 17:55
 */

package pl.poligro.entities.common;

import pl.poligro.utils.GlobalConst;

import java.time.LocalTime;

public class InteractionResult {

    private String interactionResultText;
    private LocalTime interactionTime;

    public InteractionResult() {
        interactionTime = LocalTime.now();
    }

    public InteractionResult(String interactionResultText) {
        this();
        this.interactionResultText = interactionResultText;
    }

    public String getInteractionResultText() {
        return interactionResultText;
    }

    public void setInteractionResultText(String interactionResultText) {
        this.interactionResultText = interactionResultText;
    }

    public String getInteractionTimeString() {
        return interactionTime.format(GlobalConst.DEFAULT_DATE_TIME_FORMATTER);
    }
}
