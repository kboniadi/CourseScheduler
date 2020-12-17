package io.github.swiftyninja.schedule;

import java.util.ArrayList;
import java.util.Objects;
import io.github.swiftyninja.person.Student;

/**
 * Course class
 */
public class Course implements Comparable<Course> {
    private String depart;
    private String code;
    private String description;
    private String courseID;
    private ArrayList<Session> sessions;

    /**
     * Constructor
     * @param depart name
     * @param code class code
     * @param description brief description
     */
    public Course(String depart, String code, String description) {
        setDepart(depart);
        setCode(code);
        setDescription(description);
        setCourseID(getDepartment() + getCode());
        sessions = new ArrayList<>();
    }

    /**
     *
     * @return department as a String
     */
    public String getDepartment() { return depart; }

    /**
     *
     * @return class code as a String
     */
    public String getCode() { return code; }

    /**
     *
     * @return description as a String
     */
    public String getDescription() { return description; }

    /**
     *
     * @return depart name + code
     */
    public String getCourseID() { return courseID; }

    /**
     *
     * @return list of sessins
     */
    public ArrayList<Session> getSessions() {
        return sessions;
    }

    /**
     *
     * @param depart setter String
     */
    public void setDepart(String depart) { this.depart = depart; }

    /**
     *
     * @param code setter String
     */
    public void setCode(String code) { this.code = code; }

    /**
     *
     * @param description setter String
     */
    public void setDescription(String description) { this.description = description; }

    /**
     *
     * @param courseID setter String
     */
    public void setCourseID(String courseID) { this.courseID = courseID; }


    /**
     *
     * @return checks for course cancellation
     */
    public boolean isCourseCancelled() {
        for (Session s : sessions) {
            if (!s.isCancelled())
                return false;
        }
        return true;
    }

    /**
     *
     * @return checks if course is staffed by a faculty
     */
    public boolean isCourseStaffed() {
        for (Session s : sessions) {
            if (s.getTeacher() == null)
                return false;
        }
        return true;
    }

    /**
     *
     * @return checks if course is full
     */
    public boolean isCourseFull() {
        for (Session s : sessions) {
            if (!s.isSessionFull())
                return false;
        }
        return true;
    }

    /**
     *
     * @param sessionID unique id
     * @return Session obj
     */
    public Session find(String sessionID) {
        for (Session s : sessions) {
            if (s.getSessionId().equals(sessionID)) {
                return s;
            }
        }
        return null;
    }

    /**
     *
     * @param sesh obj
     * @throws Exception
     */
    public void addSessions(Session sesh) throws Exception {
        if (!sessions.contains(sesh))
            sessions.add(sesh);
        else
            throw new Exception("session is already in course directory");
    }

    /**
     *
     * @param sessionID unique id
     * @throws Exception
     */
    public void removeSession(String sessionID) throws Exception {
        Session temp = find(sessionID);
        if (temp != null)
            sessions.remove(temp);
        else
            throw new Exception("that id is not in directory");
    }

    /**
     *
     * @param stud obj
     * @param sessionID unique id
     * @throws Exception
     */
    public void addStudent(Student stud, String sessionID) throws Exception {
        for (Session s : sessions) {
            if (s.getSessionId().equals(sessionID) && !s.isStudentInSession(stud.getId())) {
                s.addStudent(stud);
            }
        }
    }

    /**
     *
     * @param personID unique id
     * @param sessionID unique id
     * @throws Exception
     */
    public void removeStudent(String personID, String sessionID) throws Exception {
        for (Session s : sessions) {
            if (s.getSessionId().equals(sessionID) && s.isStudentInSession(personID)) {
                s.removeStudent(personID);
                break;
            }
        }
    }

    /**
     *
     * @return default formatted String data
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("\n");
        out.append(depart).append(" ").append(code).append(" - ").append(description);
        out.append(String.format("%n%-10s%-10s%-20s%-35s%-10s%n", "TICKET","COURSE", "SEAT COUNT", "INSTRUCTOR", "STATUS"));
        out.append("-".repeat(85));
        for (Session s : sessions) {
            out.append(s);
        }
        return out.toString();
    }

    /**
     *
     * @param o rhs obj
     * @return equal or not (boolean)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return getCourseID().equals(course.getCourseID());
    }

    /**
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(getCourseID());
    }

    /**
     *
     * @param o rhs obj
     * @return comparison index
     */
    @Override
    public int compareTo(Course o) {
        return getCourseID().compareTo(o.getCourseID());
    }
}
