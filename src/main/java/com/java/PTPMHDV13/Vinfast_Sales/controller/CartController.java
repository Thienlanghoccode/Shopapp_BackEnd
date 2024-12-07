package com.java.PTPMHDV13.Vinfast_Sales.controller;

import com.java.PTPMHDV13.Vinfast_Sales.dto.response.product.ProductRevenueDTO;
import com.java.PTPMHDV13.Vinfast_Sales.dto.response.product.RevenueDTO;
import com.java.PTPMHDV13.Vinfast_Sales.dto.response.product.TopSellingProduct;
import com.java.PTPMHDV13.Vinfast_Sales.entity.Cart;
import com.java.PTPMHDV13.Vinfast_Sales.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
@Tag(name = "Order Controller")
public class CartController {

    private final CartService cartService;

    @Operation(summary = "Get all cart", description = "API get all cart")
    @GetMapping
    public List<Cart> getCarts() {
        return cartService.getAllCarts();
    }

    @Operation(summary = "Get monthly revenue", description = "API get all monthly revenue")
    @GetMapping("/monthly-revenue")
    public List<RevenueDTO> getMonthlyRevenue() {
        return  cartService.getAllRevenues();
    }

    @Operation(summary = "Get product revenue", description = "API get all product revenue")
    @GetMapping("/product-revenue")
    public List<ProductRevenueDTO> getProductRevenue() {
        return  cartService.getAllProductRevenues();
    }

    @Operation(summary = "Get selling product", description = "API get top 5 best-selling product")
    @GetMapping("/top-selling-product")
    public List<TopSellingProduct> getTop5BestSellingProduct() {
        return  cartService.getAllTopSellingProducts();
    }
}
