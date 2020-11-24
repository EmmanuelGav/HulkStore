package com.hulkstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hulkstore.models.CartProducts;
import com.hulkstore.models.CartProductsKey;

@Repository
public interface CartProductsRepository extends JpaRepository<CartProducts, CartProductsKey> {
}
