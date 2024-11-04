package com.java.PTPMHDV13.Vinfast_Sales.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class CategoryDTO {

    @NotEmpty(message = "Category name must not be null")
    private String categoryName;

    private int parentId;
}
