package com.example.chatting.repository;

import com.example.chatting.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    void findAllById(Long productId);
}
