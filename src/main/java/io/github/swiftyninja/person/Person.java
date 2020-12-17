package io.github.swiftyninja.person;

import io.github.swiftyninja.utilities.FileParser;
import java.io.Serializable;
import java.util.Objects;

/**
 * Person class (abstract)
 */
public abstract class Person implements Comparable<Person>, Serializable {
    private PersonName name;
    private PersonAddress address;
    private String email;
    private String phone;
    private final String id;

    /**
     *
     * @param id default id
     */
    protected Person(String id) {
        this(id, new PersonName(), new PersonAddress(), "", "");
    }

    /**
     *
     * @param id Person id
     * @param name first, middle, last
     * @param address street, city, state, zip
     * @param email email
     * @param phone phone number
     */
    protected Person(String id, PersonName name, PersonAddress address, String email, String phone) {
        this.id = id;
        setName(name);
        setAddress(address);
        setEmail(email);
        setPhone(phone);
    }

    /**
     *
     * @return get name
     */
    public String getName() { return name.toString(); }

    /**
     *
     * @return get address
     */
    public String getAddress() { return address.toString(); }

    /**
     *
     * @return get email
     */
    public String getEmail() { return email; }

    /**
     *
     * @return get phone number
     */
    public String getPhone() { return phone; }

    /**
     *
     * @return get id
     */
    public String getId() { return id; }

    /**
     *
     * @param phone set number
     */
    public void setPhone(String phone) { this.phone = phone; }

    /**
     *
     * @param name set name
     */
    public void setName(PersonName name) { this.name = name; }

    /**
     *
     * @param address set address (street, city, state, zip)
     */
    public void setAddress(PersonAddress address) { this.address = address; }

    /**
     *
     * @param email set email
     */
    public void setEmail(String email) { this.email = email; }

    /**
     *
     * @return default formatted String of data
     */
    @Override
    public String toString() {
        return String.format("|%-25s|%-50s|%-30s|%-15s|", name, FileParser.abbreviate(address.toString(), 50), FileParser.abbreviate(email, 30), phone);
    }

    /**
     *
     * @param o rhs object for equality check
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getId().equals(((Person) o).getId());
    }

    /**
     *
     * @return hash item
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    /**
     *
     * @param o rhs object for comparison
     * @return
     */
    @Override
    public int compareTo(Person o) {
        return getId().compareTo(o.getId());
    }
}