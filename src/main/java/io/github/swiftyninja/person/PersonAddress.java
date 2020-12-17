package io.github.swiftyninja.person;

import java.io.Serializable;
import java.util.Objects;

/**
 * PersonAddress class
 */
public class PersonAddress implements Comparable<PersonAddress>, Cloneable, Serializable {
    public String street;
    public String city;
    public String state;
    public int zip;

    /**
     * Constructor no-args
     */
    public PersonAddress() {
        this("", "", "", 0);
    }

    /**
     * Constructor
     * @param street name
     * @param city name
     * @param state init
     * @param zip number
     */
    public PersonAddress(String street, String city, String state, int zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    /**
     *
     * @return default formatted String of info
     */
    @Override
    public String toString() {
        return street + ", " + city + ", " + state + " " + zip;
    }


    /**
     *
     * @param o rhs object for equality
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonAddress)) return false;
        PersonAddress that = (PersonAddress) o;
        return zip == that.zip && street.equals(that.street) && city.equals(that.city) && state.equals(that.state);
    }

    /**
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(street, city, state, zip);
    }

    /**
     *
     * @param o rhs object for comparison
     * @return
     */
    @Override
    public int compareTo(PersonAddress o) {
        int i;
        if ((i = this.street.compareTo(o.street)) != 0)
            return i;
        if ((i = this.city.compareTo(o.city)) != 0)
            return i;
        if ((i = this.state.compareTo(o.state)) != 0)
            return i;
        return Integer.compare(this.zip, o.zip);
    }

    /**
     *
     * @return cloned instance of this class
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
