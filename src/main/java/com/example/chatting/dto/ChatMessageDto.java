package com.example.chatting.dto;

import com.example.chatting.entity.Chat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessageDto {
    private String message;
    private String seller;
    private String title;
}