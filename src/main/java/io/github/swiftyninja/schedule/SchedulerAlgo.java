package io.github.swiftyninja.schedule;

import io.github.swiftyninja.person.Directory;
import io.github.swiftyninja.person.Faculty;
import io.github.swiftyninja.person.Person;
import io.github.swiftyninja.person.Student;
import io.github.swiftyninja.utilities.Configuration;

import java.util.Random;

public class SchedulerAlgo implements IAutoScheduler {

    public SchedulerAlgo(String file_name) {
        Configuration.getInstance().parseFile(file_name);
    }

    @Override
    public void scheduleGenerator(CourseSchedule schedule, Directory personDir) throws Exception {
        Random rand = new Random();

        for (Person p : personDir.getList()) {
            int courseCounter = 0;
            for (Course c : schedule.getList()) {
                int numOfSessions = c.getSessions().size();
                if (p instanceof Student && !c.isCourseFull()) {
                    int randomNum = rand.nextInt(numOfSessions);
                    while (c.getSessions().get(randomNum).isStudentInSession(p.getId()))
                        randomNum = rand.nextInt(numOfSessions);
                    if (!c.getSessions().get(randomNum).isSessionFull()) {
                        c.getSessions().get(randomNum).addStudent((Student) p);
                        ((Student) p).addSession(c.getSessions().get(randomNum));
                        courseCounter++;
                    }
                    if (courseCounter + 1 > Integer.parseInt(Configuration.getInstance().getStudentMaxCourses()))
                        break;

                } else if (p instanceof Faculty) {
                    int counter = 0;
                    while (counter < Integer.parseInt(Configuration.getInstance().getFacultyMaxSessionPerCourse()) && !c.isCourseStaffed()) {
                        int randomNum = rand.nextInt(numOfSessions);
                        if (c.getSessions().get(randomNum).getTeacher() == null) {
                            c.getSessions().get(randomNum).setTeacher((Faculty) p);
                            ((Faculty) p).addSession(c.getSessions().get(randomNum));
                            counter++;
                        }
                    }
                    if (counter != 0)
                        courseCounter++;
                    if (courseCounter + 1 > Integer.parseInt(Configuration.getInstance().getFacultyMaxCourses()))
                        break;
                }
            }
        }
    }
}
