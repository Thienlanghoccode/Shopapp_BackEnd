package com.java.PTPMHDV13.Vinfast_Sales.service;

import com.java.PTPMHDV13.Vinfast_Sales.dto.response.ProductRevenueDTO;
import com.java.PTPMHDV13.Vinfast_Sales.dto.response.RevenueDTO;
import com.java.PTPMHDV13.Vinfast_Sales.dto.response.TopSellingProduct;
import com.java.PTPMHDV13.Vinfast_Sales.entity.Cart;

import java.util.List;

public interface CartService {
     void addCart(Cart cart);
     void removeCart(Long id);
     List<Cart> getAllCarts();
     List<RevenueDTO> getAllRevenues();
     List<ProductRevenueDTO> getAllProductRevenues();
     List<TopSellingProduct> getAllTopSellingProducts();
}
