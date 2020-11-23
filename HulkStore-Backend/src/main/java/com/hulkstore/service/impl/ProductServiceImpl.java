package com.hulkstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hulkstore.models.Product;
import com.hulkstore.repositories.ProductRepository;
import com.hulkstore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

	@Override
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> getProductsAvailable() {
		return productRepository.getProductsAvailable();
	}

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}



}
