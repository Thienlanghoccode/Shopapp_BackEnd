package com.java.PTPMHDV13.Vinfast_Sales.controller;

import com.java.PTPMHDV13.Vinfast_Sales.dto.response.ResponseData;
import com.java.PTPMHDV13.Vinfast_Sales.entity.CartItem;
import com.java.PTPMHDV13.Vinfast_Sales.service.CartItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartItems")
@RequiredArgsConstructor
@Tag(name = "Order Item Controller")
public class CartItemController {
    private final CartItemService cartItemService;

    @Operation(summary = "Get all cart item", description = "API get all cart items")
    @GetMapping
    public List<CartItem> getCartItems() {
        return cartItemService.getAllCartItems();
    }

    @Operation(summary = "Delete cart item", description = "API delete cart items by id")
    @DeleteMapping("/{id}")
    public ResponseData<?> deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteByID(id);
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(),"Cart Items deleted successfully");
    }
}
