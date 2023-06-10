package com.example.chatting.dto;

import com.example.chatting.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {
    private String seller;
    private String buyer;
    private String title;
    private Product product;
}