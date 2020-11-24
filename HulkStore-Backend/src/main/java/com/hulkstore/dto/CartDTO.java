package com.hulkstore.dto;

import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.hulkstore.models.CartProducts;
import com.hulkstore.models.User;

public class CartDTO {

    private long id;
    private Long userId;
    private Set<CartProducts> cartProducts;
    private String status;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Set<CartProducts> getCartProducts() {
		return cartProducts;
	}
	public void setCartProducts(Set<CartProducts> cartProducts) {
		this.cartProducts = cartProducts;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
