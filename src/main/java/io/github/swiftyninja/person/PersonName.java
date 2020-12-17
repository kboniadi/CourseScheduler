package io.github.swiftyninja.person;

import java.io.Serializable;
import java.util.Objects;

public class PersonName implements Comparable<PersonName>, Cloneable, Serializable {
    public String first;
    public String middle;
    public String last;

    public PersonName() {
        this("", "", "");
    }

    public PersonName(String first, String middle, String last) {
        this.first = first;
        this.middle = middle;
        this.last = last;
    }

    @Override
    public String toString() {
        return first + " " + middle + " " + last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonName)) return false;
        PersonName that = (PersonName) o;
        return first.equals(that.first) && middle.equals(that.middle) && last.equals(that.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, middle, last);
    }

    @Override
    public int compareTo(PersonName o) {
        return this.toString().compareTo(o.toString());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
