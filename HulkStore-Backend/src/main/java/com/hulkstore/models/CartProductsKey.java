package com.hulkstore.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CartProductsKey implements Serializable {

	private static final long serialVersionUID = -8581085202205035746L;

	@Column(name = "cart_id")
    Long cartId;
 
    @Column(name = "product_id")
    Long productId;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
    
}
