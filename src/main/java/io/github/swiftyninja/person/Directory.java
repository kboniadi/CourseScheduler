package io.github.swiftyninja.person;

import java.util.LinkedList;

public class Directory implements IDirectory {
    private LinkedList<Person> directory;

    public Directory() {
        directory = new LinkedList<>();
    }

    @Override
    public Person find(String id) {
        for (Person p : directory) {
            if (p.getId().equals(id))
                return p;
        }
        return null;
    }

    @Override
    public void add(Person person) throws Exception {
        if (!directory.contains(person))
            directory.add(person);
        else
            throw new Exception("Person already exists in directory");
    }

    @Override
    public void remove(String id) throws Exception {
        Person temp = find(id);
        if (temp != null)
            directory.remove(temp);
        else
            throw new Exception("id not in directory");
    }

    @Override
    public void replace(String id, Person person) throws Exception {
        Person temp = find(id);
        if (temp != null) {
            directory.remove(temp);
            directory.add(person);
        } else {
            throw new Exception("id was not in database");
        }
    }

    public void printStudents() {
        System.out.println("Student's in Directory:");
        for (Person p : directory) {
            if (p instanceof Student)
                System.out.println(p);
        }
    }

    public void printFaculty() {
        System.out.println("Faculty's in Directory:");
        for (Person p : directory) {
            if (p instanceof Faculty)
                System.out.println(p);
        }
    }

    @Override
    public String toString() {
        return "Directory{" +
                "directory=" + directory +
                '}';
    }
}
