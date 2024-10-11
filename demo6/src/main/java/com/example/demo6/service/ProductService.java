package com.example.demo6.service;

import com.example.demo6.model.Product;
import com.example.demo6.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> finalAll(){
        return productRepository.findAll();
    }
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }


    public Product save(Product product) {
        return productRepository.save(product);
    }


    public Product update(Product product) {
        return productRepository.save(product);
    }
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

}
