package io.github.swiftyninja.common;

import io.github.swiftyninja.utilities.DataValidation;
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
    protected Person(String id) throws Exception {
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
    protected Person(String id, PersonName name, PersonAddress address, String email, String phone) throws Exception {
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
    public void setPhone(String phone) throws Exception {
        DataValidation.ensureNonEmptyString("phone", phone);
        this.phone = phone;
    }

    /**
     *
     * @param name set name
     */
    public void setName(PersonName name) throws Exception {
        DataValidation.ensureObjectNotNull("name", name);
        this.name = name;
    }

    /**
     *
     * @param address set address (street, city, state, zip)
     */
    public void setAddress(PersonAddress address) throws Exception {
        DataValidation.ensureObjectNotNull("address", address);
        this.address = address;
    }

    /**
     *
     * @param email set email
     */
    public void setEmail(String email) throws Exception {
        DataValidation.ensureNonEmptyString("email", email);
        this.email = email;
    }

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
     * @return equals boolean
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
     * @return comparison index
     */
    @Override
    public int compareTo(Person o) {
        return getId().compareTo(o.getId());
    }
}