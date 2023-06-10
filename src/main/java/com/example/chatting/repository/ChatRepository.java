package com.example.chatting.repository;

import com.example.chatting.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findBySellerOrBuyer(String username, String username1);

    boolean existsByBuyerAndSellerAndTitle(String buyer, String seller, String title);
    Chat findByTitle(String title);
}
