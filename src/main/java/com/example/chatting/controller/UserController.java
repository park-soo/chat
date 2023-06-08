package com.example.chatting.controller;

import com.example.chatting.dto.UserDto;
import com.example.chatting.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "sign";
    }

    @PostMapping("/signup")
    public String sign(@RequestParam("username") String username,
                       @RequestParam("password") String password,
                       @RequestParam("email") String email) {

        UserDto userDto = new UserDto();
        userDto.setEmail(email);
        userDto.setPassword(password);
        userDto.setUsername(username);

        UserService.sign(userDto);

        System.out.println("가입된 사용자 이메일:" + userDto.getEmail());
        System.out.println("가입된 사용자 비번:" + userDto.getPassword());
        System.out.println("가입된 사용자 닉네임:" + userDto.getUsername());

        return "login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session, Model model) {

        UserDto userDto = userService.login(username, password);

        if (userDto != null) {
            // 로그인 성공
            session.setAttribute("userDto", userDto);
            return "redirect:/main";
        } else {
            // 로그인 실패
            model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "login";
        }
    }



}
