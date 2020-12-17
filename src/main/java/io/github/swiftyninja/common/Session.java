package io.github.swiftyninja.common;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Session class
 */
public class Session implements Comparable<Session> {
    private String sessionId;
    private int minStudent;
    private int maxStudent;
    private Faculty teacher;
    private String courseID;
    private boolean status;
    private int studentCount;
    private ArrayList<Student> students;


    /**
     * Constructor
     * @param courseID unique
     * @param sessionId unique
     * @param minStudent min students
     * @param maxStudent max students
     */
    public Session(String courseID, String sessionId, int minStudent, int maxStudent) {
        setCourseID(courseID);
        setSessionId(sessionId);
        setMinStudent(minStudent);
        setMaxStudent(maxStudent);
        setStatus(false);
        students = new ArrayList<>();
    }

    /**
     * updates status data fields based on number of students signed up
     */
    private void updateStatus() {
        if (getStudentCount() < getMinStudent())
            setStatus(false);
        else if (getStudentCount() >= getMinStudent() && getStudentCount() <= getMaxStudent())
            setStatus(true);
    }

    /**
     *
     * @return String formatted status update
     */
    private String printStatus() {
        if (getStudentCount() < getMaxStudent()) {
            return "OPEN";
        } else if (getMaxStudent() == getStudentCount()) {
            return "FULL";
        }
        return "";
    }

    /**
     *
     * @return String sessino id
     */
    public String getSessionId() { return sessionId; }

    /**
     *
     * @return String min students allowed
     */
    public int getMinStudent() { return minStudent; }

    /**
     *
     * @return String max students allowed
     */
    public int getMaxStudent() { return maxStudent; }

    /**
     *
     * @return teacher obj
     */
    public Faculty getTeacher() { return teacher; }

    /**
     *
     * @return String course unique id
     */
    public String getCourseID() { return courseID; }

    /**
     *
     * @return boolean is course cancelled
     */
    public boolean isCancelled() { return !status; }

    /**
     *
     * @return student count
     */
    public int getStudentCount() { return studentCount; }

    /**
     *
     * @param sessionId unique id
     */
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }

    /**
     *
     * @param minStudent min amount
     */
    public void setMinStudent(int minStudent) { this.minStudent = minStudent; }

    /**
     *
     * @param maxStudent max amount
     */
    public void setMaxStudent(int maxStudent) { this.maxStudent = maxStudent; }

    /**
     *
     * @param teacher faculty obj
     */
    public void setTeacher(Faculty teacher) { this.teacher = teacher; }

    /**
     *
     * @param courseID depart + code
     */
    public void setCourseID(String courseID) { this.courseID = courseID; }

    /**
     *
     * @param status isCancelled
     */
    public void setStatus(boolean status) { this.status = status; }

    /**
     *
     * @param studentCount number of students in Session
     */
    public void setStudentCount(int studentCount) { this.studentCount = studentCount; }

    /**
     *
     * @return String formatted with student data in Session
     */
    public String studentsInSession() {
        StringBuilder out = new StringBuilder();
        for (Student s : students) {
            out.append(String.format("%s %s%n", s.getName(), s.getId()));
        }
        return out.toString();
    }

    /**
     *
     * @return check for if Session instacne is full
     */
    public boolean isSessionFull() {
        return getStudentCount() == getMaxStudent();
    }

    /**
     *
     * @param stud obj
     * @throws Exception
     */
    public void addStudent(Student stud) throws Exception {
        if (!students.contains(stud)) {
            students.add(stud);
            studentCount++;
            updateStatus();
        } else {
            throw new Exception("student already in a class");
        }
    }

    /**
     *
     * @param personID unique id
     * @throws Exception
     */
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

    /**
     *
     * @param personID unique id
     * @return
     */
    public boolean isStudentInSession(String personID) {
        for (Student s : students) {
            if (s.getId().equals(personID))
                return true;
        }
        return false;
    }

    /**
     *
     * @return default String formatted output
     */
    @Override
    public String toString() {
        return String.format("%n%-10s%-10s%-20s%-35s%-10s%n", getSessionId(), getCourseID(), "Open Seats: " +
                (getMaxStudent() - getStudentCount()), (getTeacher() == null ? "N/A" :
                getTeacher().getName() + " " + getTeacher().getId()), printStatus());
    }

    /**
     *
     * @param o rhs instance
     * @return is equal boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        Session session = (Session) o;
        return getSessionId().equals(session.getSessionId());
    }

    /**
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(getSessionId());
    }

    /**
     *
     * @param o rhs instance
     * @return comparison index
     */
    @Override
    public int compareTo(Session o) {
        return getSessionId().compareTo(o.getSessionId());
    }
}
