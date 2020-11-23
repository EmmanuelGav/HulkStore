package com.hulkstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hulkstore.dto.ProductDTO;
import com.hulkstore.exceptions.ConflictException;
import com.hulkstore.exceptions.DataNotFoundException;
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

	@Override
	public String uploadProducts(List<Product> products) throws ConflictException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findById(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product updateProduct(Product product, Long productId) throws DataNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteById(Long productId) throws DataNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}


}
