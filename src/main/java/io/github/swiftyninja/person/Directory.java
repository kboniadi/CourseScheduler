package io.github.swiftyninja.person;

import java.util.ArrayList;

public final class Directory implements IDirectory {
    private static Directory instance;
    private ArrayList<Person> directory;

    private Directory() {
        directory = new ArrayList<>();
    }

    public static Directory getInstance() {
        // double locking mechanism
        if (instance == null) {
            synchronized (Directory.class) {
                if (instance == null)
                    instance = new Directory();
            }
        }
        return instance;
    }

    public ArrayList<Person> getList() {
        return directory;
    }

    public String scheduledStudents() {
        StringBuilder out = new StringBuilder();
        for (Person p : directory) {
            if (p instanceof Student && ((Student) p).isScheduled()) {
                out.append("\n").append(p);
                out.append(String.format("%n%-10s%-10s%-20s%-35s%-10s%n", "TICKET","COURSE", "SEAT COUNT", "INSTRUCTOR", "STATUS"));
                out.append("-".repeat(85));
                out.append(((Student) p).listOfClasses());
            }
        }
        return out.toString();
    }

    public String unScheduledStudents() {
        int count = 0;
        StringBuilder out = new StringBuilder();
        for (Person p : directory) {
            if (p instanceof Student && !((Student) p).isScheduled()) {
                out.append("\n").append(p);
                out.append("\nNO CLASSES THIS SEMESTER!!\n");
                count++;
            }
        }
        System.out.printf("%-32s%d%n", "Total students with no classes: ",  count);
        return out.toString();
    }

    public String facultyInfo() {
        StringBuilder out = new StringBuilder();
        out.append("Faculty's in Directory:\n\n");
        for (Person p : directory) {
            if (p instanceof Faculty) {
                out.append("\n").append(p);
                if (((Faculty) p).isTeaching()) {
                    out.append(String.format("%n%-10s%-10s%-20s%-35s%-10s%n", "TICKET","COURSE", "SEAT COUNT", "INSTRUCTOR", "STATUS"));
                    out.append("-".repeat(85));
                    out.append(((Faculty) p).listOfSessions());
                } else {
                    out.append("\nNOT TEACHING THIS SEMESTER!!\n");
                }
            }
        }
        return out.toString();
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
    public String toString() {
        return "Directory{" +
                "directory=" + directory +
                '}';
    }
}
