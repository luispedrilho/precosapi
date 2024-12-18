package com.example.precosapi.service;

import com.example.precosapi.dto.PrecoDTO;
import com.example.precosapi.model.Item;
import com.example.precosapi.model.SearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class MercadoLivreService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public MercadoLivreService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public PrecoDTO buscarPrecosCalculados(String keyword) {
        String url = "https://api.mercadolibre.com/sites/MLB/search?q=" + keyword + "&category=MLB1055&condition=new";  // MLB1051 é o ID da categoria "Smartphones"
        String jsonResponse = restTemplate.getForObject(url, String.class);

        try {
            SearchResponse response = objectMapper.readValue(jsonResponse, SearchResponse.class);
            if (response != null && response.getResults() != null) {
                // Filtra os itens que têm catalog_product_id não nulo e não vazio
                List<Item> filteredItems = new ArrayList<>();
                for (Item item : response.getResults()) {
                    if (item.getCatalogProductId() != null && !item.getCatalogProductId().trim().isEmpty()) {
                        filteredItems.add(item);
                    }
                }

                return calcularPrecos(filteredItems);
            } else {
                System.out.println("Não há resultados ou resposta inválida.");  // Log se não houver dados
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new PrecoDTO();
    }

    private PrecoDTO calcularPrecos(List<Item> items) {
        // Calcula preços mínimo, máximo e médio
        OptionalDouble minPriceOpt = items.stream()
                .mapToDouble(item -> {
                    try {
                        return Double.parseDouble(item.getPrice());
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter preço para número: " + item.getPrice());
                        return 0.0;  // Retorna 0 em caso de erro
                    }
                })
                .min();
        OptionalDouble maxPriceOpt = items.stream()
                .mapToDouble(item -> {
                    try {
                        return Double.parseDouble(item.getPrice());
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter preço para número: " + item.getPrice());
                        return 0.0;  // Retorna 0 em caso de erro
                    }
                })
                .max();
        OptionalDouble avgPriceOpt = items.stream()
                .mapToDouble(item -> {
                    try {
                        return Double.parseDouble(item.getPrice());
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter preço para número: " + item.getPrice());
                        return 0.0;  // Retorna 0 em caso de erro
                    }
                })
                .average();

        double lowerPrice = minPriceOpt.orElse(0.0);
        double higherPrice = maxPriceOpt.orElse(0.0);
        double averagePrice = avgPriceOpt.orElse(0.0);
        double suggestedPrice = (lowerPrice + higherPrice + averagePrice) / 3;

        // Contar itens entre os intervalos
        int countLowerToAverage = (int) items.stream()
                .filter(item -> {
                    double price = Double.parseDouble(item.getPrice());
                    return price >= lowerPrice && price < averagePrice;
                })
                .count();

        int countAverageToHigher = (int) items.stream()
                .filter(item -> {
                    double price = Double.parseDouble(item.getPrice());
                    return price >= averagePrice && price <= higherPrice;
                })
                .count();

        return new PrecoDTO(lowerPrice, countLowerToAverage, averagePrice, higherPrice, countAverageToHigher, suggestedPrice);
    }
}
