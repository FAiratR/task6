package edu.t1.repository;

import java.sql.SQLException;

public interface Payable {
    public String payExecute(Long userId, Long productId, Long count) throws SQLException;
}
