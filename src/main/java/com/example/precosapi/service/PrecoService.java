package com.example.precosapi.service;

import com.example.precosapi.dto.PrecoDTO;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PrecoService {

    public PrecoDTO calcularPrecos(String keyword, String status) {
        // Simulação de cálculo de preços
        Random random = new Random();

        double lowerPrice = 100 + random.nextInt(50); // Preço mínimo entre 100 e 150
        double averagePrice = lowerPrice + random.nextInt(100); // Preço médio
        double higherPrice = averagePrice + random.nextInt(100); // Preço máximo

        int lowerPriceCount = random.nextInt(10) + 1; // Simula quantidade de itens com preço mínimo
        int higherPriceCount = random.nextInt(10) + 1; // Simula quantidade de itens com preço máximo

        // Calcula o preço sugerido
        double suggestedPrice = (lowerPrice + averagePrice + higherPrice) / 3;

        // Retorna o DTO com os preços e contagens
        return new PrecoDTO(lowerPrice, lowerPriceCount, averagePrice, higherPrice, higherPriceCount, suggestedPrice);
    }
}
