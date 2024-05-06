package be.ucll.model;

import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Table(name = "users")
public class User {

    private String name;
    private int age;
    // TODO Hibernate validation toevoegen voor email field
    @NotBlank(message = "User email is required.")
    @Email(message = "User email has invalid format.")
    private String email;
    // TODO address field toe voegen met hibernate validation
    @NotBlank(message = "User address is required.")
    private String address;

    // TODO Address toevoegen aan constructor
    public User(String name, int age, String email, String address) {
        setName(name);
        setAge(age);
        setEmail(email);
        setAddress(address);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new DomainException("User name is required.");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 140) {
            throw new DomainException("User age must be between 0 and 140 (including).");
        }
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // TODO Hibernate validation toevoegen voor email field
        // if (email == null || email.isBlank()) {
        // throw new DomainException("User email is required.");
        // }
        // if (!email.contains("@")) {
        // throw new DomainException("User email has invalid format.");
        // }
        if (this.email != null && !this.email.equals(email)) {
            throw new DomainException("User email cannot be changed.");
        }
        this.email = email;
    }

    // TODO Getters en setters voor address toevoegen
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        // TODO Hibernate validation toevoegen voor address field
        // if (address == null || address.isBlank()) {
        // throw new DomainException("User address is required.");
        // }
        this.address = address;
    }
}