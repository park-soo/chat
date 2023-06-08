package com.example.chatting.controller;

import com.example.chatting.dto.ChatDto;
import com.example.chatting.dto.UserDto;
import com.example.chatting.entity.Chat;
import com.example.chatting.service.ChatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    @ResponseBody
    public String handleChatRequest(@RequestBody ChatDto chat, HttpSession session) {

        UserDto userDto = (UserDto) session.getAttribute("userDto");

        System.out.println(userDto.getUsername());
        System.out.println(chat.getSeller());
        System.out.println(chat.getTitle());

        chatService.saveChat(chat, userDto);

        return "chatList";
    }

    @GetMapping("/chatList")
    public String chats(HttpSession session, Model model ) {

        UserDto userDto = (UserDto) session.getAttribute("userDto");
        List<Chat> chatList = chatService.getChatList(userDto);

        model.addAttribute("userDto",userDto);
        model.addAttribute("chats",chatList);

        return "chatList";
    }

}
