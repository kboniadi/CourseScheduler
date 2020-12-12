package io.github.swiftyninja.utilities;

public class SequencialID implements IdGenerator {
    private static int currID = 1;
    @Override
    public String generateId() {
        return "" + currID++;
    }
}
