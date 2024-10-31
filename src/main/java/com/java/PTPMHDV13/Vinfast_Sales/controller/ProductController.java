package com.java.PTPMHDV13.Vinfast_Sales.controller;

import com.java.PTPMHDV13.Vinfast_Sales.dto.response.ResponseData;
import com.java.PTPMHDV13.Vinfast_Sales.entity.Product;
import com.java.PTPMHDV13.Vinfast_Sales.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Product Controller")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Get all product", description = "API get all product")
    @GetMapping
    public ResponseData<List<Product>> getAllProducts() {
        return new ResponseData<>(HttpStatus.OK.value(),"Request get all of products",
                productService.getAllProducts());
    }

    @Operation(summary = "Get product information", description = "API get product by id")
    @GetMapping("/{id}")
    public ResponseData<Product> getProductById(@PathVariable Integer id) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Request get product detail, productId = " + id,
                productService.getProductById(id));
    }

    @Operation(summary = "Create new product", description = "API create new product")
    @PostMapping
    public ResponseData<?> createProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseData<>(HttpStatus.CREATED.value(),"Product was added successfully", 1);
    }

    @Operation(summary = "Delete product", description = "API delete product by id")
    @DeleteMapping("/{productId}")
    public ResponseData<?> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(),"Product was deleted successfully", 1);
    }
}
