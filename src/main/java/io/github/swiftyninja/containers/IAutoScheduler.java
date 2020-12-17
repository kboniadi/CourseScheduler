package io.github.swiftyninja.containers;

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
