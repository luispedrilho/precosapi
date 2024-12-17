package com.example.precosapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permitir CORS para o front-end específico (substitua com a URL do seu front-end)
        registry.addMapping("/**")
                .allowedOrigins("https://luispedrilho.github.io") // Adicione a URL do seu front-end aqui
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}