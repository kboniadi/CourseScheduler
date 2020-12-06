package io.github.swiftyninja.person;

public interface IDirectory {
    Person find(String id);
    void add(Person obj) throws Exception;
    void remove(String id) throws Exception;
    void replace(String id, Person obj) throws Exception;
}
