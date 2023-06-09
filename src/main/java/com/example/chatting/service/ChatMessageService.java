package com.example.chatting.service;

import com.example.chatting.entity.ChatMessage;
import com.example.chatting.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatMessage saveChatMessage(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }

    public void saveChatMessage(String fromId, String toId, String message, Long chatId) {

        ChatMessage chatmessage = new ChatMessage();
        chatmessage.setChat(chatId);
        chatmessage.setMessage(message);
        chatmessage.setToId(toId);
        chatmessage.setFromId(fromId);
        chatmessage.setTimestamp(LocalDateTime.now());

        chatMessageRepository.save(chatmessage);
    }
}