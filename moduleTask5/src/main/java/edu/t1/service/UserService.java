package edu.t1.service;

import edu.t1.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    public void createUser(Long id, String username) throws SQLException;
    public User readUser(String username) throws SQLException;
    public void updateUser(Long id, String username) throws SQLException;
    public void deleteUser(String username) throws SQLException;
    public List<User> readAllUser() throws SQLException;
}
