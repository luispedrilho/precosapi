package com.example.precosapi.service;

import com.example.precosapi.dto.PrecoDTO;
import com.example.precosapi.dto.ItemResponseDTO;
import com.example.precosapi.model.Item;
import com.example.precosapi.model.SearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Service
public class MercadoLivreService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public MercadoLivreService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    // Método que retorna os dados do primeiro item de catálogo
    public ItemResponseDTO getFirstItemDetails(String keyword) {
        String url = "https://api.mercadolibre.com/sites/MLB/search?q=" + keyword + "&category=MLB1055&condition=new";  // MLB1055 é o ID da categoria "Smartphones"
        String jsonResponse = restTemplate.getForObject(url, String.class);

        try {
            SearchResponse response = objectMapper.readValue(jsonResponse, SearchResponse.class);
            if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
                // Pegando o primeiro item
                Item firstItem = response.getResults().get(0);

                // Calculando o preço médio
                double averagePrice = calculateAveragePrice(response.getResults());

                // Retornando as informações no formato do DTO
                return new ItemResponseDTO(
                        firstItem.getTitle(),
                        firstItem.getThumbnail(),
                        averagePrice,
                        firstItem.getSeller().getNickname()  // Nome do vendedor
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // Retorna null caso não encontre resultados
    }

    // Método para calcular o preço médio dos itens
    private double calculateAveragePrice(List<Item> items) {
        OptionalDouble avgPriceOpt = items.stream()
                .mapToDouble(item -> {
                    try {
                        return Double.parseDouble(item.getPrice());
                    } catch (NumberFormatException e) {
                        return 0.0;  // Retorna 0 em caso de erro
                    }
                })
                .average();
        return avgPriceOpt.orElse(0.0); // Retorna o preço médio ou 0 se não for possível calcular
    }

    // Método que retorna a lista de preços calculados
    public PrecoDTO buscarPrecosCalculados(String keyword) {
        String url = "https://api.mercadolibre.com/sites/MLB/search?q=" + keyword + "&category=MLB1055&condition=new";  // MLB1055 é o ID da categoria "Smartphones"
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

    // Método para calcular preços mínimo, máximo e médio
    private PrecoDTO calcularPrecos(List<Item> items) {
        OptionalDouble minPriceOpt = items.stream()
                .mapToDouble(item -> {
                    try {
                        return Double.parseDouble(item.getPrice());
                    } catch (NumberFormatException e) {
                        return 0.0;  // Retorna 0 em caso de erro
                    }
                })
                .min();
        OptionalDouble maxPriceOpt = items.stream()
                .mapToDouble(item -> {
                    try {
                        return Double.parseDouble(item.getPrice());
                    } catch (NumberFormatException e) {
                        return 0.0;  // Retorna 0 em caso de erro
                    }
                })
                .max();
        OptionalDouble avgPriceOpt = items.stream()
                .mapToDouble(item -> {
                    try {
                        return Double.parseDouble(item.getPrice());
                    } catch (NumberFormatException e) {
                        return 0.0;  // Retorna 0 em caso de erro
                    }
                })
                .average();

        double lowerPrice = minPriceOpt.orElse(0.0);
        double higherPrice = maxPriceOpt.orElse(0.0);
        double averagePrice = avgPriceOpt.orElse(0.0);
        double suggestedPrice = (lowerPrice + higherPrice + averagePrice) / 3;

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
