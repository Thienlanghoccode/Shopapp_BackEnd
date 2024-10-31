package com.java.PTPMHDV13.Vinfast_Sales.controller;

import com.java.PTPMHDV13.Vinfast_Sales.dto.request.CategoryDTO;
import com.java.PTPMHDV13.Vinfast_Sales.dto.response.ResponseData;
import com.java.PTPMHDV13.Vinfast_Sales.entity.Category;
import com.java.PTPMHDV13.Vinfast_Sales.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Category Controller")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Get all category", description = "API get all category")
    @GetMapping
    public ResponseData<List<Category>> getAllCategories() {
        return new ResponseData<>(HttpStatus.OK.value(),"Request get all of categories",
                categoryService.getAllCategories());
    }

    @Operation(summary = "Get one category", description = "API get category by id")
    @GetMapping("/{id}")
    public ResponseData<Category> getCategoryById(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Request get category detail, categoryId = " + id,
                categoryService.getCategoryById(id));
    }

    @Operation(summary = "Create new category", description = "API create new category")
    @PostMapping
    public ResponseData<?> addCategory(@Valid @RequestBody CategoryDTO category) {
        categoryService.saveCategory(category);
        return new ResponseData<>(HttpStatus.CREATED.value(),"Category added successfully",1);
    }

    @Operation(summary = "Delete category", description = "API delete categoy by id")
    @DeleteMapping("/{categoryId}")
    public ResponseData<?> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(),"Category deleted successfully");
    }

}
