package com.example.dajewelry_cuoiky.controllers;

import com.example.dajewelry_cuoiky.models.DetailProduct;
import com.example.dajewelry_cuoiky.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class HomeController {
    // Best Seller Product
    @Autowired
    private BestSellerRepository repo1;
    // Home
    @Autowired
    private HomeRepository repo;
    @GetMapping({"", "/"} )
    public String home(Model model) {
        model.addAttribute("products", repo.findAll());
        model.addAttribute("bestseller", repo1.findAll());

        return "jewelry/home"; // Render home.html
    }

    // Detail Product lay theo id
    @Autowired
    private DetailRepository repo2;
    @Autowired
    private ReviewRepository reponse;
    @GetMapping("/detail/{id}")
    public String getById(@PathVariable("id") int id, Model model) {
        for (DetailProduct detail : repo2.findAll()) {
            if (detail.getId() == id) {
                model.addAttribute("detail_products", detail); // Gắn dữ liệu vào model
                model.addAttribute("reviews", reponse.findAll());
                return "jewelry/detail"; // Tên file HTML (detail.html)
            }
        }
        return "error"; // Tên file HTML khác nếu không tìm thấy
    }

    // Trang products
    @Autowired
    private PageProductRepository repo3;

    // Trang hiển thị danh sách tất cả sản phẩm
    @GetMapping("/products")
    public String getAllProducts(Model model) {
        model.addAttribute("page_products", repo3.findAll());
        return "jewelry/products"; // Trả về view "products"
    }

    // Trang tìm kiếm sản phẩm thoe tên 
    @GetMapping("/search")
    public String searchProducts(@RequestParam(value = "name", required = false) String name, Model model) {
        if (name != null && !name.isEmpty()) {
            model.addAttribute("page_products", repo3.findByNameContainingIgnoreCase(name));
            model.addAttribute("searchKeyword", name); // Đưa từ khóa tìm kiếm vào model
        } else {
            model.addAttribute("page_products", null); // Không có kết quả nếu từ khóa rỗng
        }
        return "jewelry/search"; // Trả về view "search"
    }

}
