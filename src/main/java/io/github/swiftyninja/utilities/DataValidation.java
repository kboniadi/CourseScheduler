package io.github.swiftyninja.utilities;

/**
 * DataValidation class
 */
public final class DataValidation {
    /**
     * Constructor
     */
    private DataValidation() {}

    /**
     *
     * @param propertyName used for error output
     * @param value String to check
     * @throws Exception
     */
    public static void ensureNonEmptyString(String propertyName, String value) throws Exception {
        if (value == null || value.trim().equals(""))
            throw new Exception(String.format("%s cannot be null or empty", propertyName));
    }

    /**
     *
     * @param propertyName used for error output
     * @param obj Object to check
     * @throws Exception
     */
    public static void ensureObjectNotNull(String propertyName, Object obj) throws Exception {
        if (obj == null)
            throw new Exception(String.format("%s cannot be null", propertyName));
    }
}
