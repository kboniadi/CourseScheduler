package io.github.swiftyninja.person;

import io.github.swiftyninja.schedule.Session;
import java.time.LocalDate;
import java.util.ArrayList;

public class Student extends Person {
    private LocalDate birth;
    private String gpa;
    private LocalDate startdate;
    private ArrayList<Session> classes;

    public Student(String id) {
        this(id, LocalDate.now(), "", LocalDate.now());
    }

    public Student(String id, LocalDate birth, String gpa, LocalDate startdate) {
        super(id);
        setBirth(birth);
        setGpa(gpa);
        setStartDate(startdate);
        classes = new ArrayList<>();
    }

    public Student(String id, PersonName name, PersonAddress address, String email, String phone, LocalDate birth, String gpa, LocalDate startdate) {
        super(id, name, address, email, phone);
        setBirth(birth);
        setGpa(gpa);
        setStartDate(startdate);
        classes = new ArrayList<>();
    }

    public String getBirth() { return birth.toString(); }
    public String getGpa() {
        return Double.toString((Integer.parseInt(gpa) / 100.0));
    }
    public String getStartDate() { return startdate.toString(); }
    public ArrayList<Session> getClasses() { return classes; }
    public int numOfClasses() { return classes.size(); }

    public void setBirth(LocalDate birth) { this.birth = birth; }
    public void setGpa(String gpa) { this.gpa = gpa; }
    public void setStartDate(LocalDate startdate) { this.startdate = startdate; }

    public void addSession(Session sesh) {
        if (!classes.contains(sesh))
            classes.add(sesh);
    }

    public void removeSession(String sessionID) {
        classes.removeIf(n -> n.getSessionId().equals(sessionID));
    }

    public Session findSession(String sessionId) {
        return classes.stream().filter(n -> n.getSessionId().equals(sessionId)).findAny().orElse(null);
    }

    public void printMyClasses() {
        if (classes.isEmpty()) {
            System.out.println("\nYour schedule is empty");
            return;
        }
        StringBuilder out = new StringBuilder();
        out.append(String.format("%n%-10s%-10s%-20s%n", "TICKET", "Course", "INSTRUCTOR"));
        out.append("-".repeat(40));
        for (Session s : classes) {
            out.append(String.format("%n%-10s%-10s%-20s%n", s.getSessionId(), s.getCourseID(), (s.getTeacher() == null ? "N/A" : s.getTeacher().getName())));
        }
        System.out.println(out.toString());
    }

    @Override
    public String toString() {
        return super.toString() + "\nBirthday: " + birth.toString() +
                "\nSchool start: " + startdate.toString() + "\nGPA: " + getGpa();

    }
}
