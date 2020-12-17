package io.github.swiftyninja.person;

import io.github.swiftyninja.utilities.FileParser;

import java.io.Serializable;
import java.util.Objects;

public abstract class Person implements Comparable<Person>, Serializable {
    private PersonName name;
    private PersonAddress address;
    private String email;
    private String phone;
    private final String id;

    protected Person(String id) {
        this(id, new PersonName(), new PersonAddress(), "", "");
    }
    protected Person(String id, PersonName name, PersonAddress address, String email, String phone) {
        this.id = id;
        setName(name);
        setAddress(address);
        setEmail(email);
        setPhone(phone);
    }

    public String getName() { return name.toString(); }
    public String getAddress() { return address.toString(); }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getId() { return id; }

    public void setPhone(String phone) { this.phone = phone; }
    public void setName(PersonName name) { this.name = name; }
    public void setAddress(PersonAddress address) { this.address = address; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return String.format("|%-25s|%-50s|%-30s|%-15s|", name, FileParser.abbreviate(address.toString(), 50), FileParser.abbreviate(email, 30), phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getId().equals(((Person) o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public int compareTo(Person o) {
        return getId().compareTo(o.getId());
    }
}