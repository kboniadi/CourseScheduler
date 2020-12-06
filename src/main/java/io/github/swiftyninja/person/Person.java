package io.github.swiftyninja.person;

import java.util.Objects;

public abstract class Person implements Comparable<Person> {
    private PersonName name;
    private PersonAddress address;
    private String email;
    private String phone;

    protected Person() {
        this(new PersonName(), new PersonAddress(), "", "");
    }
    protected Person(PersonName name, PersonAddress address, String email, String phone) {
        setName(name);
        setAddress(address);
        setEmail(email);
        setPhone(phone);
    }

    public String getName() { return name.toString(); }
    public String getAddress() { return address.toString(); }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }
    public void setName(PersonName name) { this.name = name; }
    public void setAddress(PersonAddress address) { this.address = address; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", address=" + address +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getName().equals(person.getName()) && getPhone().equals(person.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhone());
    }

    @Override
    public int compareTo(Person o) {
        int i;
        if ((i = this.name.compareTo(o.name)) != 0)
            return i;
        return 0;
    }
}