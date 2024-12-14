package com.example.dajewelry_cuoiky.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.dajewelry_cuoiky.Service.ProductService;
import com.example.dajewelry_cuoiky.models.PageProduct;
@Controller
public class ApiProductController {
    @Autowired
    private ProductService productService;

    // Khởi tạo danh sách Product
    public ApiProductController() {
    }

    // 1. get API trả về danh sách Product
    @GetMapping("/api/products")
    @ResponseBody
    public List<PageProduct> getProductsList() {
        return productService.finalAll();
    }

    // 2. Get by id - Trả về một Product cụ thể theo Id
    @GetMapping("/api/products/{id}")
    public ResponseEntity<PageProduct> getProductId(@PathVariable("id") Integer productId) {
        PageProduct product = productService.findById(productId);  // Tìm sản phẩm theo Id
        if (product != null) {
            return ResponseEntity.status(200).body(product);
        }
        return ResponseEntity.status(404).body(null);  // Trả về lỗi 404 nếu không tìm thấy sản phẩm
    }

    // 3. Xóa Product theo Id
    @DeleteMapping("/api/products/{id}")
    @ResponseBody
    public List<PageProduct> deleteById(@PathVariable("id") Integer productId) {
        productService.delete(productId);  // Gọi phương thức delete với kiểu Long
        return productService.finalAll();  // Trả về danh sách sau khi xóa
    }

    // 4. Tạo mới Product
    @PostMapping("/api/products")
    public ResponseEntity<PageProduct> createProduct(@RequestBody PageProduct product) {
        productService.save(product);
        return ResponseEntity.status(201).body(product);
    }

    // 5. Cập nhật Product theo Id
    @PutMapping("/api/products/{id}")
    public ResponseEntity<PageProduct> updateProduct(@PathVariable("id") Integer productId,
                                                 @RequestBody PageProduct updateProduct) {
        PageProduct product = productService.findById(productId);
        if (product != null) {
            product.setName(updateProduct.getName());
            product.setPrice(updateProduct.getPrice());
            productService.save(product);
            return ResponseEntity.status(201).body(product);
        }
        return ResponseEntity.status(404).body(null);
    }
}


