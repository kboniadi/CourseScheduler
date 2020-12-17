package io.github.swiftyninja.schedule;

import io.github.swiftyninja.person.IDirectory;

import java.util.ArrayList;

public class CourseSchedule implements IDirectory {
    private ArrayList<Course> courseList;

    public CourseSchedule() {
        courseList = new ArrayList<>();
    }

    public ArrayList<Course> getList() {
        return courseList;
    }

    public int scheduledSessionDebug() {
        int count = 0;
        for (Course c : courseList) {
            for (Session s : c.getSessions()) {
                if (!s.isCancelled())
                    count++;
            }
        }
        return count;
    }

    public String scheduledCourseSessions() {
        StringBuilder out = new StringBuilder();
        StringBuilder out2 = new StringBuilder();

        out2.append("\nStudent list for each Session:\n");
        for (Course c : courseList) {
            if (!c.isCourseCancelled()) {
                out.append(c);
                for (Session s : c.getSessions()) {
                    out2.append(String.format("%nSession id: %s | Course: %s | Student count: %s%n", s.getSessionId(), s.getCourseID(), s.getStudentCount()));
                    out2.append("-".repeat(58));
                    out2.append("\n").append(s.studentsInSession());
                }
            }
        }
        System.out.printf("%-32s%d%n", "Total sessions scheduled: ", scheduledSessionDebug());
        return out.append(out2).toString();
    }

    public String unScheduledCourseSessions() {
        int count = 0;
        StringBuilder out = new StringBuilder();
        for (Course c : courseList) {
            if (c.isCourseCancelled()) {
                out.append(c);
                count++;
            }
        }
        System.out.printf("%-32s%d%n", "Total Courses unscheduled: ", count);
        return out.toString();
    }

    @Override
    public Object find(String courseID) {
        for (Course c : courseList) {
            if (c.getCourseID().equals(courseID))
                return c;
        }
        return null;
    }

    @Override
    public void add(Object obj) throws Exception {
        if (!(obj instanceof Course))
            return;

        if (!courseList.contains(obj))
            courseList.add((Course) obj);
        else
            throw new Exception("Course already exists in directory");
    }

    @Override
    public void remove(String courseID) throws Exception {
        Course temp = (Course) find(courseID);
        if (temp != null)
            courseList.remove(temp);
        else
            throw new Exception("id not in directory");
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (Course c : courseList)
            out.append(c);
        return out.toString();
    }
}
