import io.github.swiftyninja.person.*;
import io.github.swiftyninja.schedule.*;
import io.github.swiftyninja.utilities.FileParser;
import io.github.swiftyninja.utilities.SequencialID;
import io.github.swiftyninja.utilities.UUIDGenerator;

public class CourseScheduler {
    public static void main(String[] args) throws Exception {
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

        FileParser.parseStudentFile(STUDENT_INPUT_FILE, dir, new SequencialID());
        FileParser.parseFacultyFile(FACULTY_INPUT_FILE, dir, new SequencialID());
        FileParser.parseCourseFile(COURSE_INFO_FILE, SESSION_INFO_FILE, skd, new UUIDGenerator());

        SchedulerAlgo test = new SchedulerAlgo("src/main/resources/inputFiles/Config.cfg");
        test.scheduleGenerator(skd, dir);

        System.out.println(skd.scheduledCourseSessions());
        System.out.println(skd.unScheduledCourseSessions());
        System.out.println(dir.printFaculty());
        System.out.println("completed");

    }
}
