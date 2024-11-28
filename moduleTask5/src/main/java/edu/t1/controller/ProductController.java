package edu.t1.controller;

import edu.t1.models.Product;
import edu.t1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/getProduct/{productId}")
    public Product getProduct(@PathVariable("productId") Long productId) throws SQLException {
        return productService.getProduct(productId);
    }

    @GetMapping(value = "/getAllProduct/{userId}")
    public List<Product> getAllProducts(@PathVariable("userId")Long userId) throws SQLException {
        return productService.getAllProducts(userId);
    }

    @PostMapping(value = "/updateProduct/{productId}/{count}")
    public void updateProduct(@PathVariable("productId")Long productId, @PathVariable("count")Long count) throws SQLException {
        productService.updateProduct(productId, count);
    }
}
