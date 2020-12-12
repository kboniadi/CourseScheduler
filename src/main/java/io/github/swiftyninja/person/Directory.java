package io.github.swiftyninja.person;

import java.util.LinkedList;

public class Directory implements IDirectory {
    private LinkedList<Person> directory;

    public Directory() {
        directory = new LinkedList<>();
    }

    @Override
    public Object find(String personID) {
        for (Person p : directory) {
            if (p.getId().equals(personID))
                return p;
        }
        return null;
    }

    @Override
    public void add(Object obj) throws Exception {
        if (!(obj instanceof Person))
            return;

        if (!directory.contains(obj))
            directory.add((Person) obj);
        else
            throw new Exception("Person already exists in directory");
    }

    @Override
    public void remove(String personID) throws Exception {
        Person temp = (Person) find(personID);
        if (temp != null)
            directory.remove(temp);
        else
            throw new Exception("id not in directory");
    }

    @Override
    public void replace(String personID, Object person) throws Exception {
        Person temp = (Person) find(personID);
        if (temp != null) {
            directory.remove(temp);
            directory.add((Person) person);
        } else {
            throw new Exception("id was not in directory");
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
