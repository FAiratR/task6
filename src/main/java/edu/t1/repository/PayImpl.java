package edu.t1.repository;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class PayImpl implements Payable{

    @Qualifier("dataSourcePay")  // чтобы брался правильный Bean из PayConfigurations
    private final HikariDataSource dataSource;

    @Autowired
    public PayImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String payExecute(Long userId, Long productId, Long count) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = dataSource.getConnection().createStatement();
        String SQL = "INSERT INTO pays(product, userId, count) VALUES(" + productId + ", " + userId + ", " + count + ")";
        int resultIns = statement.executeUpdate(SQL);
        connection.commit();
        if (resultIns < 0) {
            statement.close();
            throw new SQLException("Ошибка при исполнении платежа: " + Thread.currentThread().getStackTrace());
        }

        return "Платеж исполнен";
    }
}
