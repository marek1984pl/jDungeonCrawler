/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 20:16
 */

package pl.poligro.utils;

import java.util.UUID;

public class IdGenerator {

    private IdGenerator() {
    }

    public static String generateNewId() {
        return UUID.randomUUID().toString();
    }
}
