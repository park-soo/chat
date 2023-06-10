package com.example.chatting.repository;

import com.example.chatting.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    void findAllById(Long productId);

    Product findByTitle(String title);
}
