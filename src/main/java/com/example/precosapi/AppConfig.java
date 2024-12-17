package com.example.precosapi;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // Permite todas as rotas da API
						.allowedOrigins("https://luispedrilho.github.io") // Permite chamadas do frontend
						.allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
						.allowedHeaders("*") // Permite todos os headers
						.allowCredentials(true); // Permite envio de credenciais (opcional)
			}
		};
	}
}
