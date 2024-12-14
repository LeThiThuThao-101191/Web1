package com.example.dajewelry_cuoiky.controllers;

import com.example.dajewelry_cuoiky.models.PageProduct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.dajewelry_cuoiky.services.PageProductRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.dajewelry_cuoiky.Service.ProductService;

import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PageProductRepository productRepository;

    @Autowired
    private ProductService productService;

    // Hiển thị danh sách sản phẩm
    @GetMapping("/products")
    public String viewProducts(Model model) {
        model.addAttribute("products", productRepository.findAll()); // Truyền danh sách sản phẩm vào view
        return "jewelry/adminProducts"; // Tên template hiển thị danh sách sản phẩm
    }

    // Hiển thị form thêm sản phẩm
    @GetMapping("/product/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new PageProduct()); // Truyền object rỗng vào view
        return "jewelry/admin_add_product"; // Tên template form thêm sản phẩm
    }

    // Lưu sản phẩm vào cơ sở dữ liệu
    @PostMapping("/product/save")
    public String saveProduct(@ModelAttribute("product") PageProduct product) {
        productService.save(product); // Gọi service để lưu sản phẩm
        return "redirect:/admin/products"; // Chuyển hướng về trang danh sách sản phẩm
    }



@GetMapping("/product/edit/{id}")
public String showEditProductForm(@PathVariable("id") Integer id, Model model) {
    PageProduct product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
    model.addAttribute("product", product);
    return "jewelry/editProduct";  // Đảm bảo đường dẫn này khớp với template Thymeleaf của bạn
}

    @PostMapping("/product/update/{id}")
    public String updateProduct(@PathVariable("id") Integer id, @ModelAttribute PageProduct product) {
        // Đảm bảo product.id khớp với id từ form (hoặc cập nhật nếu cần)
        product.setId(id); // Đảm bảo id của sản phẩm được lấy từ URL
        productRepository.save(product);
        return "redirect:/admin/products";
    }



    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productRepository.deleteById(id);
        return "redirect:/admin/products";
    }
}

