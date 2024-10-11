package com.example.demo6.controller;

import com.example.demo6.model.Product;
import com.example.demo6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductsController {
    @Autowired
    private ProductService productService;

    // Khởi tạo danh sách Product
    public ProductsController() {
    }

    // 1. get API trả về danh sách Product
    @GetMapping("/products")
    @ResponseBody
    public List<Product> getProductsList() {
        return productService.finalAll();
    }

    // 2. Get by id - Trả về một Product cụ thể theo Id
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductId(@PathVariable("id") Long productId) {
        Product product = productService.findById(productId);  // Tìm sản phẩm theo Id
        if (product != null) {
            return ResponseEntity.status(200).body(product);
        }
        return ResponseEntity.status(404).body(null);  // Trả về lỗi 404 nếu không tìm thấy sản phẩm
    }

    // 3. Xóa Product theo Id
    @DeleteMapping("/products/{id}")
    @ResponseBody
    public List<Product> deleteById(@PathVariable("id") Long productId) {
        productService.delete(productId);  // Gọi phương thức delete với kiểu Long
        return productService.finalAll();  // Trả về danh sách sau khi xóa
    }

    // 4. Tạo mới Product
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productService.save(product);
        return ResponseEntity.status(201).body(product);
    }

    // 5. Cập nhật Product theo Id
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long productId,
                                                 @RequestBody Product updateProduct) {
        Product product = productService.findById(productId);
        if (product != null) {
            product.setName(updateProduct.getName());
            product.setPrice(updateProduct.getPrice());
            productService.save(product);
            return ResponseEntity.status(201).body(product);
        }
        return ResponseEntity.status(404).body(null);
    }
}
