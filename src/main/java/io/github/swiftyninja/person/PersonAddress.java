package io.github.swiftyninja.person;

import java.util.Objects;

public class PersonAddress implements Comparable<PersonAddress> {
    public String street;
    public String city;
    public String state;
    public int zip;

    public PersonAddress() {
        this("", "", "", 0);
    }

    public PersonAddress(String street, String city, String state, int zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    @Override
    public String toString() {
        return street + ", " + city + ", " + state + " " + zip;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonAddress)) return false;
        PersonAddress that = (PersonAddress) o;
        return zip == that.zip && street.equals(that.street) && city.equals(that.city) && state.equals(that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, state, zip);
    }

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
}
