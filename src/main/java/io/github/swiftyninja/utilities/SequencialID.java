package io.github.swiftyninja.utilities;

/**
 * SequentialID class
 */
public class SequencialID implements IdGenerator {
    private static int currID = 1;

    /**
     *
     * @return id
     */
    @Override
    public String generateId() {
        return "" + currID++;
    }
}
