package com.hulkstore.service;

import java.util.List;

import com.hulkstore.models.Cart;

public interface CartService {

    List<Cart> addItem(List<Cart> carts);

    Cart updateItem(Cart cart, Long cartId);

    List<Cart> findAll();

    String deleteById(Long cartId);

}
