package io.github.swiftyninja.scheduler.person;

import java.util.LinkedList;

public class Directory implements IDirectory {
    private LinkedList<Person> directory;

    public Directory() {
        directory = new LinkedList<>();
    }

    public Person find(String id) {
        for (Person p : directory) {
            if (p instanceof Student) {
                if (((Student) p).getId().equals(id))
                    return p;
            } else if (p instanceof Faculty) {
                if (((Faculty) p).getId().equals(id))
                    return p;
            }
        }
        return null;
    }

    public void add(Person person) throws Exception {
        if (!directory.contains(person))
            directory.add(person);
        else
            throw new Exception("Person already exists in directory");
    }

    public void remove(String id) throws Exception {
        Person temp = find(id);
        if (temp != null)
            directory.remove(temp);
        else
            throw new Exception("id not in directory");
    }

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
