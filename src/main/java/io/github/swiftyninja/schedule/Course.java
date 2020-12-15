package io.github.swiftyninja.schedule;

import java.util.ArrayList;
import java.util.Collections;

import io.github.swiftyninja.person.Student;

public class Course {
    private String depart;
    private String code;
    private String description;
    private String courseID;
//    private int minStudent;
//    private int maxStudent;
    private boolean status;
    private ArrayList<Session> sessions;
//    private ArrayList<Student> students;

    public Course(String depart, String code, String description) {
        setDepart(depart);
        setCode(code);
        setDescription(description);
        setCourseID(getDepartment() + getCode());
        sessions = new ArrayList<>();
//        students = new ArrayList<>();
    }

    public String getDepartment() { return depart; }
    public String getCode() { return code; }
    public String getDescription() { return description; }
    public String getCourseID() { return courseID; }
//    public int getMinStudent() { return minStudent; }
//    public int getMaxStudent() { return maxStudent; }
    public boolean isCancelled() { return status; }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void setDepart(String depart) { this.depart = depart; }
    public void setCode(String code) { this.code = code; }
    public void setDescription(String description) { this.description = description; }
    public void setCourseID(String courseID) { this.courseID = courseID; }
//    public void setMinStudent(int minStudent) { this.minStudent = minStudent; }
//    public void setMaxStudent(int maxStudent) { this.maxStudent = maxStudent; }
    public void setStatus(boolean status) { this.status = status; }

    public boolean isCourseStaffed() {
        for (Session s : sessions) {
            if (s.getTeacher() == null)
                return false;
        }
        return true;
    }

    public boolean isCourseFull() {
        for (Session s : sessions) {
            if (!s.isSessionFull())
                return false;
        }
        return true;
    }

    public Session find(String sessionID) {
        for (Session s : sessions) {
            if (s.getSessionId().equals(sessionID)) {
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

    public void removeSession(String sessionID) throws Exception {
        Session temp = find(sessionID);
        if (temp != null)
            sessions.remove(temp);
        else
            throw new Exception("that id is not in directory");
    }

    public void addStudent(Student stud, String sessionID) throws Exception {
        for (Session s : sessions) {
            if (s.getSessionId().equals(sessionID) && !s.isStudentInSession(stud.getId())) {
                s.addStudent(stud);
            }
        }
    }

    public void removeStudent(String personID) throws Exception {
        for (Session s : sessions) {
            if (s.isStudentInSession(personID)) {
                s.removeStudent(personID);
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("\n");
        out.append(depart).append(" ").append(code).append(" - ").append(description);
        out.append(String.format("%n%-10s%-15s%-20s%-10s%n", "TICKET", "SEAT COUNT", "INSTRUCTOR", "STATUS"));
        out.append("-".repeat(55));
        for (Session s : sessions) {
            out.append(s);
        }
        return out.toString();
    }
}
