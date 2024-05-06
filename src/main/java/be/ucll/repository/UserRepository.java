package be.ucll.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import be.ucll.model.User;

@Repository
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
    }

    // TODO ADDRESS toevoegen aan insert statement
    public void addUser(User user) {
        jdbcTemplate.update("INSERT INTO users (NAME, AGE, EMAIL, ADDRESS) VALUES (?, ?, ?, ?)", user.getName(),
                user.getAge(), user.getEmail(), user.getAddress());
    }

    // TODO updateUser toe te voegen
    public void updateUser(User user) {
        jdbcTemplate.update("UPDATE USERS SET NAME = ?, AGE = ?, ADDRESS = ? WHERE EMAIL = ?", user.getName(),
                user.getAge(), user.getAddress(), user.getEmail());
    }

    public void deleteUser(User user) {
        jdbcTemplate.update("DELETE FROM USERS WHERE EMAIL = ?", user.getEmail());
    }

    public User getUserByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM users WHERE users.email = ?", new UserRowMapper(), email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
