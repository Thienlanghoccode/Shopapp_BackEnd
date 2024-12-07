package com.java.PTPMHDV13.Vinfast_Sales.repository;

import com.java.PTPMHDV13.Vinfast_Sales.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    Optional<User> findByUsername(String username);

    @Query(value = "SELECT u.username, SUM(p.price * ci.quantity) AS total_spent\n" +
                   "FROM [user] u\n" +
                   "JOIN cart c ON u.id = c.user_id\n" +
                   "JOIN cartitem ci ON c.id = ci.cart_id\n" +
                   "JOIN product p ON ci.product_id = p.id\n" +
                   "GROUP BY u.username\n", nativeQuery = true)
    List<Object[]> getTotalSpentByUser();


    @Query(value = "SELECT DATEPART(YEAR, c.buy_date) AS year, DATEPART(MONTH, c.buy_date) AS month, COUNT(DISTINCT c.user_id) AS active_users\n" +
                   "FROM cart c\n" +
                   "GROUP BY DATEPART(YEAR, c.buy_date), DATEPART(MONTH, c.buy_date)\n" +
                   "ORDER BY year DESC, month DESC\n", nativeQuery = true)
    List<Object[]> getActiveUsersByMonth();


    @Query(value = "SELECT p.name AS product_name, COUNT(DISTINCT c.user_id) AS unique_visitors, SUM(ci.quantity) AS quantity_sold, SUM(ci.quantity * p.price) AS total_revenue\n" +
                   "FROM product p\n" +
                   "LEFT JOIN cartitem ci ON p.id = ci.product_id\n" +
                   "LEFT JOIN cart c ON ci.cart_id = c.id\n" +
                   "GROUP BY p.name\n" +
                   "ORDER BY total_revenue DESC\n", nativeQuery = true)
    List<Object[]> getCustomerInteraction();


    @Query(value = "SELECT TOP 3 u.username, COUNT(DISTINCT p.id) AS products_bought, SUM(ci.quantity * p.price) AS total_spent\n" +
                   "FROM [user] u\n" +
                   "JOIN cart c ON u.id = c.user_id\n" +
                   "JOIN cartitem ci ON c.id = ci.cart_id\n" +
                   "JOIN product p ON ci.product_id = p.id\n" +
                   "GROUP BY u.username\n" +
                   "ORDER BY total_spent DESC\n", nativeQuery = true)
    List<Object[]> getTop3SpendingUsers();


    @Query(value = "SELECT u.username, COUNT(c.id) AS total_orders, AVG(ci.quantity) AS average_items_per_order\n" +
                   "FROM [user] u\n" +
                   "JOIN cart c ON u.id = c.user_id\n" +
                   "JOIN cartitem ci ON c.id = ci.cart_id\n" +
                   "GROUP BY u.username\n" +
                   "ORDER BY total_orders DESC\n", nativeQuery = true)
    List<Object[]> getOrderBehaviorAnalysis();


    @Query(value = "SELECT u.username, COUNT(c.id) AS purchase_frequency, DATEDIFF(DAY, MAX(c.buy_date), GETDATE()) AS last_purchase_days\n" +
                   "FROM [user] u\n" +
                   "JOIN cart c ON u.id = c.user_id\n" +
                   "GROUP BY u.username\n" +
                   "HAVING COUNT(c.id) > 1 AND DATEDIFF(DAY, MAX(c.buy_date), GETDATE()) > 180\n", nativeQuery = true)
    List<Object[]> getUserOver180days();


    @Query(value = "WITH RFM AS (\n" +
                   "    SELECT u.username,\n" +
                   "           DATEDIFF(DAY, MAX(c.buy_date), GETDATE()) AS recency,\n" +
                   "           COUNT(c.id) AS frequency,\n" +
                   "           SUM(ci.quantity * p.price) AS monetary\n" +
                   "    FROM [user] u\n" +
                   "    JOIN cart c ON u.id = c.user_id\n" +
                   "    JOIN cartitem ci ON c.id = ci.cart_id\n" +
                   "    JOIN product p ON ci.product_id = p.id\n" +
                   "    GROUP BY u.username\n" +
                   ")\n" +
                   "SELECT username,\n" +
                   "       CASE\n" +
                   "           WHEN recency <= 100 THEN 'Very Active'\n" +
                   "           WHEN recency BETWEEN 100 AND 200 THEN 'Active'\n" +
                   "           ELSE 'Less Active'\n" +
                   "       END AS recency_segment,\n" +
                   "       CASE\n" +
                   "           WHEN frequency >= 5 THEN 'Frequent Buyer'\n" +
                   "           WHEN frequency BETWEEN 2 AND 4 THEN 'Occasional Buyer'\n" +
                   "           ELSE 'First Time Buyer'\n" +
                   "       END AS frequency_segment,\n" +
                   "       CASE\n" +
                   "           WHEN monetary >= 13000000000 THEN 'High Spender'\n" +
                   "           WHEN monetary BETWEEN 1000000000 AND 12999999999 THEN 'Medium Spender'\n" +
                   "           ELSE 'Low Spender'\n" +
                   "       END AS monetary_segment\n" +
                   "FROM RFM\n", nativeQuery = true)
    List<Object[]> getCustomerRFMAnalysis();

}
