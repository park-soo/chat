package com.example.chatting.service;

import com.example.chatting.dto.UserDto;
import com.example.chatting.entity.User;
import com.example.chatting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void sign(UserDto userDto) {

        User user = new User();
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        userRepository.save(user);
    }

    public UserDto login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username,password);
        if (user != null) {
            UserDto userDto = new UserDto();
            userDto.setUsername(user.getUsername());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());
            userDto.setId(user.getId());
            return userDto;
        }
        return null;
    }
}
