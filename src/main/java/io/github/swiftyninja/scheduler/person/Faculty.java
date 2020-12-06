package io.github.swiftyninja.scheduler.person;

import io.github.swiftyninja.scheduler.schedule.Course;

import java.time.LocalDate;
import java.util.ArrayList;

public class Faculty extends Person {
    private LocalDate hired;
    private boolean tenured;
    private String id;
    private ArrayList<Course> classes;

    public Faculty() {
        super();
        setHired(LocalDate.now());
        setTenured(false);
        setId("");
        classes = new ArrayList<>();
    }

    public Faculty(PersonName name, PersonAddress address, String email, String phone, LocalDate hired, boolean tenured, String id) {
        super(name, address, email, phone);
        setHired(hired);
        setTenured(tenured);
        setId(id);
    }

    public LocalDate getHired() { return hired; }
    public boolean isTenured() { return tenured; }
    public String getId() { return id; }

    public void setHired(LocalDate hired) { this.hired = hired; }
    public void setTenured(boolean tenured) { this.tenured = tenured; }
    public void setId(String id) { this.id = id; }
}
