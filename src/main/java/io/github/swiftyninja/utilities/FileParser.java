package io.github.swiftyninja.utilities;

import io.github.swiftyninja.person.*;
import io.github.swiftyninja.schedule.Course;
import io.github.swiftyninja.schedule.CourseSchedule;
import io.github.swiftyninja.schedule.Session;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public final class FileParser {
    private FileParser() {}

    public static void parseStudentFile(String file_name, Directory list) {
        IdGenerator generator = new SequencialID();
        try {
            File file = new File(file_name);
            Scanner in = new Scanner(file);

            in.nextLine();
            while (in.hasNext()) {
                String[] data = in.nextLine().split("[|]");
                list.add(new Student(generator.generateId(), new PersonName(data[0], data[1], data[2]),
                        new PersonAddress(data[3], data[4], data[5], Integer.parseInt(data[6])), data[7],
                        data[8], LocalDate.parse(data[9], DateTimeFormatter.ofPattern("MM/dd/yyyy")), data[10], LocalDate.parse(data[11], DateTimeFormatter.ofPattern("MM/dd/yyyy"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void parseFacultyFile(String file_name, Directory list) {
        IdGenerator generator = new SequencialID();
        try {
            File file = new File(file_name);
            Scanner in = new Scanner(file);

            in.nextLine();
            while (in.hasNext()) {
                String[] data = in.nextLine().split("[|]");
                list.add(new Faculty(generator.generateId(), new PersonName(data[0], data[1], data[2]),
                        new PersonAddress(data[3], data[4], data[5], Integer.parseInt(data[6])), data[7],
                        data[8], LocalDate.parse(data[9], DateTimeFormatter.ofPattern("MM/dd/yyyy")), data[10].equalsIgnoreCase("yes")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void parseCourseFile(String course_file_name, String session_file_name, CourseSchedule list) {
        try {
            File course_file = new File(course_file_name);
            File session_file = new File(session_file_name);
            Scanner inCourse = new Scanner(course_file);
            Scanner inSession = new Scanner(session_file);

            inCourse.nextLine();
            while (inCourse.hasNext()) {
                String[] data = inCourse.nextLine().split("[|]");
                list.add(new Course(data[0], data[1], data[2]));
            }

            inSession.nextLine();
            while (inSession.hasNext()) {
                String[] data = inSession.nextLine().split("[|]");
                ((Course) list.find(data[0])).addSessions(new Session(data[0], data[1],
                        Integer.parseInt(data[2]), Integer.parseInt(data[3])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
