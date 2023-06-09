package com.example.chatting.controller;

import com.example.chatting.dto.ChatDto;
import com.example.chatting.dto.ChatMessageDto;
import com.example.chatting.dto.UserDto;
import com.example.chatting.entity.Chat;
import com.example.chatting.entity.ChatMessage;
import com.example.chatting.entity.Product;
import com.example.chatting.repository.ChatRepository;
import com.example.chatting.service.ChatMessageService;
import com.example.chatting.service.ChatService;
import com.example.chatting.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
    private final ProductService productService;
    private final ChatRepository chatRepository;
    private final ChatMessageService chatMessageService;


    public ChatController(ChatService chatService, ProductService productService, ChatRepository chatRepository, ChatMessageService chatMessageService) {
        this.chatService = chatService;
        this.productService = productService;
        this.chatRepository = chatRepository;
        this.chatMessageService = chatMessageService;
    }

    @PostMapping("/chat")
    @ResponseBody
    public void handleChatRequest(@RequestBody ChatDto chat, HttpSession session) {

        UserDto userDto = (UserDto) session.getAttribute("userDto");

        System.out.println(userDto.getUsername());
        System.out.println(chat.getSeller());
        System.out.println(chat.getTitle());

        chatService.saveChat(chat, userDto);
        try{
        Thread.sleep(1000);}
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/chatList")
    public String chats(HttpSession session, Model model ) {

        UserDto userDto = (UserDto) session.getAttribute("userDto");
        List<Chat> chatList = chatService.getChatList(userDto);

        for (Chat chat : chatList) {
            String title = chat.getTitle();
            // 상품 서비스를 사용하여 상품 정보를 가져온다.
            Product product = productService.getProductByTitle(title);
            chat.setProduct(product); // Chat 객체에 상품 정보를 설정한다.
        }

        model.addAttribute("userDto",userDto);
        model.addAttribute("chats",chatList);

        return "chatList";
    }



}
