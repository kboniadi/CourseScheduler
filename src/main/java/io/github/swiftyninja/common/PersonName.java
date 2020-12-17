package io.github.swiftyninja.common;

import java.io.Serializable;
import java.util.Objects;

/**
 * PersonName class
 */
public class PersonName implements Comparable<PersonName>, Cloneable, Serializable {
    public String first;
    public String middle;
    public String last;

    /**
     * Constructor no-args
     */
    public PersonName() {
        this("", "", "");
    }

    /**
     *
     * @param first name
     * @param middle name
     * @param last name
     */
    public PersonName(String first, String middle, String last) {
        this.first = first;
        this.middle = middle;
        this.last = last;
    }

    /**
     *
     * @return default formatted String of data
     */
    @Override
    public String toString() {
        return first + " " + middle + " " + last;
    }

    /**
     *
     * @param o rhs object for equality
     * @return equals booloean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonName)) return false;
        PersonName that = (PersonName) o;
        return first.equals(that.first) && middle.equals(that.middle) && last.equals(that.last);
    }

    /**
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(first, middle, last);
    }

    /**
     *
     * @param o rhs object for comparison
     * @return comparison index
     */
    @Override
    public int compareTo(PersonName o) {
        return this.toString().compareTo(o.toString());
    }

    /**
     *
     * @return cloned instance
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
