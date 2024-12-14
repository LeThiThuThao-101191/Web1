package com.example.dajewelry_cuoiky.services;

import com.example.dajewelry_cuoiky.models.PageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PageProductRepository extends JpaRepository<PageProduct, Integer> {
    // Tìm kiếm sản phẩm có tên chứa từ khóa (không phân biệt chữ hoa/thường)
    List<PageProduct> findByNameContainingIgnoreCase(String name);

}
