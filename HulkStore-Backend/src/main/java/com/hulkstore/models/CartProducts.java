package com.hulkstore.models;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.hulkstore.enums.Status;

@Entity 
public class CartProducts {


    @EmbeddedId
    CartProductsKey id = new CartProductsKey();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    Cart cart;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    Product product;

	private Integer amount;
	
	private Integer totalPrice;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public CartProductsKey getId() {
		return id;
	}

	public void setId(CartProductsKey id) {
		this.id = id;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

}
