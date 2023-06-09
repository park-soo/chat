package com.example.chatting.controller;

import com.example.chatting.dto.ChatMessageDto;
import com.example.chatting.dto.UserDto;
import com.example.chatting.entity.Chat;
import com.example.chatting.repository.ChatRepository;
import com.example.chatting.service.ChatMessageService;
import com.example.chatting.service.ChatService;
import com.example.chatting.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatMessageController {

    private final ChatService chatService;
    private final ProductService productService;
    private final ChatRepository chatRepository;
    private final ChatMessageService chatMessageService;


    public ChatMessageController(ChatService chatService, ProductService productService, ChatRepository chatRepository, ChatMessageService chatMessageService) {
        this.chatService = chatService;
        this.productService = productService;
        this.chatRepository = chatRepository;
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessageDto handleChatMessage(ChatMessageDto chatMessageDto, HttpSession session) {

        UserDto userDto = (UserDto) session.getAttribute("userDto");

        String fromId = String.valueOf(userDto.getUsername());
        String toId = chatMessageDto.getSeller();
        String message = chatMessageDto.getMessage();
        Chat chat = chatRepository.findByTitle(chatMessageDto.getTitle());
        Long chatId = chat.getId();

        chatMessageService.saveChatMessage(fromId,toId,message,chatId);

        return chatMessageDto;
    }
}
