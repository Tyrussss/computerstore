package com.cs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cs.util.FileUploadUtil;

@Configuration
public class FileUploadUtilConfig {

    @Bean
    public FileUploadUtil fileUploadUtil() {
        return new FileUploadUtil();
    }
}