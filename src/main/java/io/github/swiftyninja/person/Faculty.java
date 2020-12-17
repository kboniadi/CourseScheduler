package io.github.swiftyninja.person;

import io.github.swiftyninja.schedule.Session;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Faculty extends Person implements Cloneable, Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    private LocalDate hired;
    private boolean tenured;
    private ArrayList<Session> classes;

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
        classes = new ArrayList<>();
    }

    public ArrayList<Session> getClasses() { return classes; }
    public LocalDate getHired() { return hired; }
    public boolean isTenured() { return tenured; }

    public void setHired(LocalDate hired) { this.hired = hired; }
    public void setTenured(boolean tenured) { this.tenured = tenured; }

    public boolean isTeaching() {
        for (Session s : classes)
            if (!s.isCancelled()) return true;
        return false;
    }
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

    public String listOfSessions() {
        StringBuilder out = new StringBuilder();
        for (Session s : classes) {
            if (!s.isCancelled()) {
                out.append(s);
            }
        }
        return out.toString();
    }

    @Override
    public String toString() {
        return "-".repeat(149) +
                String.format("%n|%-25s|%-50s|%-30s|%-15s|%-15s|%-7s|%n",
                        "Name", "Address", "Email", "Phone", "Hired Date", "Tenured") +
                "-".repeat(149) +
                String.format("%n%-120s%-15s|%-7s|%n",
                        super.toString(), hired, (isTenured() ? "Yes" : "No")) + "-".repeat(149) + "\n";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Faculty deepCopy() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(this);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bis);

        return (Faculty) in.readObject();
    }

}
