package io.github.swiftyninja.utilities;

public final class DataValidation {
    private DataValidation() {}

    public static void ensureNonEmptyString(String propertyName, String value) throws Exception {
        if (value == null || value.trim().equals(""))
            throw new Exception(String.format("%s cannot be null or empty", propertyName));
    }

    public static void ensureObjectNotNull(String propertyName, Object obj) throws Exception {
        if (obj == null)
            throw new Exception(String.format("%s cannot be null", propertyName));
    }
}
