package io.github.swiftyninja.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Configuration {
    private HashMap<ControlParams, String> students;
    private HashMap<ControlParams, String> faculty;
    private boolean empty;

    public Configuration() {
        empty = false;
    }

    public void parseFile(String file_name) {
        students.clear();
        faculty.clear();

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

    private void parseStudent(Scanner in) throws FileNotFoundException {
        ControlParams id = ControlParams.NONE;
        String value = "";
//        Scanner in = new Scanner(file);

        while (in.hasNext()) {
            HashMap<String, String> map = new HashMap<>();
            if (!getConfigBlockLine(in, map))
                break;

            // add more params here
            if (map.containsKey("courses")) {
                id = ControlParams.COURSES;
                value = map.get("courses");
            } else if (map.containsKey("sessions")) {
                id = ControlParams.SESSIONS;
                value = map.get("sessions");
            }
            students.put(id, value);
        }
    }

    private void parseFaculty(Scanner in) throws FileNotFoundException {
        ControlParams id = ControlParams.NONE;
        String value = "";
//        Scanner in = new Scanner(file);

        while (in.hasNext()) {
            HashMap<String, String> map = new HashMap<>();
            if (!getConfigBlockLine(in, map))
                break;

            // add more params here
            if (map.containsKey("courses")) {
                id = ControlParams.COURSES;
                value = map.get("courses");
            } else if (map.containsKey("sessions")) {
                id = ControlParams.SESSIONS;
                value = map.get("sessions");
            }
            students.put(id, value);
        }
    }


    private boolean getConfigBlockLine(Scanner in, HashMap<String, String> map) throws FileNotFoundException {
//        Scanner in = new Scanner(file);

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

    public String getStudentControlParam(ControlParams param) {
        return students.getOrDefault(param, null);
    }

    public String getFacultyControlParam(ControlParams param) {
        return faculty.getOrDefault(param, null);
    }

    public boolean isStudentParamExist(ControlParams param) {
        return students.getOrDefault(param, null) != null;
    }

    public boolean isFacultyParamExist(ControlParams param) {
        return faculty.getOrDefault(param, null) != null;
    }

    public boolean isEmpty() {
        return empty;
    }
}
