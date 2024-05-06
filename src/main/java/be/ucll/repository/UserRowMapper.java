package be.ucll.repository;

import org.springframework.jdbc.core.RowMapper;

import be.ucll.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        // TODO Address toevoegen aan RowMapper
        return new User(rs.getString("NAME"), rs.getInt("AGE"), rs.getString("EMAIL"), rs.getString("ADDRESS"));
    }
}
