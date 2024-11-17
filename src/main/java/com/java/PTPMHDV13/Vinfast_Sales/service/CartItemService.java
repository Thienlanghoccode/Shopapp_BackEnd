package com.java.PTPMHDV13.Vinfast_Sales.service;

import com.java.PTPMHDV13.Vinfast_Sales.entity.CartItem;

import java.util.List;

public interface CartItemService {
    List<CartItem> getAllCartItems();
    void deleteByID(Long id);
}
