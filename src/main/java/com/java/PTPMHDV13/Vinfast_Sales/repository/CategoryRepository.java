package com.java.PTPMHDV13.Vinfast_Sales.repository;

import com.java.PTPMHDV13.Vinfast_Sales.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByCateName(String name);
    List<Category> findByParentCategory(Category parentCategory);
}
