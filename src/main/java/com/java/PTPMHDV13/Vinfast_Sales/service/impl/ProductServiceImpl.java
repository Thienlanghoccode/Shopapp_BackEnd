package com.java.PTPMHDV13.Vinfast_Sales.service.impl;

import com.java.PTPMHDV13.Vinfast_Sales.dto.request.ProductDTO;
import com.java.PTPMHDV13.Vinfast_Sales.entity.Category;
import com.java.PTPMHDV13.Vinfast_Sales.entity.Product;
import com.java.PTPMHDV13.Vinfast_Sales.mapper.ProductMapper;
import com.java.PTPMHDV13.Vinfast_Sales.repository.CategoryRepository;
import com.java.PTPMHDV13.Vinfast_Sales.repository.ProductRepository;
import com.java.PTPMHDV13.Vinfast_Sales.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final CategoryRepository categoryRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    @Override
    @Transactional
    public void addProduct(ProductDTO productDTO) {
        Product product = productMapper.toProduct(productDTO);
        Category category = categoryRepository.findById(productDTO.getCateID()).get();
        product.setCategory(category);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Integer id) {
        if(productRepository.findById(Math.toIntExact(id)).isEmpty())
            throw new NoSuchElementException("Product not found");
        productRepository.deleteById(id);
    }
}
