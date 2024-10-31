package com.java.PTPMHDV13.Vinfast_Sales.service;

import com.java.PTPMHDV13.Vinfast_Sales.dto.request.CategoryDTO;
import com.java.PTPMHDV13.Vinfast_Sales.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    void saveCategory(CategoryDTO category);
    void deleteCategoryById(Long id);
}
