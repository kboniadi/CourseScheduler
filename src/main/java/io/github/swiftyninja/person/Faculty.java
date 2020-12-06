package io.github.swiftyninja.person;

import io.github.swiftyninja.schedule.Course;

import java.time.LocalDate;
import java.util.ArrayList;

public class Faculty extends Person {
    private LocalDate hired;
    private boolean tenured;
    private ArrayList<Course> classes;

    public Faculty(String id) {
        super(id);
        setHired(LocalDate.now());
        setTenured(false);
        classes = new ArrayList<>();
    }

    public Faculty(String id, PersonName name, PersonAddress address, String email, String phone, LocalDate hired, boolean tenured) {
        super(id, name, address, email, phone);
        setHired(hired);
        setTenured(tenured);
    }

    public LocalDate getHired() { return hired; }
    public boolean isTenured() { return tenured; }

    public void setHired(LocalDate hired) { this.hired = hired; }
    public void setTenured(boolean tenured) { this.tenured = tenured; }
}
