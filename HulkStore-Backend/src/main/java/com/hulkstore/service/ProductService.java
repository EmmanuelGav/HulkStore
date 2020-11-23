package com.hulkstore.service;

import java.util.List;

import com.hulkstore.models.Product;


public interface ProductService {

	List<Product> getProductsAvailable();
	
	List<Product> getProducts();

	Product addProduct(Product product);

}
