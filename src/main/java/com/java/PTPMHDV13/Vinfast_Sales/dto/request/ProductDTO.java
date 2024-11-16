package com.java.PTPMHDV13.Vinfast_Sales.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    @NotBlank(message =  "Product name must not be blank !")
    private String productName;

    @NotBlank(message =  "Product price must not be blank !")
    private Double productPrice;

    @NotBlank(message =  "Product description must not be blank !")
    private String productDescription;

    @NotNull(message = "Product URL image must not be null !")
    private String productImage;

    @NotBlank(message =  "Product quantity must not be blank !")
    private int inStock;

    @NotBlank(message =  "Category id must not be blank !")
    private int cateID;
}
