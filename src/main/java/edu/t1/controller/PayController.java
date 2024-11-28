package edu.t1.controller;

import edu.t1.models.Product;
import edu.t1.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.sql.SQLException;
import java.util.List;

@RestController
public class PayController {
    @Autowired
    private PayService payService;

    @GetMapping("/payService/getAllProduct/{userId}")
    public List<Product> getAllProducts(@PathVariable("userId")Long userId) throws SQLException {
        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
        return restClient
                .get()
                .uri("/product/getAllProduct/"+userId)
                .retrieve()
                .body(List.class);
    }

    @GetMapping("/payService/payExecute/{userId}/{productId}/{count}")
    public String getPayExecute(@PathVariable("userId")Long userId, @PathVariable("productId")Long productId, @PathVariable("count")Long count) throws SQLException {
        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
        restClient
                .post()
                .uri("/product/updateProduct/"+productId+"/"+count)
                .retrieve().body(List.class);

        String res = payService.payExecute(userId, productId, count);
        return res;
    }
}
