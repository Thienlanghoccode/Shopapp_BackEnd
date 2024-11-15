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
        if(categoryRepository.findById(categoryDTO.getParentId()).isEmpty()){
            category.setParentCategory(null);
        } else {
            category.setParentCategory(categoryRepository.findById(categoryDTO.getParentId()).get());
        }
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void deleteCategoryById(Long id) {
        Category parentCategory  = getCategoryById(id);
        List<Category> childCategories = categoryRepository.findByParentCategory(parentCategory);
        childCategories.forEach(child -> child.setParentCategory(null));
        categoryRepository.saveAll(childCategories);
        categoryRepository.deleteById(Math.toIntExact(parentCategory.getId()));
    }

    @Override
    @Transactional
    public void updateCategory(Long id, CategoryDTO category) {
        Category categoryToUpdate = getCategoryById(id);
        categoryToUpdate.setCateName(category.getCategoryName());
        categoryRepository.save(categoryToUpdate);
    }
}
