package com.example.chatting.service;

import com.example.chatting.dto.ChatDto;
import com.example.chatting.dto.UserDto;
import com.example.chatting.entity.Chat;
import com.example.chatting.entity.Product;
import com.example.chatting.repository.ChatRepository;
import com.example.chatting.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {


    private final ChatRepository chatRepository;

    private final ProductRepository productRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository, ProductRepository productRepository) {
        this.chatRepository = chatRepository;
        this.productRepository = productRepository;
    }

    public void saveChat(ChatDto chatDto, UserDto userDto) {
        System.out.println(userDto.getUsername());
        System.out.println(chatDto.getSeller());
        System.out.println(chatDto.getTitle());

        String buyer = userDto.getUsername();
        String seller = chatDto.getSeller();
        String title = chatDto.getTitle();

        Product pId = productRepository.findByTitle(chatDto.getTitle());

        // 중복 체크
        if (chatRepository.existsByBuyerAndSellerAndTitle(buyer, seller, title) ||
                seller.equals(userDto.getUsername())) {
            // 이미 해당 값이 존재하거나 seller가 현재 유저와 동일한 경우 처리할 로직 작성
            System.out.println("중복 및 판매자가 같아");
            return;
        }

        Chat chat = new Chat();
        chat.setBuyer(userDto.getUsername());
        chat.setSeller(chatDto.getSeller());
        chat.setTitle(chatDto.getTitle());
        chat.setProduct(pId);

        chatRepository.save(chat);
    }

    public List<Chat> getChatList(UserDto userDto) {
        String username = userDto.getUsername();
        List<Chat> chatList = chatRepository.findBySellerOrBuyer(username, username);
        return chatList;
    }
}
