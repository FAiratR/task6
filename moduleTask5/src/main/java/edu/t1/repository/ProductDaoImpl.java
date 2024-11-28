package edu.t1.repository;

import com.zaxxer.hikari.HikariDataSource;
import edu.t1.models.Product;
import edu.t1.models.TypePruduct;
import jakarta.persistence.NoResultException;
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
public class ProductDaoImpl implements ProductDao {

    @Autowired
    @Qualifier("dataSourceMy") // чтобы брался правильный Bean из MyConfigurations
    private final HikariDataSource dataSource;

    public ProductDaoImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Product getProduct(Long productId) throws SQLException {

        Statement statement = dataSource.getConnection().createStatement();
        String SQL = "SELECT id, userId, accNUm, balance, type FROM products WHERE id=" + productId;
        ResultSet result = statement.executeQuery(SQL);
        while (result.next()) {
            return new Product(result.getLong("id"),
                                result.getLong("userId"),
                                result.getString("accNum"),
                                result.getLong("balance"),
                                TypePruduct.valueOf(result.getString("type")));
        }
        statement.close();
        throw new NoResultException("Не найден продукт по id="+productId);
    }

    public List<Product> getAllProduct(Long userId) throws SQLException {
        List<Product> products = new ArrayList<Product>();
        Statement statement = dataSource.getConnection().createStatement();
        String SQL = "SELECT id, userId, accNUm, balance, type FROM products WHERE userId=" + userId;
        ResultSet result = statement.executeQuery(SQL);
        while (result.next()) {
            products.add(new Product(result.getLong("id"),
                    result.getLong("userId"),
                    result.getString("accNum"),
                    result.getLong("balance"),
                    TypePruduct.valueOf(result.getString("type"))));
        }
        statement.close();
        if (products.isEmpty()) throw new NoResultException("В таблице products данные не найдены");
        return products;
    }

    public void updateProduct(Long id, Long count) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String SQL = "UPDATE products set balance=balance-"+count+" WHERE id=" + id;
        int result = statement.executeUpdate(SQL);
        connection.commit();
        if (result < 0) {
            statement.close();
            throw new NoSuchElementException("Ошибка обновления записи(id="+id+") в таблице products");
        }
        statement.close();
        connection.close();
    }
}
