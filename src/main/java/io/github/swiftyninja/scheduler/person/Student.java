package io.github.swiftyninja.scheduler.person;

import io.github.swiftyninja.scheduler.schedule.Session;
import java.time.LocalDate;
import java.util.ArrayList;

public class Student extends Person {
    private LocalDate birth;
    private String gpa;
    private LocalDate startdate;
    private String id;
    private ArrayList<Session> classes;

    public Student() {
        this(LocalDate.now(), "", LocalDate.now());
    }

    public Student(LocalDate birth, String gpa, LocalDate startdate) {
        super();
        setBirth(birth);
        setGpa(gpa);
        setStartDate(startdate);
        classes = new ArrayList<Session>();
    }

    public Student(PersonName name, PersonAddress address, String email, String phone, LocalDate birth, String gpa, LocalDate startdate) {
        super(name, address, email, phone);
        setBirth(birth);
        setGpa(gpa);
        setStartDate(startdate);
        classes = new ArrayList<Session>();
    }

    public String getBirth() { return birth.toString(); }
    public String getGpa() { return gpa; }
    public String getStartDate() { return startdate.toString(); }
    public String getId() { return id; }

    public void setBirth(LocalDate birth) { this.birth = birth; }
    public void setGpa(String gpa) { this.gpa = gpa; }
    public void setStartDate(LocalDate startdate) { this.startdate = startdate; }
    public void setId(String id) { this.id = id; }
}
