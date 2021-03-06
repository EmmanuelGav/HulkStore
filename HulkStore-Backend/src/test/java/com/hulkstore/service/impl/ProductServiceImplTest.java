package com.hulkstore.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hulkstore.models.Product;
import com.hulkstore.repositories.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;
 
    @Mock
    private ProductRepository productRepository;

    List<Product> products;

	private Product product;

    @Before
    public void init() {
    	product = new Product();
    	products = new ArrayList<>();
    	products.add(product);
    	products.add(product);
    	products.add(product);
    }

    @Test
	public void getProducts() {
    	when(productRepository.findAll()).thenReturn(products);

        assertEquals(productService.getProducts().isEmpty(), false);
        assertEquals(productService.getProducts().size(), products.size());
	}

    @Test
	public void getProductsAvailable() {
    	when(productRepository.getProductsAvailable()).thenReturn(products);

        assertEquals(productService.getProductsAvailable().isEmpty(), false);
        assertEquals(productService.getProductsAvailable().size(), products.size());
	}

	@Test
	public void addProduct() {
    	when(productRepository.save(product)).thenReturn(product);
        assertEquals(productService.addProduct(product), product);
	}

}
