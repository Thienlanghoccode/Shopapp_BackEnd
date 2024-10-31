package com.java.PTPMHDV13.Vinfast_Sales.controller;

import com.java.PTPMHDV13.Vinfast_Sales.entity.Cart;
import com.java.PTPMHDV13.Vinfast_Sales.service.CartService;
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

    @GetMapping
    public List<Cart> getCarts() {
        return cartService.getAllCarts();
    }
}
