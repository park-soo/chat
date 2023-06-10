package com.example.chatting.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_id")
    private String fromId;

    @Column(name = "to_id")
    private String toId;

    @Column(name = "chat_id")
    private Long chat;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "message")
    private String message;

    // 생성자, getter, setter, 기타 메소드 생략
}