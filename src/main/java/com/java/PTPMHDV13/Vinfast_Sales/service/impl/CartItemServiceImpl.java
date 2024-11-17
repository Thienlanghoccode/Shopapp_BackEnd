package com.java.PTPMHDV13.Vinfast_Sales.service.impl;

import com.java.PTPMHDV13.Vinfast_Sales.entity.CartItem;
import com.java.PTPMHDV13.Vinfast_Sales.repository.CartItemRepository;
import com.java.PTPMHDV13.Vinfast_Sales.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteByID(Long id) {
        if(cartItemRepository.findById(id).isPresent()) {
            cartItemRepository.deleteById(id);
        } else throw new NoSuchElementException("CartItem not found");
    }
}
