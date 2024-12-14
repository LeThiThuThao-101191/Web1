package com.example.dajewelry_cuoiky.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.dajewelry_cuoiky.Entity.Role;
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
