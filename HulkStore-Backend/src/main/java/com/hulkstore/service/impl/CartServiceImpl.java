package com.hulkstore.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hulkstore.models.Cart;
import com.hulkstore.service.CartService;


@Service
public class CartServiceImpl implements CartService {


    @Override
    public List<Cart> addItem(List<Cart> carts) {
        return null;
    }

    @Override
    public Cart updateItem(Cart cart, Long cartId) {
        return null;
    }

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public String deleteById(Long cartId) {
        return null;
    }
}
