import io.github.swiftyninja.containers.*;
import io.github.swiftyninja.utilities.FileParser;
import io.github.swiftyninja.utilities.SequencialID;
import io.github.swiftyninja.utilities.UUIDGenerator;

/**
 * <h1>CourseScheduler</h1>
 * This program will take in text files worth of student, faculty, session, and course information. Then through
 * the use of a scheduling algorithm, students and faculty members will be assigned to sessions with data outputted
 * to text files. A Console debug output will give a brief summery of whats outputted to the text files
 * @author kord
 * @since 12/17/20
 */
public class CourseScheduler {
    public static void main(String[] args) throws Exception {
        final String CONFIG_FILE        = "src/main/resources/inputFiles/Config.cfg";
        final String STUDENT_INPUT_FILE = "src/main/resources/inputFiles/student_info.csv";
        final String FACULTY_INPUT_FILE = "src/main/resources/inputFiles/faculty_info.csv";
        final String COURSE_INFO_FILE   = "src/main/resources/inputFiles/course_info.csv";
        final String SESSION_INFO_FILE  = "src/main/resources/inputFiles/session_info.csv";

        final String SCHEDULED_COURSE_SESSIONS_FILE     = "src/main/resources/storage/scheduled_course_sessions.txt";
        final String UNSCHEDULED_COURSE_SESSIONS_FILE   = "src/main/resources/storage/unscheduled_course_sessions.txt";
        final String FACULTY_FILE                       = "src/main/resources/storage/faculty.txt";
        final String SCHEDULED_STUDENTS_FILE            = "src/main/resources/storage/scheduled_students.txt";
        final String UNSCHEDULED_STUDENTS_FILE          = "src/main/resources/storage/unscheduled_students.txt";

        FileParser.parseStudentFile(STUDENT_INPUT_FILE, Directory.getInstance(), new SequencialID());
        FileParser.parseFacultyFile(FACULTY_INPUT_FILE, Directory.getInstance(), new SequencialID());
        FileParser.parseCourseFile(COURSE_INFO_FILE, SESSION_INFO_FILE, CourseSchedule.getInstance(), new UUIDGenerator());

        IAutoScheduler test = new SchedulerAlgo(CONFIG_FILE);
        test.scheduleGenerator(CourseSchedule.getInstance(), Directory.getInstance());

        FileParser.dataOutputTry(SCHEDULED_COURSE_SESSIONS_FILE, CourseSchedule.getInstance().scheduledCourseSessions());
        FileParser.dataOutputTry(UNSCHEDULED_COURSE_SESSIONS_FILE, CourseSchedule.getInstance().unScheduledCourseSessions());
        FileParser.dataOutputTry(FACULTY_FILE, Directory.getInstance().facultyInfo());
        FileParser.dataOutputTry(SCHEDULED_STUDENTS_FILE, Directory.getInstance().scheduledStudents());
        FileParser.dataOutputTry(UNSCHEDULED_STUDENTS_FILE, Directory.getInstance().unScheduledStudents());

        System.out.println("completed");
    }
}
