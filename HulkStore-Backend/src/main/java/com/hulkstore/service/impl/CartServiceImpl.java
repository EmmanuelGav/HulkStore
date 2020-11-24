package com.hulkstore.service.impl;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hulkstore.dto.CartDTO;
import com.hulkstore.enums.Status;
import com.hulkstore.exceptions.DataNotFoundException;
import com.hulkstore.models.Cart;
import com.hulkstore.models.CartProducts;
import com.hulkstore.repositories.CartProductsRepository;
import com.hulkstore.repositories.CartRepository;
import com.hulkstore.repositories.ProductRepository;
import com.hulkstore.repositories.UserRepository;
import com.hulkstore.service.CartService;


@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CartProductsRepository cartProductsRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	@Transactional
	public Cart saveCart(CartDTO cartDTO) throws DataNotFoundException {
		Cart cart = new Cart();
		cart.setUser(userRepository.findById(cartDTO.getUserId())
				.orElseThrow(() -> new DataNotFoundException("Error: User is not found.")));
		cart.setOrderDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		cart.setStatus(Status.Ordered);
		
		Cart cartNew = cartRepository.save(cart);
		
		cart.setId(cartNew.getId());
		cart = cartRepository.save(cart);
		
		for (CartProducts cartProducts : cartDTO.getCartProducts()) {
			cartProducts.setCart(cart);
			cartProducts.getId().setCartId(cart.getId());
			cartProducts.getId().setProductId(cartProducts.getProduct().getId());
			cartProductsRepository.save(cartProducts);
			cartProducts.getProduct().setStock(cartProducts.getProduct().getStock()-cartProducts.getAmount());
			productRepository.save(cartProducts.getProduct());
		}
		
		return cart;
	}

}
