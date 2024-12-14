package com.example.dajewelry_cuoiky.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.dajewelry_cuoiky.Entity.User;
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
