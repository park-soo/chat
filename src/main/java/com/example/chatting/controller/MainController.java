package com.example.chatting.controller;

import org.springframework.ui.Model;
import com.example.chatting.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // ...

    @GetMapping("/main")
    public String mainPage(HttpSession session, Model model) {
        // 세션에서 사용자 정보 가져오기
        UserDto userDto = (UserDto) session.getAttribute("userDto");

        // 사용자 정보를 모델에 추가
        model.addAttribute("userDto", userDto);

        return "main";
    }

    // ...
}