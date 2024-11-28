package edu.t1.repository;

import com.zaxxer.hikari.HikariDataSource;
import edu.t1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    @Qualifier("dataSourceMy") // чтобы брался правильный Bean из MyConfigurations
    private final HikariDataSource dataSource;

    public UserDaoImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createUser(Long id, String username) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String SQL = "INSERT INTO users(id, username) VALUES("+id+", '" + username + "')";
        int resultIns = statement.executeUpdate(SQL);
        connection.commit();
        statement.close();
    }

    public User readUser(String username) throws SQLException {
        Statement statement = dataSource.getConnection().createStatement();
        String SQL = "SELECT id, username FROM users WHERE username='" + username + "' and rownum=1";
        ResultSet result = statement.executeQuery(SQL);
        while (result.next()) {
            return new User(result.getLong("id"), result.getString("username"));
        }
        return null;
    }

    public void updateUser(Long id, String username) throws SQLException {
        Statement statement = dataSource.getConnection().createStatement();
        String SQL = "UPDATE users set username='"+username+"' WHERE id=" + id;
        int result = statement.executeUpdate(SQL);

        if (result < 0) {
            statement.close();
            throw new NoSuchElementException("Ошибка обновления записи: " + username);
        }
    }

    public void deleteUser(String username) throws SQLException {
        Statement statement = dataSource.getConnection().createStatement();
        String SQL = "DELETE FROM users where username='"+username+"'";
        int resultIns = statement.executeUpdate(SQL);
        statement.close();
    }

    public List<User> readAllUser() throws SQLException {
        List<User> users = new ArrayList<User>();
        Statement statement = dataSource.getConnection().createStatement();
        String SQL = "SELECT id, username FROM users";
        ResultSet result = statement.executeQuery(SQL);
        while (result.next()) {
            users.add(new User(result.getLong("id"), result.getString("username")));
        }
        statement.close();
        return users;
    }
}
