package io.github.swiftyninja.utilities;

import java.util.UUID;

public class UUIDGenerator implements IdGenerator {
    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
