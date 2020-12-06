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
    public String getGpa() { return gpa; }
    public String getStartDate() { return startdate.toString(); }

    public void addSession(Session sesh) {

    }

    public void removeSession(String id) {

    }

    public Session findSession(String id) {
        return null;
    }

    public void setBirth(LocalDate birth) { this.birth = birth; }
    public void setGpa(String gpa) { this.gpa = gpa; }
    public void setStartDate(LocalDate startdate) { this.startdate = startdate; }
}
