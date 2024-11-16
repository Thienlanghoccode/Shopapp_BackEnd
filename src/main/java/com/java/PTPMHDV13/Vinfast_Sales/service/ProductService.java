package com.java.PTPMHDV13.Vinfast_Sales.service;

import com.java.PTPMHDV13.Vinfast_Sales.dto.request.ProductDTO;
import com.java.PTPMHDV13.Vinfast_Sales.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Integer id);
    void addProduct(ProductDTO product);
    void deleteProduct(Integer id);
}
