package io.github.swiftyninja.utilities;

import java.util.UUID;

/**
 * UUIDGenerator class
 */
public class UUIDGenerator implements IdGenerator {
    /**
     *
     * @return id
     */
    public String generateId() {
        String temp = UUID.randomUUID().toString();
        // shrinks number to 7 chars
        return temp.substring(0, temp.indexOf("-"));
    }

}
