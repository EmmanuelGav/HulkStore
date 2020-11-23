package com.hulkstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hulkstore.models.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
