package io.github.swiftyninja.schedule;

import io.github.swiftyninja.person.Directory;
import io.github.swiftyninja.person.Faculty;
import io.github.swiftyninja.person.Person;
import io.github.swiftyninja.person.Student;
import io.github.swiftyninja.utilities.Configuration;
import java.util.Random;

public class SchedulerAlgo implements IAutoScheduler {

    public SchedulerAlgo(String config_file) {
        Configuration.getInstance().parseFile(config_file);
    }

    @Override
    public void scheduleGenerator(CourseSchedule schedule, Directory personDir) throws Exception {
        Random rand = new Random();

        for (Person p : personDir.getList()) {
            int courseCounter = 0;
            for (Course c : schedule.getList()) {
                int numOfSessions = c.getSessions().size();
                if (p instanceof Student && !c.isCourseFull()) {

                    for (int i = 0; i < numOfSessions; i++) {
                        if (!c.getSessions().get(i).isStudentInSession(p.getId()) && !c.getSessions().get(i).isSessionFull()) {
                            c.getSessions().get(i).addStudent((Student) p);
                            ((Student) p).addSession(c.getSessions().get(i));
                            courseCounter++;
                            break;
                        }
                    }

                    if (courseCounter >= Integer.parseInt(Configuration.getInstance().getStudentMaxCourses()))
                        break;

                } else if (p instanceof Faculty) {
                    int counter = 0;

                    for (int i = 0; i < numOfSessions; i++) {
                        if (c.getSessions().get(i).getTeacher() == null) {
                            c.getSessions().get(i).setTeacher((Faculty) p);
                            ((Faculty) p).addSession(c.getSessions().get(i));
                            counter++;
                        }

                        if (counter >= Integer.parseInt(Configuration.getInstance().getFacultyMaxSessionPerCourse()))
                            break;
                    }

                    if (counter != 0)
                        courseCounter++;
                    if (courseCounter >= Integer.parseInt(Configuration.getInstance().getFacultyMaxCourses()))
                        break;
                }
            }
        }
    }
}
