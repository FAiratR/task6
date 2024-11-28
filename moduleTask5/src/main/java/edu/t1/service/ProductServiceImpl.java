package edu.t1.service;

import edu.t1.models.Product;
import edu.t1.repository.ProductDao;
import edu.t1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product getProduct(Long productId) throws SQLException {
        return productDao.getProduct(productId);
    }

    public List<Product> getAllProducts(Long userId) throws SQLException {
        return productDao.getAllProduct(userId);
    }
    public void updateProduct(Long id, Long count) throws SQLException {
        productDao.updateProduct(id, count);
    }
}
