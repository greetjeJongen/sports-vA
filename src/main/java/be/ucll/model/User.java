package be.ucll.model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Table(name = "users")
public class User {

    private String name;
    private int age;
    private String email;
    @NotBlank(message = "User address is required and has more than (>) 5 characters.")
    @Length(min = 5, message = "User address is required and has more than (>) 5 characters")
    private String address;

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
        if (this.email != null && !this.email.equals(email)) {
            throw new DomainException("User email cannot be changed.");
        }
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
