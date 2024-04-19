package com.cs.controller;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cs.util.FileUploadUtil;

@Controller
public class UploadController {

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @GetMapping("/upload")
    public String uploadForm(Model model) {
        return "/client/upload"; // Thymeleaf template name
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("imageFile") MultipartFile file, Model model) throws IOException {
        String fileName = file.getOriginalFilename();
        String message;

        try {
            String fileCode = "avatar"; // Or replace with dynamic code generation
            String filePath = fileUploadUtil.saveFile(Paths.get("static/client/img/"), fileName, file, fileCode);
            message = "Image uploaded successfully: " + fileName;
        } catch (IOException e) {
            message = "Error uploading image: " + e.getMessage();
            e.printStackTrace();
        }

        model.addAttribute("message", message);
        return "/client/upload"; // Redirect to the same page after upload/error
    }
}