package io.github.swiftyninja.schedule;

import io.github.swiftyninja.person.Faculty;
import io.github.swiftyninja.person.Student;
import java.util.ArrayList;

public class Session {
    private String sessionId;
    private int minStudent;
    private int maxStudent;
    private Faculty teacher;
    private String courseID;
    private boolean status;
    private int studentCount;
    private ArrayList<Student> students;


    public Session(String courseID, String sessionId, int minStudent, int maxStudent) {
        setCourseID(courseID);
        setSessionId(sessionId);
        setMinStudent(minStudent);
        setMaxStudent(maxStudent);
        setStatus(false);
        students = new ArrayList<>();
    }

    public String getSessionId() { return sessionId; }
    public int getMinStudent() { return minStudent; }
    public int getMaxStudent() { return maxStudent; }
    public Faculty getTeacher() { return teacher; }
    public String getCourseID() { return courseID; }
    public boolean isCancelled() { return !status; }
    public int getStudentCount() { return studentCount; }

    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    public void setMinStudent(int minStudent) { this.minStudent = minStudent; }
    public void setMaxStudent(int maxStudent) { this.maxStudent = maxStudent; }
    public void setTeacher(Faculty teacher) { this.teacher = teacher; }
    public void setCourseID(String courseID) { this.courseID = courseID; }
    public void setStatus(boolean status) { this.status = status; }
    public void setStudentCount(int studentCount) { this.studentCount = studentCount; }

    public String studentsInSession() {
        StringBuilder out = new StringBuilder();
        for (Student s : students) {
            out.append(String.format("%s %s%n", s.getName(), s.getId()));
        }
        return out.toString();
    }

    public boolean isSessionFull() {
        return getStudentCount() == getMaxStudent();
    }

    private void updateStatus() {
        if (getStudentCount() < getMinStudent())
            setStatus(false);
        else if (getStudentCount() >= getMinStudent() && getStudentCount() <= getMaxStudent())
            setStatus(true);
    }

    private String printStatus() {
        if (getStudentCount() < getMaxStudent()) {
            return "OPEN";
        } else if (getMaxStudent() == getStudentCount()) {
            return "FULL";
        }
        return "";
    }

    public void addStudent(Student stud) throws Exception {
        if (!students.contains(stud)) {
            students.add(stud);
            studentCount++;
            updateStatus();
        } else {
            throw new Exception("student already in a class");
        }
    }

    public void removeStudent(String personID) throws Exception {
        Student temp = null;
        for (var v : students) {
            if (v.getId().equals(personID))
                temp = v;
        }
        if (temp != null) {
            students.remove(temp);
            studentCount--;
            updateStatus();
        } else {
            throw new Exception("student not in class");
        }
    }

    public boolean isStudentInSession(String personID) {
        for (Student s : students) {
            if (s.getId().equals(personID))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%n%-10s%-10s%-20s%-35s%-10s%n", getSessionId(), getCourseID(), "Open Seats: " +
                (getMaxStudent() - getStudentCount()), (getTeacher() == null ? "N/A" :
                getTeacher().getName() + " " + getTeacher().getId()), printStatus());
    }
}
