package io.github.swiftyninja.common;

import io.github.swiftyninja.utilities.DataValidation;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Student class
 */
public class Student extends Person implements Cloneable, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private LocalDate birth;
    private String gpa;
    private LocalDate startdate;
    private ArrayList<Session> classes;

    /**
     * Constructor
     * @param id unique to student obj
     */
    public Student(String id) throws Exception {
        this(id, LocalDate.now(), "", LocalDate.now());
    }

    /**
     * Constructor
     * @param id unique to student
     * @param birth year/month/day
     * @param gpa 4.0 scale
     * @param startdate year/month/day
     */
    public Student(String id, LocalDate birth, String gpa, LocalDate startdate) throws Exception {
        super(id);
        setBirth(birth);
        setGpa(gpa);
        setStartDate(startdate);
        classes = new ArrayList<>();
    }

    /**
     * Constructor
     * @param id unique to student
     * @param name first, middle, last
     * @param address street, city, state, zip
     * @param email email
     * @param phone phone number
     * @param birth year/month/day
     * @param gpa on 4.0 scale
     * @param startdate year/month/day
     */
    public Student(String id, PersonName name, PersonAddress address, String email, String phone, LocalDate birth, String gpa, LocalDate startdate) throws Exception {
        super(id, name, address, email, phone);
        setBirth(birth);
        setGpa(gpa);
        setStartDate(startdate);
        classes = new ArrayList<>();
    }

    /**
     *
     * @return birthday as String
     */
    public String getBirth() { return birth.toString(); }

    /**
     *
     * @return gpa as a String
     */
    public String getGpa() {
        return Double.toString((Integer.parseInt(gpa) / 100.0));
    }

    /**
     *
     * @return start date as a String
     */
    public String getStartDate() { return startdate.toString(); }

    /**
     *
     * @return list of classes
     */
    public ArrayList<Session> getClasses() { return classes; }

    /**
     *
     * @return num of classes in schedule
     */
    public int numOfClasses() { return classes.size(); }

    /**
     *
     * @param birth setter LocalDate
     */
    public void setBirth(LocalDate birth) throws Exception {
        DataValidation.ensureObjectNotNull("birth", birth);
        this.birth = birth;
    }

    /**
     *
     * @param gpa setter String
     */
    public void setGpa(String gpa) throws Exception {
        DataValidation.ensureNonEmptyString("gpa", gpa);
        this.gpa = gpa;
    }

    /**
     *
     * @param startdate setter LocalDate
     */
    public void setStartDate(LocalDate startdate) throws Exception {
        DataValidation.ensureObjectNotNull("startDate", startdate);
        this.startdate = startdate;
    }

    /**
     *
     * @return formatted list of classes
     */
    public String listOfClasses() {
        StringBuilder out = new StringBuilder();
        for (Session s : classes) {
            if (!s.isCancelled()) {
                out.append(s);
            }
        }
        return out.toString();
    }

    /**
     *
     * @return checks if schedule is empty or not
     */
    public boolean isScheduled() {
        for (Session s : classes) {
            if (!s.isCancelled())
                return true;
        }
        return false;
    }

    /**
     *
     * @param sesh course session obj to add
     */
    public void addSession(Session sesh) throws Exception {
        if (!classes.contains(sesh))
            classes.add(sesh);
    }

    /**
     *
     * @param sessionID id of session to remove
     */
    public void removeSession(String sessionID) {
        classes.removeIf(n -> n.getSessionId().equals(sessionID));
    }

    /**
     *
     * @param sessionId id of session to find
     * @return Session obj
     */
    public Session findSession(String sessionId) throws Exception {
        return classes.stream().filter(n -> n.getSessionId().equals(sessionId)).findAny().orElse(null);
    }

//    public void printMyClasses() {
//        if (classes.isEmpty()) {
//            System.out.println("\nYour schedule is empty");
//            return;
//        }
//        StringBuilder out = new StringBuilder();
//        out.append(String.format("%n%-10s%-10s%-20s%n", "TICKET", "Course", "INSTRUCTOR"));
//        out.append("-".repeat(40));
//        for (Session s : classes) {
//            out.append(String.format("%n%-10s%-10s%-20s%n", s.getSessionId(), s.getCourseID(), (s.getTeacher() == null ? "N/A" : s.getTeacher().getName())));
//        }
//        System.out.println(out.toString());
//    }

    /**
     *
     * @return default formatted Student String
     */
    @Override
    public String toString() {
        return "-".repeat(163) +
                String.format("%n|%-25s|%-50s|%-30s|%-15s|%-15s|%-15s|%-5s|%n",
                        "Name", "Address", "Email", "Phone", "Birthday", "Start Date", "GPA") +
                "-".repeat(163) +
                String.format("%n%-120s%-15s|%-15s|%-5s|%n",
                        super.toString(), birth.toString(), startdate.toString(), getGpa()) + "-".repeat(163) + "\n";
    }

    /**
     *
     * @return adds cloneable logic to class
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     *
     * @return adds Serializable logic to class
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Student deepCopy() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(this);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bis);

        return (Student) in.readObject();
    }

}
