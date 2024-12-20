package com.java.PTPMHDV13.Vinfast_Sales.repository;

import com.java.PTPMHDV13.Vinfast_Sales.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query(value = "SELECT FORMAT(c.buy_date, 'MM-yyyy') AS month, " +
            "SUM(ci.quantity * p.price) AS total_revenue " +
            "FROM cartitem ci " +
            "JOIN cart c ON ci.cart_id = c.id " +
            "JOIN product p ON ci.product_id = p.id " +
            "GROUP BY FORMAT(c.buy_date, 'MM-yyyy') " +
            "ORDER BY month ASC", nativeQuery = true)
    List<Object[]> getMonthlyRevenueRaw();

    @Query(value = "SELECT p.id AS product_id, " +
            "p.name AS product_name, " +
            "SUM(ci.quantity * p.price) AS total_revenue " +
            "FROM cartitem ci " +
            "JOIN cart c ON ci.cart_id = c.id " +
            "JOIN product p ON ci.product_id = p.id " +
            "GROUP BY p.id, p.name " +
            "ORDER BY total_revenue DESC",
            nativeQuery = true)
    List<Object[]> getProductRevenue();

    @Query(value = "SELECT TOP 5 " +
            "p.id AS product_id, " +
            "p.name AS product_name, " +
            "SUM(ci.quantity) AS total_quantity, " +
            "SUM(ci.quantity * p.price) AS total_revenue " +
            "FROM cartitem ci " +
            "JOIN product p ON ci.product_id = p.id " +
            "GROUP BY p.id, p.name " +
            "ORDER BY total_quantity DESC", nativeQuery = true)
    List<Object[]> getTopSellingProducts();
}
