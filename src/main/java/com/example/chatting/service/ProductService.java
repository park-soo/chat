package com.example.chatting.service;

import com.example.chatting.dto.ProductDto;
import com.example.chatting.entity.Product;
import com.example.chatting.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public static void regist(ProductDto productDto) {

        Product product = new Product();
        product.setSeller(productDto.getSeller());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImagePath(productDto.getImage());

        productRepository.save(product);
    }

    public static List<Product> getAllProducts() {
        // 상품 목록을 조회하는 로직을 구현하세요
        // 예를 들어, 다음은 ProductRepository에서 모든 상품을 조회하는 코드입니다.
        List<Product> productList = productRepository.findAll();
        return productList;
    }



    // 생성할 이미지 저장 디렉토리 경로
    private static final String UPLOAD_DIR = "/Users/parkmansoo/Downloads/chat-master/src/main/resources/static/images";

// ...

    // 이미지 저장 디렉토리 생성
    private void createUploadDirectory() {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }


    public static String saveImage(MultipartFile image) throws IOException {
        // 현재 시간을 기준으로 파일 이름 생성
        LocalDateTime now = LocalDateTime.now();
        String timestamp = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(now);
        String prefix = "minty";
        String extension = getFileExtension(image.getOriginalFilename());

        // 저장할 파일 경로 생성
        String fileName = timestamp + prefix + "." + extension;
        String filePath = UPLOAD_DIR + "/" + fileName;

        // 파일 저장
        Path path = Paths.get(filePath);
        Files.write(path, image.getBytes());

        // 기존 파일 삭제
        if (image.getOriginalFilename() != null && !image.getOriginalFilename().isEmpty()) {
            String previousFileName = UPLOAD_DIR + "/" + image.getOriginalFilename();
            Path previousPath = Paths.get(previousFileName);
            Files.deleteIfExists(previousPath);
        }

        return fileName;
    }

    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex >= 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }

    public Product getProductByTitle(String title) {

        Product product = productRepository.findByTitle(title);
        return product;
    }
}
