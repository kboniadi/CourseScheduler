package io.github.swiftyninja.person;

/**
 *  Directory like function template
 */
public interface IDirectory {
    /**
     *
     * @param id object you want to search for
     * @return Object type for flexibility
     */
    Object find(String id);

    /**
     *
     * @param obj item to add to dir
     * @throws Exception nullException
     */
    void add(Object obj) throws Exception;

    /**
     *
     * @param id object you want to search for
     * @throws Exception
     */
    void remove(String id) throws Exception;
}
