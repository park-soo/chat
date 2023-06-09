package com.example.chatting.controller;

import com.example.chatting.dto.ProductDto;
import com.example.chatting.dto.UserDto;
import com.example.chatting.entity.Product;
import com.example.chatting.service.ProductService;
import com.example.chatting.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.example.chatting.service.ProductService.saveImage;


@Controller
public class SellController {

    private final UserService userService;

    public SellController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sellList")
    public String sellListPage(Model model) {
        // 상품 목록을 가져와서 모델에 추가

        List<Product> productList = ProductService.getAllProducts();
        model.addAttribute("products", productList);

        return "sellList";
    }

    @GetMapping("/sell")
    public String sellPage(HttpSession session, Model model) {
        // 세션에서 사용자 정보 가져오기
        UserDto userDto = (UserDto) session.getAttribute("userDto");

        // 사용자 정보를 모델에 추가
        model.addAttribute("userDto", userDto);

        return "sell";
    }

    @PostMapping("/sell")
    public String sellProduct(@RequestParam("seller") String seller,
                              @RequestParam("title") String title,
                              @RequestParam("price") int price,
                              @RequestParam("description") String description,
                              @RequestParam("image") MultipartFile image,
                              Model model) {
        // 판매자 정보를 기반으로 판매 등록 로직 처리

        // 등록한 제품 정보를 모델에 추가
        System.out.println(seller);

        ProductDto product = new ProductDto();
        product.setSeller(seller);
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);

        try {
            String imageName = saveImage(image);
            product.setImage(imageName);
        } catch (IOException e) {
            // 이미지 저장 실패 시 예외 처리
            e.printStackTrace();
            return "error";
        }

        ProductService.regist(product);

        return "redirect:/sellList";
    }



}