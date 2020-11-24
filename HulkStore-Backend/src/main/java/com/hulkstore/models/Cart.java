package com.hulkstore.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.hulkstore.enums.Status;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "cart")
    private Set<CartProducts> cartProducts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private Status status;
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<CartProducts> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(Set<CartProducts> cartProducts) {
		this.cartProducts = cartProducts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
