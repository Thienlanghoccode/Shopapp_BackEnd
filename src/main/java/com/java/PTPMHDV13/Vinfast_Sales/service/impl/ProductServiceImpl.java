package com.java.PTPMHDV13.Vinfast_Sales.service.impl;

import com.java.PTPMHDV13.Vinfast_Sales.entity.Product;
import com.java.PTPMHDV13.Vinfast_Sales.exception.AlReadyExistException;
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
    public void addProduct(Product product) {
        if(productRepository.findById(Math.toIntExact(product.getId())).isPresent())
            throw new AlReadyExistException("Product already exists");
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
