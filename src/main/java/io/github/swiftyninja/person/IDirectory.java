package io.github.swiftyninja.person;

public interface IDirectory {
    Object find(String id);
    void add(Object obj) throws Exception;
    void remove(String id) throws Exception;
}
