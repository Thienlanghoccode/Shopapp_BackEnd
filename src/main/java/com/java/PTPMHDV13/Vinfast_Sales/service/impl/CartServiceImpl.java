package com.java.PTPMHDV13.Vinfast_Sales.service.impl;

import com.java.PTPMHDV13.Vinfast_Sales.entity.Cart;
import com.java.PTPMHDV13.Vinfast_Sales.repository.CartRepository;
import com.java.PTPMHDV13.Vinfast_Sales.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
