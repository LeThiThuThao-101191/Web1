package com.example.dajewelry_cuoiky.Service;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.dajewelry_cuoiky.models.PageProduct;
import com.example.dajewelry_cuoiky.services.PageProductRepository;
    @Service
    public class ProductService {
        @Autowired
        private PageProductRepository productRepository;


        public PageProduct saveProduct(PageProduct product) {
            return productRepository.save(product); // Lưu sản phẩm vào DB
        }

        public List<PageProduct> finalAll(){
            return productRepository.findAll();
        }
        public PageProduct findById(Integer id) {
            return productRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        }


        public PageProduct save(PageProduct product) {
            return productRepository.save(product);
        }


        public PageProduct update(PageProduct product) {
            return productRepository.save(product);
        }
        public void delete(Integer id) {
            productRepository.deleteById(id);
        }

    }

