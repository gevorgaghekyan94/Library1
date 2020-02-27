package src.models;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

public class Customer {

    private String name;
    private String surname;
    private String password;
    private String salt = BCrypt.gensalt();

    public Customer() {
    }

    public Customer(String name, String surname, String password) {
        this.name = name;
        this.surname = surname;
        this.password = BCrypt.hashpw(password, salt);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, salt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) &&
                Objects.equals(surname, customer.surname) &&
                Objects.equals(password, customer.password) &&
                Objects.equals(salt, customer.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, password, salt);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
