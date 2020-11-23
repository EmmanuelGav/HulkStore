package com.hulkstore.service;

import java.util.List;

import com.hulkstore.dto.ProductDTO;
import com.hulkstore.exceptions.ConflictException;
import com.hulkstore.exceptions.DataNotFoundException;
import com.hulkstore.models.Product;


public interface ProductService {

	List<Product> getProductsAvailable();
	
	List<Product> getProducts();

	Product addProduct(Product product);

    String uploadProducts(List<Product> products) throws ConflictException;

    Product findById(Long productId);

    Product updateProduct(Product product, Long productId) throws DataNotFoundException;

    String deleteById(Long productId) throws DataNotFoundException;
}
