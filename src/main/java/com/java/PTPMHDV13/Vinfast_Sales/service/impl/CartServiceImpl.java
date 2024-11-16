package com.java.PTPMHDV13.Vinfast_Sales.service.impl;

import com.java.PTPMHDV13.Vinfast_Sales.dto.response.ProductRevenueDTO;
import com.java.PTPMHDV13.Vinfast_Sales.dto.response.RevenueDTO;
import com.java.PTPMHDV13.Vinfast_Sales.dto.response.TopSellingProduct;
import com.java.PTPMHDV13.Vinfast_Sales.entity.Cart;
import com.java.PTPMHDV13.Vinfast_Sales.repository.CartRepository;
import com.java.PTPMHDV13.Vinfast_Sales.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    @Transactional
    public void addCart(Cart cart) {
        if(cartRepository.existsById(cart.getId())) {
            throw new NoSuchElementException("Cart already exists");
        }
        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void removeCart(Long id) {
        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
        } else throw new NoSuchElementException("Cart not found");
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public List<RevenueDTO> getAllRevenues() {
        List<Object []> result = cartRepository.getMonthlyRevenueRaw();
        List<RevenueDTO> revenueDTOList = new ArrayList<>();

        for (Object[] row : result) {
            String month = (String) row[0];
            BigDecimal totalRevenue = (BigDecimal) row[1];
            revenueDTOList.add(RevenueDTO.builder()
                            .month(month)
                            .totalRevenue(totalRevenue)
                    .build());
        }

        return revenueDTOList;
    }

    @Override
    public List<ProductRevenueDTO> getAllProductRevenues() {
        List<Object[]> results = cartRepository.getProductRevenue();
        List<ProductRevenueDTO> productRevenueDTOList = new ArrayList<>();
        for (Object[] row : results) {
            Integer productId = (Integer) row[0];
            String productName = (String) row[1];
            BigDecimal totalRevenue = (BigDecimal) row[2];
            productRevenueDTOList.add(ProductRevenueDTO.builder()
                            .productId(productId)
                            .productName(productName)
                            .totalRevenue(totalRevenue)
                    .build());
        }
        return productRevenueDTOList;
    }

    @Override
    public List<TopSellingProduct> getAllTopSellingProducts() {
        List<Object[]> results = cartRepository.getTopSellingProducts();
        List<TopSellingProduct> topSellingProductList = new ArrayList<>();
        for (Object[] row : results) {
            Integer productId = (Integer) row[0];
            String productName = (String) row[1];
            Integer totalQuantity = (Integer) row[2];
            BigDecimal totalRevenue = (BigDecimal) row[3];
            topSellingProductList.add(TopSellingProduct.builder()
                            .productId(productId)
                            .productName(productName)
                            .totalQuantity(totalQuantity)
                            .totalRevenue(totalRevenue)
                    .build());
        }
        return topSellingProductList;
    }

}
