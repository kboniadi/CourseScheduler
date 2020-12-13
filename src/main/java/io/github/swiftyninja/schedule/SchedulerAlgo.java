package io.github.swiftyninja.schedule;

import io.github.swiftyninja.person.Directory;
import io.github.swiftyninja.person.Person;
import io.github.swiftyninja.person.Student;
import io.github.swiftyninja.utilities.Configuration;
import io.github.swiftyninja.utilities.ControlParams;

public class SchedulerAlgo implements IAutoScheduler {
    private Configuration config;

    SchedulerAlgo(String file_name) {
        config = new Configuration();
        config.parseFile(file_name);
    }

    @Override
    public void scheduleGenerator(CourseSchedule schedule, Directory personDir) {
        int studsId = 0;
        int studCourses = 0;
        int studSessions = 0;
        int facID = 0;
        int facCourses = 0;
        int facSessions = 0;

        if (config.isStudentParamExist(ControlParams.ID))
            studsId = Integer.parseInt(config.getStudentControlParam(ControlParams.ID));
        else if (config.isStudentParamExist(ControlParams.COURSES))
            studCourses = Integer.parseInt(config.getStudentControlParam(ControlParams.COURSES));
        else if (config.isStudentParamExist(ControlParams.SESSIONS))
            studSessions = Integer.parseInt(config.getStudentControlParam(ControlParams.SESSIONS));

        if (config.isFacultyParamExist(ControlParams.ID))
            facID = Integer.parseInt(config.getFacultyControlParam(ControlParams.ID));
        else if (config.isFacultyParamExist(ControlParams.COURSES))
            facCourses = Integer.parseInt(config.getFacultyControlParam(ControlParams.COURSES));
        else if (config.isFacultyParamExist(ControlParams.SESSIONS))
            facSessions = Integer.parseInt(config.getFacultyControlParam(ControlParams.SESSIONS));

        for (Person p : personDir.getList()) {
            for (Course c : schedule.getList()) {
                if (p instanceof Student) {


                }
            }
        }
    }
}
