package com.java.PTPMHDV13.Vinfast_Sales.service.impl;

import com.java.PTPMHDV13.Vinfast_Sales.dto.request.CategoryDTO;
import com.java.PTPMHDV13.Vinfast_Sales.entity.Category;
import com.java.PTPMHDV13.Vinfast_Sales.exception.AlReadyExistException;
import com.java.PTPMHDV13.Vinfast_Sales.repository.CategoryRepository;
import com.java.PTPMHDV13.Vinfast_Sales.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new NoSuchElementException("Category not found"));
    }

    @Override
    @Transactional
    public void saveCategory(CategoryDTO categoryDTO) {
        if(categoryRepository.existsByCateName(categoryDTO.getCategoryName()))
            throw new AlReadyExistException("Category already exists");
        Category category = new Category();
        category.setCateName(categoryDTO.getCategoryName());
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void deleteCategoryById(Long id) {
        if(categoryRepository.findById(Math.toIntExact(id)).isEmpty())
            throw new NoSuchElementException("Category not found");
        else categoryRepository.deleteById(Math.toIntExact(id));
    }
}
