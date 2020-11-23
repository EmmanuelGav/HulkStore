package com.hulkstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hulkstore.dto.ProductDTO;
import com.hulkstore.dto.SuccessResponseDTO;
import com.hulkstore.exceptions.ConflictException;
import com.hulkstore.exceptions.DataNotFoundException;
import com.hulkstore.models.Product;
import com.hulkstore.service.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/all-available")
    public  ResponseEntity<?> getProductsAvailable() {
    	return new ResponseEntity<List<Product>>(productService.getProductsAvailable(), HttpStatus.OK);
    }
    
    @GetMapping(value = "/all")
    public  ResponseEntity<?> getProducts() {
    	return new ResponseEntity<List<Product>>(productService.getProducts(), HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        return new ResponseEntity<Product>(productService.addProduct(product), HttpStatus.CREATED);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addMultipleProducts(@RequestBody List<Product> products) throws ConflictException {
        return  new ResponseEntity<SuccessResponseDTO>(new SuccessResponseDTO(productService.uploadProducts(products)), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{productId}", produces = "application/json")
    public ResponseEntity<?> getProduct(@PathVariable Long productId) {
        return new ResponseEntity<Product>(productService.findById(productId), HttpStatus.OK);
    }

    @PutMapping(value = "/{productId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable Long productId) throws DataNotFoundException {
        return new ResponseEntity<Product>(productService.updateProduct(product, productId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{productId}", produces = "application/json")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) throws DataNotFoundException {
        return new ResponseEntity<SuccessResponseDTO>(new SuccessResponseDTO(productService.deleteById(productId)), HttpStatus.OK);
    }

}
