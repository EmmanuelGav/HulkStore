package com.hulkstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hulkstore.dto.CartDTO;
import com.hulkstore.exceptions.DataNotFoundException;
import com.hulkstore.models.Cart;
import com.hulkstore.service.CartService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartService cartService;
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addCart(@RequestBody CartDTO cartDTO) throws DataNotFoundException {
        return new ResponseEntity<Cart>(cartService.saveCart(cartDTO), HttpStatus.CREATED);
    }
}
