package com.java.PTPMHDV13.Vinfast_Sales.repository;

import com.java.PTPMHDV13.Vinfast_Sales.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByCateName(String name);
    List<Category> findByParentCategory(Category parentCategory);

    @Query(value = "SELECT c.cateName AS categoryName, SUM(ci.quantity * p.price) AS totalRevenue " +
            "FROM CartItem ci " +
            "JOIN Product p ON ci.productId = p.id " +
            "JOIN Category c ON p.cateId = c.cateId " +
            "GROUP BY c.cateName " +
            "ORDER BY totalRevenue DESC", nativeQuery = true)
    List<Object[]> getRevenueByCategory();
}
