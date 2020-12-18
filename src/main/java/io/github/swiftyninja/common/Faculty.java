package io.github.swiftyninja.common;

import io.github.swiftyninja.utilities.DataValidation;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Faculty class containing info relating to a faculty member
 */
public class Faculty extends Person implements Cloneable, Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    private LocalDate hired;
    private boolean tenured;
    private ArrayList<Session> classes;

    /**
     * Constructor
     * @param id faculty unique id
     */
    public Faculty(String id) throws Exception {
        super(id);
        setHired(LocalDate.now());
        setTenured(false);
        classes = new ArrayList<>();
    }

    /**
     *
     * @param id faculty unique id
     * @param name name
     * @param address address
     * @param email email
     * @param phone phone number
     * @param hired date hired
     * @param tenured are they tenured
     */
    public Faculty(String id, PersonName name, PersonAddress address, String email, String phone, LocalDate hired, boolean tenured) throws Exception {
        super(id, name, address, email, phone);
        setHired(hired);
        setTenured(tenured);
        classes = new ArrayList<>();
    }

    /**
     *
     * @return list of Sessions being taught by person
     */
    public ArrayList<Session> getClasses() { return classes; }

    /**
     *
     * @return hired date
     */
    public LocalDate getHired() { return hired; }

    /**
     *
     * @return is tenured
     */
    public boolean isTenured() { return tenured; }

    /**
     *
     * @param hired setter
     */
    public void setHired(LocalDate hired) throws Exception {
        DataValidation.ensureObjectNotNull("hired", hired);
        this.hired = hired;
    }

    /**
     *
     * @param tenured setter
     */
    public void setTenured(boolean tenured) {
        this.tenured = tenured;
    }

    /**
     *
     * @return check schedule to see if teaching
     */
    public boolean isTeaching() {
        for (Session s : classes)
            if (!s.isCancelled()) return true;
        return false;
    }

    /**
     *
     * @param sesh adds class session to faculty schedule
     */
    public void addSession(Session sesh) throws Exception {
        if (!classes.contains(sesh))
            classes.add(sesh);
    }

    /**
     *
     * @param sessionID unique id
     */
    public void removeSession(String sessionID) throws Exception {
        classes.removeIf(n -> n.getSessionId().equals(sessionID));
    }

    /**
     *
     * @param sessionId unique id
     * @return Session object to that id
     */
    public Session findSession(String sessionId) throws Exception {
        return classes.stream().filter(n -> n.getSessionId().equals(sessionId)).findAny().orElse(null);
    }

    /**
     *
     * @return all sessions
     */
    public String listOfSessions() {
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
     * @return formatted default String of info
     */
    @Override
    public String toString() {
        return "-".repeat(149) +
                String.format("%n|%-25s|%-50s|%-30s|%-15s|%-15s|%-7s|%n",
                        "Name", "Address", "Email", "Phone", "Hired Date", "Tenured") +
                "-".repeat(149) +
                String.format("%n%-120s%-15s|%-7s|%n",
                        super.toString(), hired, (isTenured() ? "Yes" : "No")) + "-".repeat(149) + "\n";
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
    public Faculty deepCopy() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(this);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bis);

        return (Faculty) in.readObject();
    }

}
