import io.github.swiftyninja.person.*;
import io.github.swiftyninja.schedule.*;
import io.github.swiftyninja.utilities.FileParser;
import io.github.swiftyninja.utilities.SequencialID;
import io.github.swiftyninja.utilities.UUIDGenerator;

import java.util.ArrayList;
import java.util.Scanner;

public class CourseScheduler {
    public static void main(String[] args) {
        final String MAIN_MENU = "\n--------------------------" +
                                 "\n      CLASS SCHEDULE      " +
                                 "\n--------------------------" +
                                 "\n(0) Exit Program"           +
                                 "\n(1) Student Login"          +
                                 "\n(2) Faculty Login"          +
                                 "\n(3) Admin Login"            +
                                 "\n--------------------------";

        final String STUDENT_PORTAL = "\n--------------------------" +
                                      "\n      STUDENT PORTAL      " +
                                      "\n--------------------------" +
                                      "\n(0) Exit Portal"            +
                                      "\n(1) My Current Classes"     +
                                      "\n(2) My Information"         +
                                      "\n(3) Class Schedule"         +
                                      "\n--------------------------";

        final String FACULTY_PORTAL = "\n--------------------------" +
                                      "\n      FACULTY PORTAL      " +
                                      "\n--------------------------" +
                                      "\n(0) Exit Portal"            +
                                      "\n(1) My Current Courses"     +
                                      "\n(2) My Information"         +
                                      "\n(3) Class Schedule"         +
                                      "\n--------------------------";

        final String MY_CURRENT_CLASSES = "\n--------------------------" +
                                          "\n(0) Back"                   +
                                          "\n(1) Drop class"             +
                                          "\n--------------------------";

        final String CLASS_SCHEDULE = "\n--------------------------" +
                                      "\n(0) Back"                   +
                                      "\n(1) Add Class"              +
                                      "\n(2) Display Course's again" +
                                      "\n--------------------------";

        final String STUDENT_INPUT_FILE = "src/main/resources/inputFiles/student_info.csv";
        final String FACULTY_INPUT_FILE = "src/main/resources/inputFiles/faculty_info.csv";
        final String COURSE_INFO_FILE   = "src/main/resources/inputFiles/course_info.csv";
        final String SESSION_INFO_FILE  = "src/main/resources/inputFiles/session_info.csv";

        final String SCHEDULED_COURSE_SESSIONS_FILE     = "src/main/resources/inputFiles/scheduled_course_sessions.txt";
        final String UNSCHEDULED_COURSE_SESSIONS_FILE   = "src/main/resources/inputFiles/unscheduled_course_sessions.txt";
        final String FACULTY_FILE                       = "src/main/resources/inputFiles/faculty.txt";
        final String SCHEDULED_STUDENTS_FILE            = "src/main/resources/inputFiles/scheduled_students.txt";
        final String UNSCHEDULED_STUDENTS_FILE          = "src/main/resources/inputFiles/unscheduled_students.txt";

        Directory dir = new Directory();
        CourseSchedule skd = new CourseSchedule();
        Scanner in = new Scanner(System.in);
        int menuOption;
        String first;
        String middle;
        String last;
        String idNum;

        FileParser.parseStudentFile(STUDENT_INPUT_FILE, dir, new SequencialID());
        FileParser.parseFacultyFile(FACULTY_INPUT_FILE, dir, new SequencialID());
        FileParser.parseCourseFile(COURSE_INFO_FILE, SESSION_INFO_FILE, skd, new UUIDGenerator());

        System.out.println(MAIN_MENU);
        System.out.print("command: ");
        menuOption = in.nextInt();
        while (menuOption != 0) {
            switch (menuOption) {
            case 1 -> { // Student Login
//                System.out.print("Enter your full name (first middle last): ");
//                first = in.next();
//                middle = in.next();
//                last = in.next();
                System.out.print("Enter you ID number: ");
                idNum = in.next();
                System.out.println(STUDENT_PORTAL);
                System.out.print("command: ");
                menuOption = in.nextInt();
                while (menuOption != 0) {
                    switch (menuOption) {
                    case 1 -> { // My Current Classes
                        Student temp = (Student) dir.find(idNum);
                        if (temp.numOfClasses() == 0) {
                            System.out.println("\nYour schedule is empty");
                        } else {
                            System.out.println("Current Schedule:");
                            temp.printMyClasses();
                            System.out.println(MY_CURRENT_CLASSES);
                            System.out.print("command: ");
                            menuOption = in.nextInt();
                            while (menuOption != 0) {
                                if (menuOption == 1) {  // drop class
                                    System.out.print("Enter the session ID and courseID pair you would like to drop (e.g \"1A17041 CS1A\"): ");
                                    String sessionID = in.next().toUpperCase();
                                    String courseID = in.next().toUpperCase();
                                    try {
                                        ((Course) skd.find(courseID)).find(sessionID).removeStudent(idNum);
                                        ((Student) dir.find(idNum)).removeSession(sessionID);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println("\nUPDATED Current SCHEDULE");
                                    temp.printMyClasses();
                                }
                                System.out.println(MY_CURRENT_CLASSES);
                                System.out.print("command: ");
                                menuOption = in.nextInt();
                            }
                        }
                    }
                    case 2 -> { //  My Information
                        System.out.println("\nPerson info:");
                        System.out.println(((Student) dir.find(idNum)));
                    }
                    case 3 -> { // Class Schedule
                        System.out.println(skd);
                        System.out.println(CLASS_SCHEDULE);
                        System.out.print("command: ");
                        menuOption = in.nextInt();
                        while (menuOption != 0) {
                            switch (menuOption) {
                            case 1 -> { // add class
                                System.out.print("Enter the course ID you would like to look for a session in: ");
                                String courseID = in.next().toUpperCase();
                                System.out.println(skd.find(courseID));
                                System.out.print("Enter the session ID you would like to enrolled in: ");
                                String sessionID = in.next().toUpperCase();
                                try {
                                    ((Course) skd.find(courseID)).find(sessionID).addStudent((Student) dir.find(idNum));
                                    ((Student) dir.find(idNum)).addSession(((Course) skd.find(courseID)).find(sessionID));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                System.out.println("\nUPDATED CLASS SCHEDULE");
                                System.out.println(skd.find(courseID));
                            }
                            case 2 -> System.out.println(skd);  // re-display course schedule
                            }
                            System.out.println(CLASS_SCHEDULE);
                            System.out.print("command: ");
                            menuOption = in.nextInt();
                        }
                    }
                    }
                    System.out.println(STUDENT_PORTAL);
                    System.out.print("command: ");
                    menuOption = in.nextInt();
                }
            }
            case 2-> {  // Faculty Login

            }
            }   // Main Menu switch
        }   // main loop
    }
}
