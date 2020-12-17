package io.github.swiftyninja.utilities;

import io.github.swiftyninja.common.*;
import io.github.swiftyninja.common.Course;
import io.github.swiftyninja.containers.CourseSchedule;
import io.github.swiftyninja.common.Session;
import io.github.swiftyninja.containers.Directory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * FileParser class
 */
public final class FileParser {
    /**
     * Constructor
     */
    private FileParser() {}

    /**
     *
     * @param file_name student file
     * @param list person dir
     * @param gen type of ID generator
     */
    public static void parseStudentFile(String file_name, Directory list, IdGenerator gen) {
        try {
            int count = 0;
            File file = new File(file_name);
            Scanner in = new Scanner(file);

            in.nextLine();
            while (in.hasNext()) {
                String[] data = in.nextLine().split("[|]");
                list.add(new Student(gen.generateId(), new PersonName(data[0], data[1], data[2]),
                        new PersonAddress(data[3], data[4], data[5], Integer.parseInt(data[6])), data[7],
                        data[8], LocalDate.parse(data[9], DateTimeFormatter.ofPattern("MM/dd/yyyy")), data[10],
                        LocalDate.parse(data[11], DateTimeFormatter.ofPattern("MM/dd/yyyy"))));
                count++;
            }
            System.out.printf("%-32s%d%n", "Total students: ", count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param file_name faculty file
     * @param list person dir
     * @param gen type of ID generator
     */
    public static void parseFacultyFile(String file_name, Directory list, IdGenerator gen) {
        try {
            int count = 0;
            File file = new File(file_name);
            Scanner in = new Scanner(file);

            in.nextLine();
            while (in.hasNext()) {
                String[] data = in.nextLine().split("[|]");
                list.add(new Faculty(gen.generateId(), new PersonName(data[0], data[1], data[2]),
                        new PersonAddress(data[3], data[4], data[5], Integer.parseInt(data[6])), data[7],
                        data[8], LocalDate.parse(data[9], DateTimeFormatter.ofPattern("MM/dd/yyyy")), data[10].equalsIgnoreCase("yes")));
                count++;
            }
            System.out.printf("%-32s%d%n", "Total Faculty: ", count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param course_file_name course file
     * @param session_file_name session file
     * @param list courseSchedule dir
     * @param gen type of ID generator
     */
    public static void parseCourseFile(String course_file_name, String session_file_name, CourseSchedule list, IdGenerator gen) {
        try {
            int count = 0;
            File course_file = new File(course_file_name);
            File session_file = new File(session_file_name);
            Scanner inCourse = new Scanner(course_file);
            Scanner inSession = new Scanner(session_file);

            inCourse.nextLine();
            while (inCourse.hasNext()) {
                String[] data = inCourse.nextLine().split("[|]");
                list.add(new Course(data[0], data[1], data[2]));
                count++;
            }
            System.out.printf("%-32s%d%n", "Total Course: ", count);

            inSession.nextLine();
            while (inSession.hasNext()) {
                String[] data = inSession.nextLine().split("[|]");
                ((Course) list.find(data[0])).addSessions(new Session(data[0], gen.generateId(),
                        Integer.parseInt(data[2]), Integer.parseInt(data[3])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param file_name output file name
     * @param data String to write
     * @throws IOException
     */
    public static void dataOutputTry(String file_name, String data) throws IOException {
        DataOutputStream out = null;
        try {
            File file = new File(file_name);
            out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            out.write(data.getBytes(StandardCharsets.UTF_8));
        } finally {
            if (out != null)
                out.close();
        }
    }

    /**
     *
     * @param input String to concatenate
     * @param maxLength desired max length
     * @return concatenated String + ".."
     */
    public static String abbreviate(String input, int maxLength) {
        if (input.length() <= maxLength)
            return input;
        return input.substring(0, maxLength - 2).concat("..");
    }
}
