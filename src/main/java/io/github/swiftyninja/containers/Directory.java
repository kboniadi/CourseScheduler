package io.github.swiftyninja.containers;

import io.github.swiftyninja.common.Faculty;
import io.github.swiftyninja.common.Person;
import io.github.swiftyninja.common.Student;

import java.util.ArrayList;

/**
 * Directory class that contains Person info
 */
public final class Directory implements IDirectory {
    private static Directory instance;
    private ArrayList<Person> directory;

    /**
     * no-arg Constructor
     */
    private Directory() {
        directory = new ArrayList<>();
    }

    /**
     *
     * @return instance of Directory class
     */
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

    /**
     *
     * @return list of Perrson in dir
     */
    public ArrayList<Person> getList() {
        return directory;
    }

    /**
     *
     * @return formatted String of scheduled students
     */
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

    /**
     *
     * @return formatted String un-scheduled students
     */
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

    /**
     *
     * @return formatted String facultyInfo
     */
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

    /**
     *
     * @param personID object you want to search for
     * @return Object type for flexibility
     */
    @Override
    public Object find(String personID) {
        for (Person p : directory) {
            if (p.getId().equals(personID))
                return p;
        }
        return null;
    }

    /**
     *
     * @param obj item to add to dir
     * @throws Exception nullException
     */
    @Override
    public void add(Object obj) throws Exception {
        if (!(obj instanceof Person))
            return;

        if (!directory.contains(obj))
            directory.add((Person) obj);
        else
            throw new Exception("Person already exists in directory");
    }

    /**
     *
     * @param personID object you want to search for
     * @throws Exception
     */
    @Override
    public void remove(String personID) throws Exception {
        Person temp = (Person) find(personID);
        if (temp != null)
            directory.remove(temp);
        else
            throw new Exception("id not in directory");
    }

    /**
     *
     * @return default String containing dir info
     */
    @Override
    public String toString() {
        return "Directory{" +
                "directory=" + directory +
                '}';
    }
}
