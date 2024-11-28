package edu.t1.service;

import edu.t1.repository.Payable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;

@Service
public class PaySeviceImpl implements PayService{
    Payable payable;

    @Autowired
    public PaySeviceImpl(Payable payable) {
        this.payable = payable;
    }

    public String payExecute(Long userId, Long productId, Long count) throws SQLException {
        return payable.payExecute(userId, productId, count);
    }
}
