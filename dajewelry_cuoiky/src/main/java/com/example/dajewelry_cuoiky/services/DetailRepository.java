package com.example.dajewelry_cuoiky.services;

import com.example.dajewelry_cuoiky.models.DetailProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailRepository extends JpaRepository<DetailProduct, Integer> {
}
