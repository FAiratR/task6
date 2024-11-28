package edu.t1.service;

import edu.t1.models.User;
import edu.t1.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createUser(Long id, String username) throws SQLException {
        if (readUser(username) == null)
            userDao.createUser(id, username);
        else throw new SQLException("Клиент с указанным username="+username+" уже существует");
    }
    public User readUser(String username) throws SQLException {
        return userDao.readUser(username);
    }
    public void updateUser(Long id, String username) throws SQLException {
        if (readUser(username) != null)
            userDao.updateUser(id, username);
    }
    public void deleteUser(String username) throws SQLException {
        userDao.deleteUser(username);
    }
    public List<User> readAllUser() throws SQLException {
        return userDao.readAllUser();
    }
}
