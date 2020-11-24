package com.hulkstore.service;

import java.util.List;

import com.hulkstore.dto.CartDTO;
import com.hulkstore.exceptions.DataNotFoundException;
import com.hulkstore.models.Cart;

public interface CartService {

    Cart saveCart(CartDTO cartDTO) throws DataNotFoundException;

}
