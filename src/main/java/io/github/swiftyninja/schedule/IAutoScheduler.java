package io.github.swiftyninja.schedule;

import io.github.swiftyninja.person.Directory;

/**
 * Auto Scheduler interface
 */
public interface IAutoScheduler {
    /**
     *
     * @param schedule obj
     * @param personDir obj
     * @throws Exception
     */
    void scheduleGenerator(CourseSchedule schedule, Directory personDir) throws Exception;
}
