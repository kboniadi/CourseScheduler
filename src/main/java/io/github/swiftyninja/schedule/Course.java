package io.github.swiftyninja.schedule;

import java.util.ArrayList;
import io.github.swiftyninja.person.Student;

public class Course {
    private String depart;
    private String code;
    private String description;
    private String courseID;
    private int minStudent;
    private int maxStudent;
    private boolean status;
    private ArrayList<Session> sessions;
    private ArrayList<Student> students;

    public Course(String depart, String code, String description, int minStudent,
                  int maxStudent, boolean status) {
        this.depart = depart;
        this.code = code;
        this.description = description;
        this.minStudent = minStudent;
        this.maxStudent = maxStudent;
        this.status = status;
    }

    public String getDepartment() { return depart; }
    public String getCode() { return code; }
    public String getDescriptiono() { return description; }
    public String getCourseID() { return courseID; }
    public int getMinStudent() { return minStudent; }
    public int getMaxStudent() { return maxStudent; }
    public boolean isCancelled() { return status; }

    public void setDepart(String depart) { this.depart = depart; }
    public void setCode(String code) { this.code = code; }
    public void setDescription(String description) { this.description = description; }
    public void setCourseID(String courseID) { this.courseID = courseID; }
    public void setMinStudent(int minStudent) { this.minStudent = minStudent; }
    public void setMaxStudent(int maxStudent) { this.maxStudent = maxStudent; }
    public void setStatus(boolean status) { this.status = status; }

    public Session find(String id) {
        for (Session s : sessions) {
            if (s.getSessionId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public void addSessions(Session sesh) throws Exception {
        if (!sessions.contains(sesh))
            sessions.add(sesh);
        else
            throw new Exception("session is already in course directory");
    }

    public void removeSession(String id) throws Exception {
        Session temp = find(id);
        if (temp != null)
            sessions.remove(temp);
        else
            throw new Exception("that id is not in directory");
    }

    public void addStudent(Student stud) throws Exception {
        if (!students.contains(stud))
            students.add(stud);
        else
            throw new Exception("student already in a class");
    }

    public void removeStudent(String id) throws Exception {
        Student temp = null;
        for (var v : students) {
            if (v.getId().equals(id))
                temp = v;
        }
        if (temp != null)
            students.remove(temp);
        else
            throw new Exception("student not in class");
    }
}
