package io.github.swiftyninja.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Configuration class (Singleton)
 */
public final class Configuration {
    private static Configuration instance;
    private String studentMaxCourses;
    private String studentMaxSessionPerCourse;
    private String facultyMaxCourses;
    private String facultyMaxSessionPerCourse;
    private static boolean empty;

    /**
     * Constructor
     */
    private Configuration() {
        empty = false;
    }

    /**
     *
     * @return instance of internal Configuration instance
     */
    public static Configuration getInstance() {
        // double locking mechanism
        if (instance == null) {
            synchronized (Configuration.class) {
                if (instance == null)
                    instance = new Configuration();
            }
        }
        return instance;
    }

    /**
     *
     * @param file_name file.cfg
     */
    public void parseFile(String file_name) {

        try {
            File file = new File(file_name);
            Scanner in = new Scanner(file);

            while (in.hasNext()) {
                String line = in.nextLine().trim();
                if (line.compareTo("#student") == 0)
                    parseStudent(in);
                else if (line.compareTo("#faculty") == 0)
                    parseFaculty(in);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        empty = false;
    }

    /**
     *
     * @param in scanner from parseFile for student info
     * @throws FileNotFoundException
     */
    private void parseStudent(Scanner in) throws FileNotFoundException {
        while (in.hasNext()) {
            HashMap<String, String> map = new HashMap<>();
            if (!getConfigBlockLine(in, map))
                break;

            // add more params here
            if (map.containsKey("courses")) {
                studentMaxCourses = map.get("courses");
            } else if (map.containsKey("sessions")) {
                studentMaxSessionPerCourse = map.get("sessions");
            }
        }
    }

    /**
     *
     * @param in scanner from parseFile for Faculty info
     * @throws FileNotFoundException
     */
    private void parseFaculty(Scanner in) throws FileNotFoundException {
        while (in.hasNext()) {
            HashMap<String, String> map = new HashMap<>();
            if (!getConfigBlockLine(in, map))
                break;

            // add more params here
            if (map.containsKey("courses")) {
                facultyMaxCourses = map.get("courses");
            } else if (map.containsKey("sessions")) {
                facultyMaxSessionPerCourse = map.get("sessions");
            }
        }
    }


    /**
     *
     * @param in scanner from sub-parsers for internal parse
     * @param map key, value
     * @return true if successful
     * @throws FileNotFoundException
     */
    private boolean getConfigBlockLine(Scanner in, HashMap<String, String> map) throws FileNotFoundException {
        while (in.hasNext()) {
            String line = in.nextLine().trim();

            if (line.compareTo("#end") == 0)
                return false;
            int loc = line.indexOf('=');
            if (loc != -1) {
                map.put(line.substring(0, loc).trim(), line.substring(loc + 1).trim());
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return course configuration data
     */
    public String getStudentMaxCourses() {
        return studentMaxCourses;
    }

    /**
     *
     * @return session configuration data
     */
    public String getStudentMaxSessionPerCourse() {
        return studentMaxSessionPerCourse;
    }

    /**
     *
     * @return course configuration data
     */
    public String getFacultyMaxCourses() {
        return facultyMaxCourses;
    }

    /**
     *
     * @return session configuration  data
     */
    public String getFacultyMaxSessionPerCourse() {
        return facultyMaxSessionPerCourse;
    }

    /**
     *
     * @return has file.txt been parse yet
     */
    public boolean isEmpty() {
        return empty;
    }
}
