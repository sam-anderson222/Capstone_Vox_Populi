package com.example.VoxPopuli.repository.mappers;

import com.example.VoxPopuli.model.User;
import org.springframework.jdbc.core.RowMapper;

public class UserMapper {
    public static RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();

            user.setUserId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setUserPassword(rs.getString("user_password"));
            user.setAccessLevel(rs.getInt("access_level"));

            return user;
        };
    }
}
