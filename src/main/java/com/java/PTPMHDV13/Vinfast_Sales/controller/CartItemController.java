package com.java.PTPMHDV13.Vinfast_Sales.controller;

import com.java.PTPMHDV13.Vinfast_Sales.entity.CartItem;
import com.java.PTPMHDV13.Vinfast_Sales.service.CartItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cartItems")
@RequiredArgsConstructor
@Tag(name = "Order Item Controller")
public class CartItemController {
    private final CartItemService cartItemService;

    @GetMapping
    public List<CartItem> getCartItems() {
        return cartItemService.getAllCartItems();
    }
}
