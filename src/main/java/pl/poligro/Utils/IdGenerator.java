package pl.poligro.Utils;

import java.util.UUID;

public class IdGenerator {

    public static String generateNewId() {
        return UUID.randomUUID().toString();
    }
}
