package io.github.swiftyninja.schedule;

import io.github.swiftyninja.person.Directory;

public interface IAutoScheduler {
    void scheduleGenerator(CourseSchedule schedule, Directory personDir);
}
