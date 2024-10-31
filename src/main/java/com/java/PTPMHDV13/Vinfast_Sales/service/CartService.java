package com.java.PTPMHDV13.Vinfast_Sales.service;

import com.java.PTPMHDV13.Vinfast_Sales.entity.Cart;

import java.util.List;

public interface CartService {
    public void addCart(Cart cart);
    public void removeCart(Long id);
    public List<Cart> getAllCarts();
}
