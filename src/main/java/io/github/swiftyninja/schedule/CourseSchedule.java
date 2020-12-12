package io.github.swiftyninja.schedule;

import io.github.swiftyninja.person.IDirectory;
import java.util.LinkedList;

public class CourseSchedule implements IDirectory {
    private LinkedList<Course> courseList;

    public CourseSchedule() {
        courseList = new LinkedList<>();
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
    public void replace(String courseID, Object obj) throws Exception {
        Course temp = (Course) find(courseID);
        if (temp != null) {
            int index = courseList.indexOf(temp);
            courseList.remove(temp);
            courseList.add(index, (Course) obj);
        } else {
            throw new Exception("id was not in directory");
        }
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (Course c : courseList)
            out.append(c);
        return out.toString();
    }
}
